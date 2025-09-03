package com.litmus7.inventorymanagementphase2.ui;

import com.litmus7.inventorymanagementphase2.controller.InventoryController;
import com.litmus7.inventorymanagementphase2.dto.Response;

public class Client {
	private static InventoryController request = new InventoryController();
	
	public static void main(String[] args) {
		
		Response<Integer> insertResponse = request.insertCSVToDB("D:/Litmus7/inventory_feed/InventoryManagementPhase2/input");
		
		Integer statusCode = insertResponse.getStatusCode();
		
		if(statusCode == 200) {
			System.out.println("Message : " + insertResponse.getStatusMessage());
			System.out.println("Successfull entries : " + insertResponse.getData());
		} else {
			System.out.println("Message : " + insertResponse.getStatusMessage());
		}
		
	}
}
