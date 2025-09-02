package com.litmus7.inventorymanagementsinglethread.controller;

import java.util.HashMap;

import com.litmus7.inventorymanagementsinglethread.constants.ResponseConstants;
import com.litmus7.inventorymanagementsinglethread.dto.Response;
import com.litmus7.inventorymanagementsinglethread.exception.InventoryException;
import com.litmus7.inventorymanagementsinglethread.service.InventoryService;
import com.litmus7.inventorymanagementsinglethread.util.ApplicationErrorHandler;

public class InventoryController {
	private InventoryService inventoryService = new InventoryService();
	
	public Response<Void> addInventoryToDB(String source) {
		
		String processedPath = "D:/Litmus7/inventory_feed/InventoryManagementSingleThread/processed";
		String errorPath = "D:/Litmus7/inventory_feed/InventoryManagementSingleThread/error";
		
		
		try {
			
			HashMap<String, Integer> addStatus = inventoryService.addInventoryToDB(source, processedPath, errorPath);
			
			if(addStatus.get("errorCount") == 0 && addStatus.get("successCount") != 0) {
				return new Response<>(ResponseConstants.OK, ResponseConstants.INSERTED_MESSAGE);
			}else if(addStatus.get("successCount") == 0) {
				return new Response<>(ResponseConstants.OK, ResponseConstants.FULLY_INSERTED_MESSAGE);
			} else {
				return new Response<>(ResponseConstants.OK, ResponseConstants.PARTIAL_INSERTED_MESSAGE);
			}
			
		} catch (InventoryException e) {
			return new Response<>(ResponseConstants.BAD_REQUEST, ApplicationErrorHandler.getMessage(String.valueOf(e.getErrorCode())));
		}
	}
}

//return new ResponseDTO<>(ResponseConstants.BAD_REQUEST,ErrorMessageLoader.getMessage(String.valueOf(e.getErrorCode()), filePath)

//String.valueOf(e.getErrorCode()))