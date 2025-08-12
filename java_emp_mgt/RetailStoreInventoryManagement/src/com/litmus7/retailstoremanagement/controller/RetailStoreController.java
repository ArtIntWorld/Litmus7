package com.litmus7.retailstoremanagement.controller;

import java.util.Comparator;
import java.util.List;
import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.dto.Response;

public class RetailStoreController {
	
	public Response<Boolean> exportProductDetails(String[] product, String category){
		// 1. Validate the format of category entered.
		// 2. Call the function exportProduct() from RetailStoreService.
		return null;
	}
	
	public Response<List<String[]>> importAllProductsDetails(){
		// 1. Call importAllProducts() from RetailStoreService and return the list of product details.
		 return null;
	}
	
	public Response<List<String[]>> importAllProductsDetailsByCategory(String category){
		// 1. Validate the format of category.
		// 2. Call importAllProductsByCategory() from RetailStoreService and return the list of product details.
		 return null;
	}
	
	public Response<List<String[]>> importSortedProductsDetails(Comparator<Product> comparator){
		// 1. Call importAndSortProducts() from RetailStoreService and return the list of sorted product details.
		return null;
	}
}
