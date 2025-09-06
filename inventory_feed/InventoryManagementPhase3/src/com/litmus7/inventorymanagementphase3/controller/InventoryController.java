package com.litmus7.inventorymanagementphase3.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventorymanagementphase3.constants.ResponseConstants;
import com.litmus7.inventorymanagementphase3.dto.Response;
import com.litmus7.inventorymanagementphase3.exception.FileUtilException;
import com.litmus7.inventorymanagementphase3.exception.InventoryServiceException;
import com.litmus7.inventorymanagementphase3.service.InventoryService;
import com.litmus7.inventorymanagementphase3.util.ApplicationErrorHandler;
import com.litmus7.inventorymanagementphase3.util.FileUtil;

public class InventoryController {
	private static InventoryService inventoryService = new InventoryService();
	private static Logger logger = LogManager.getLogger(InventoryController.class);
	private static final int THREAD_POOL_SIZE = 5;
	
	public Response<Integer> insertCSVToDB(String folderPath) {
		
		logger.info("Beginning insertCSVToDB() wth folder path [{}].", folderPath);
		List<String> csvPaths = null;
		String errorPath = "D:/Litmus7/inventory_feed/InventoryManagementPhase3/error";
		String processedPath = "D:/Litmus7/inventory_feed/InventoryManagementPhase3/processed";
		
		try {
			csvPaths = FileUtil.getAllCSVFiles(folderPath);
		} catch (FileUtilException e) {
			logger.error("Error occured while extracting csv paths : " + ApplicationErrorHandler.getMessage(String.valueOf(e.getErrorCode())));
			return new Response<>(ResponseConstants.BAD_REQUEST, ApplicationErrorHandler.getMessage(String.valueOf(e.getErrorCode())));
		}
		
		AtomicInteger processedCount = new AtomicInteger(0);
		AtomicInteger errorCount = new AtomicInteger(0);
		
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		
		logger.info("Beginning with {} csvFiles and {} threads", csvPaths.size(), THREAD_POOL_SIZE);
		
		for(String csvPath : csvPaths) {
			
			ThreadFileHandler fileThread = new ThreadFileHandler(csvPath, processedPath, errorPath, processedCount, errorCount, inventoryService);
			
			executor.submit(fileThread);
			logger.debug("Assigned the task for the file in path : {}.", csvPath);
		}
		
		executor.shutdown();
		logger.info("Tasks submitted and waiting for completion.");
		
		 try {
	            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
	                logger.warn("Thread pool did not terminate within 1 hour. Forcing shutdown...");
	                executor.shutdownNow();
	                if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
	                    logger.error("Thread pool did not terminate after forced shutdown");
	                }
	                return new Response<>(ResponseConstants.BAD_REQUEST, ResponseConstants.TIMOUT_MESSAGE, processedCount.get());
	            }
	        } catch (InterruptedException e) {
	            logger.error("Thread pool interrupted while waiting for completion", e);
	            executor.shutdownNow();
	            Thread.currentThread().interrupt();
	            return new Response<>(ResponseConstants.BAD_REQUEST, ResponseConstants.INTERRUPTED_MESSAGE, processedCount.get());
	        }
		
		
		Integer finalProcessedCount = processedCount.get();
		Integer finalErrorCount = errorCount.get();
		
		if (finalProcessedCount > 0 && finalErrorCount > 0) {
            logger.warn("Partial success due to some error files.");
            return new Response<>(ResponseConstants.OK, ResponseConstants.PARTIAL_INSERTED_MESSAGE, processedCount.get());
        } else if (finalProcessedCount > 0) {
            logger.info("Successful execution of files.");
            return new Response<>(ResponseConstants.OK, ResponseConstants.NO_INSERTED_MESSAGE, processedCount.get());
        } else {
            logger.error("Invalid files being processed.");
            return new Response<>(ResponseConstants.BAD_REQUEST, ResponseConstants.NO_INSERTED_MESSAGE, processedCount.get());
        } 
        
	}
	
}

class ThreadFileHandler implements Runnable{

	private String csvPath;
	private String processedPath;
	private String errorPath;
	private AtomicInteger processedCount;
	private AtomicInteger errorCount;
	private InventoryService inventoryService;
	private static Logger logger = LogManager.getLogger(ThreadFileHandler.class);
	
	public ThreadFileHandler(String csvPath, String processedPath, String errorPath, AtomicInteger processedCount, AtomicInteger errorCount, InventoryService inventoryService) {
		this.csvPath = csvPath;
		this.processedPath = processedPath;
		this.errorPath = errorPath;
		this.processedCount = processedCount;
		this.errorCount = errorCount;
		this.inventoryService = inventoryService;
	}
	
	@Override
	public void run() {
		
		String threadName = Thread.currentThread().getName();
		logger.info("[{}] is handling file in path [{}].", threadName, csvPath);
		
		try {
			boolean serviceStatus = inventoryService.addInventoryToDB(csvPath, processedPath, errorPath);
			
			if(serviceStatus) {
				logger.info("Successful entry of records in the file from [{}]", csvPath);
				processedCount.incrementAndGet();
			} else {
				logger.warn("Failed the entry of records in the file from [{}]", csvPath);
				errorCount.incrementAndGet();
			}
		} catch (InventoryServiceException e) {
			logger.error("[{}] Service error for file {}: {}", threadName, csvPath, e.getMessage());
            errorCount.incrementAndGet();
        } catch (Exception e) {
            logger.error("[{}] Unexpected error for file in {}: {}", threadName, csvPath, e.getMessage());
            errorCount.incrementAndGet();
            }
		
	}
	
}
