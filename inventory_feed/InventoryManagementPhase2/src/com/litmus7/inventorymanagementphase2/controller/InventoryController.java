package com.litmus7.inventorymanagementphase2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventorymanagementphase2.constants.ResponseConstants;
import com.litmus7.inventorymanagementphase2.dto.Response;
import com.litmus7.inventorymanagementphase2.exception.FileUtilException;
import com.litmus7.inventorymanagementphase2.exception.InventoryServiceException;
import com.litmus7.inventorymanagementphase2.service.InventoryService;
import com.litmus7.inventorymanagementphase2.util.ApplicationErrorHandler;
import com.litmus7.inventorymanagementphase2.util.FileUtil;

public class InventoryController {
	private static InventoryService inventoryService = new InventoryService();
	private static Logger logger = LogManager.getLogger(InventoryController.class);
	
	public Response<Integer> insertCSVToDB(String folderPath) {
		
		logger.info("Beginning insertCSVToDB() wth folder path [{}].", folderPath);
		List<String> csvPaths = null;
		String errorPath = "D:/Litmus7/inventory_feed/InventoryManagementPhase2/error";
		String processedPath = "D:/Litmus7/inventory_feed/InventoryManagementPhase2/processed";
		Integer threadIndex = 0;
		
		try {
			csvPaths = FileUtil.getAllCSVFiles(folderPath);
		} catch (FileUtilException e) {
			logger.error("Error occured while extracting csv paths : " + ApplicationErrorHandler.getMessage(String.valueOf(e.getErrorCode())));
			return new Response<>(ResponseConstants.BAD_REQUEST, ApplicationErrorHandler.getMessage(String.valueOf(e.getErrorCode())));
		}
		
		AtomicInteger processedCount = new AtomicInteger(0);
		AtomicInteger errorCount = new AtomicInteger(0);
		
		List<Thread> threads = new ArrayList<>();
		
		for(String csvPath : csvPaths) {
			
			threadIndex++;
			
			ThreadFileHandler fileThread = new ThreadFileHandler(csvPath, processedPath, errorPath, processedCount, errorCount, inventoryService);
			
			Thread thread = new Thread(fileThread, "FileProcessor - [" + threadIndex +"]");
			threads.add(thread);
			thread.start();
			logger.debug("Started thread {} for file from [{}]", thread.getName(), csvPath);
		}
		
		for (Thread thread : threads) {
            try {
                thread.join();
                logger.debug("Thread {} completed", thread.getName());
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while waiting: {}", thread.getName(), e);
                Thread.currentThread().interrupt();
                return new Response<>(ResponseConstants.BAD_REQUEST, "Processing interrupted", processedCount.get());
            }
        }
		
		Integer finalProcessedCount = processedCount.get();
		Integer finalErrorCount = errorCount.get();
		
		logger.info("Multithreaded processing complete. Success={}, Failed={}", finalProcessedCount, finalErrorCount);
		
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
