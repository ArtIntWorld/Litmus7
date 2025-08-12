package com.litmus7.retailstoremanagement.service;

import java.util.Comparator;
import java.util.List;

import com.litmus7.retailstoremanagement.dto.Clothing;
import com.litmus7.retailstoremanagement.dto.Electronic;
import com.litmus7.retailstoremanagement.dto.Grocery;
import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.exception.RetailStoreServiceException;

public class RetailStoreService {
	
	public Electronic formatElectronicProduct(String[] product) throws RetailStoreServiceException {
		// 1. Convert into Electronic object and return it.
		return null;
	}
	
	public Clothing formatClothingProduct(String[] product) throws RetailStoreServiceException {
		// 1. Convert into Clothing object and return it.
		return null;
	}
	
	public Grocery formatGroceryProduct(String[] product) throws RetailStoreServiceException {
		// 1. Convert into Grocery object and return it.
		return null;
	}
	
	public boolean exportProduct(String[] product, String category) throws RetailStoreServiceException {
		// 1. Validate the product based on category chosen
		// 2. Based on category, we choose the methods formatElectronicProduct(), formatClothingProduct(), formatGroceryProduct() to convert into object format.
		// 3. Call the function addProduct() from RetailStoreDAO of specified category as parameter and return true if successful entry.
		return true;
	}
	
	public List<String[]> importAllProducts() throws RetailStoreServiceException {
		// 1. Call the function getAllProducts() from RetailStoreDAO and returns the list of product data.
		return null;
	}
	
	public List<String[]> importAllProductsByCategory(String category) throws RetailStoreServiceException {
		// 1. Call the method getAllProductsByCategory() from RetailStoreDAO and return the list of products under the category.
		return null;
	}
	
	public List<String[]> importAndSortProducts(Comparator<Product> comparator) throws RetailStoreServiceException {
		// 1. Import all the products using getAllProducts() from RetailStoreDAO.
		// 2. Sort the imported product details as per the comparator provided. Return the list of sorted product details.
		return null;
	}
}
