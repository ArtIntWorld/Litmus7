package com.litmus7.inventorymanagementsinglethread.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.litmus7.inventorymanagementsinglethread.dao.InventoryDao;
import com.litmus7.inventorymanagementsinglethread.dto.Inventory;
import com.litmus7.inventorymanagementsinglethread.exception.InventoryException;
import com.litmus7.inventorymanagementsinglethread.util.CSVUtil;
import com.litmus7.inventorymanagementsinglethread.util.FileUtil;

public class InventoryService {

	private InventoryDao inventoryDao = new InventoryDao();
	private static final Logger logger = LogManager.getLogger(InventoryService.class);
	
	public Inventory convertToInventory(String[] rawInventory){
		
		Inventory inventory = new Inventory();
		
		inventory.setId(Integer.parseInt(rawInventory[0].trim()));
		inventory.setProductName(rawInventory[1].trim());
		inventory.setQuantity(Integer.parseInt(rawInventory[2].trim()));
		inventory.setPrice(Double.parseDouble(rawInventory[3].trim()));
		
		return inventory;
	}
	
	public List<Inventory> convertAllToInventory(List<String[]> rawInventories){
		
		List<Inventory> inventories = new ArrayList<>();
		for(String[] rawInventory : rawInventories) {
			inventories.add(convertToInventory(rawInventory));
		}
		
		return inventories;
		
	}
	
	public HashMap<String, Integer> addInventoryToDB(List<String> csvPaths, String processedPath, String errorPath) throws InventoryException{
		
		boolean insertStatus;
		Integer errorCount = 0;
		Integer successCount = 0;
		List<String[]> rawInventories = new ArrayList<>();
		List<Inventory> inventories = new ArrayList<>();
		HashMap<String, Integer> result = new HashMap<>();
		
		logger.info("Beginning addInventoryToDB()");
		logger.debug("Processing {} csv files from the [input] folder.", csvPaths.size());
		
		try{
			
			for(String csvPath : csvPaths) {
				
				logger.info("Processing file : " + csvPath);
				
				rawInventories = CSVUtil.extractDataFromCSV(csvPath);
				inventories = convertAllToInventory(rawInventories);
				logger.debug("Retrieved {} records from the file [{}].", inventories.size(), csvPath);
								
				insertStatus = inventoryDao.insertInventory(inventories);
				
				if(insertStatus) {
					FileUtil.moveFile(csvPath, processedPath);
					logger.info("Successfully inserted the records from file [{}].", csvPath);
					logger.debug("Moved the file from [{}] to [{}]", csvPath, processedPath);
					
					successCount++;
				}
				else {
					FileUtil.moveFile(csvPath, errorPath);
					logger.info("Failed to insert the records from file [{}].", csvPath);
					logger.debug("Moved the file from [{}] to [{}]", csvPath, errorPath);
					errorCount++;
				}
				
			}
			
			result.put("successCount", successCount);
			result.put("errorCount", errorCount);
			
			return result;
			
			
		} catch(InventoryException e) {
			throw new InventoryException(e.getErrorCode(), e);
		}
		
		
	}
	
	
}
