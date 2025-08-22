package com.litmus7.retailstoremanagement.dao;

import java.util.List;

import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.exception.RetailStoreDAOException;

public class RetailStoreDAO {
	
	public void addProduct(Product product) throws RetailStoreDAOException {
		// 1. Open the file to which the data to be written.
		// 2. Add the electronic product to the file.
	}
	
	public List<String[]> getAllProducts() throws RetailStoreDAOException {
		// 1. Open the file from which data needed to be retrieved.
		// 2. Store the data in the format of List<String[]> and return it.
		return null;
	}
	
	public List<String[]> getAllProductsByCategory(String category) throws RetailStoreDAOException{
		// 1. Open the file from which data needed to be retrieved.
		// 3. Choose the products that are related to the specified category.
		// 2. Store the data in the format of List<String[]> and return it.
		return null;
	}
}
