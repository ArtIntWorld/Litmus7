package com.litmus7.inventorymanagementphase2.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventorymanagementphase2.dao.InventoryDao;
import com.litmus7.inventorymanagementphase2.dto.Product;
import com.litmus7.inventorymanagementphase2.exception.CSVUtilException;
import com.litmus7.inventorymanagementphase2.exception.FileUtilException;
import com.litmus7.inventorymanagementphase2.exception.InventoryDaoException;
import com.litmus7.inventorymanagementphase2.exception.InventoryServiceException;
import com.litmus7.inventorymanagementphase2.util.CSVUtil;
import com.litmus7.inventorymanagementphase2.util.FileUtil;

public class InventoryService {
	private static final Logger logger = LogManager.getLogger(InventoryService.class);
	private static InventoryDao inventoryDao = new InventoryDao(); 
	
	public Product convertToInventory(String[] rawInventory){
		
		Product inventory = new Product();
		
		inventory.setId(Integer.parseInt(rawInventory[0].trim()));
		inventory.setProductName(rawInventory[1].trim());
		inventory.setQuantity(Integer.parseInt(rawInventory[2].trim()));
		inventory.setPrice(Double.parseDouble(rawInventory[3].trim()));
		
		return inventory;
	}
	
	public List<Product> convertAllToInventory(List<String[]> rawInventories){
		
		List<Product> inventories = new ArrayList<>();
		
		for(String[] rawInventory : rawInventories) {
			inventories.add(convertToInventory(rawInventory));
		}
		
		return inventories;
		
	}

	public Boolean addInventoryToDB(String csvPath, String processedPath, String errorPath) throws InventoryServiceException{
		
		logger.info("Beginning addInventoryToDB() from [{}]", csvPath);
		
		List<String[]> rawProducts = new ArrayList<>();
		List<Product> products = new ArrayList<>();
				
			try {
				rawProducts = CSVUtil.extractDataFromCSV(csvPath);
				logger.debug("Succsssfully extracted {} records drom the file.", rawProducts.size());
			} catch (CSVUtilException e) {
				logger.error("Error while extracting csv file.");
				throw new InventoryServiceException(e.getErrorCode(), e);
			}
			
			
			products = convertAllToInventory(rawProducts);
			logger.info("Converted product records into object structure.");
								
			try {
				inventoryDao.insertInventory(products);
				logger.info("Successfully inserted {} records into the table.", products.size());
				
				try {
					FileUtil.moveFile(csvPath, processedPath);
				} catch (FileUtilException fileEx) {
					logger.error("Error while moving file from [{}] to [{}].", csvPath, processedPath);
					throw new InventoryServiceException(fileEx.getErrorCode(), fileEx);
				}
				return true;
			} catch (InventoryDaoException e) {
				logger.error("Error while inserting the records into table.", e);
				try {
					FileUtil.moveFile(csvPath, errorPath);
				} catch (FileUtilException fileEx) {
					logger.error("Error while moving file from [{}] to [{}].", csvPath, errorPath);
					throw new InventoryServiceException(fileEx.getErrorCode(), fileEx);
				}
				return false;
			}
		
		
	}
}
