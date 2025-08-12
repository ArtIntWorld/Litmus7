package com.litmus7.retailstoremanagement.dao;

import java.util.List;

import com.litmus7.retailstoremanagement.dto.Clothing;
import com.litmus7.retailstoremanagement.dto.Electronic;
import com.litmus7.retailstoremanagement.dto.Grocery;
import com.litmus7.retailstoremanagement.exception.RetailStoreDAOException;

public class RetailStoreDAO {
	
	public void addProduct(Electronic product) throws RetailStoreDAOException {
		// 1. Open the file to which the data to be written.
		// 2. Add the electronic product to the file.
	}
	public void addProduct(Clothing product) throws RetailStoreDAOException {
		// 1. Open the file to which the data to be written.
		// 2. Add the clothing product to the file.
	}
	public void addProduct(Grocery product) throws RetailStoreDAOException {
		// 1. Open the file to which the data to be written.
		// 2. Add the grocery product to the file.
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
