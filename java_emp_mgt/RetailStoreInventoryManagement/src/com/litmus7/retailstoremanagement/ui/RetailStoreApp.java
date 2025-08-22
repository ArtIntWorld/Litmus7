package com.litmus7.retailstoremanagement.ui;

import com.litmus7.retailstoremanagement.controller.RetailStoreController;
import com.litmus7.retailstoremanagement.dto.Response;

public class RetailStoreApp {
	
	static RetailStoreController request;
	
	public static void main(String[] args) {
		
		Response inputProductResponse = request.importProductDetails(null);
		
		Response allProductsResponse = request.exportAllProductsDetails();
		
		Response categoryProductsResponse = request.exportAllProductsDetailsByCategory(null);
		
		Response sortProductResponse = request.exportSortedProductsDetails(null);
	}
}
