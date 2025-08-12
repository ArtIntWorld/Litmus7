package com.litmus7.retailstoremanagement.ui;

import com.litmus7.retailstoremanagement.controller.RetailStoreController;
import com.litmus7.retailstoremanagement.dto.Response;

public class RetailStoreApp {
	
	static RetailStoreController request;
	
	public static void main(String[] args) {
		
		Response inputProductResponse = request.exportProductDetails(null, null);
		
		Response allProductsResponse = request.importAllProductsDetails();
		
		Response categoryProductsResponse = request.importAllProductsDetailsByCategory(null);
		
		Response sortProductResponse = request.importSortedProductsDetails(null);
	}
}
