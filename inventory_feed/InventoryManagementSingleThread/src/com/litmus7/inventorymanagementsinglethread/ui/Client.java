package com.litmus7.inventorymanagementsinglethread.ui;

import com.litmus7.inventorymanagementsinglethread.controller.InventoryController;
import com.litmus7.inventorymanagementsinglethread.dto.Response;

public class Client {
	private static InventoryController request = new InventoryController();
	
	public static void main(String[] args) {
		
		String filePath = "D:/Litmus7/inventory_feed/InventoryManagementSingleThread/input";
		
		Response<Void> responseInsert = request.addInventoryToDB(filePath);
		
		System.out.println("Status : " + responseInsert.getStatusCode());
		System.out.println("Message : " + responseInsert.getStatusMessage());
		
	}
}
