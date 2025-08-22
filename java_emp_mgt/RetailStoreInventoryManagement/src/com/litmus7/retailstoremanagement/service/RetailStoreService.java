package com.litmus7.retailstoremanagement.service;

import java.util.Comparator;
import java.util.List;

import com.litmus7.retailstoremanagement.dao.RetailStoreDAO;
import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.exception.RetailStoreDAOException;
import com.litmus7.retailstoremanagement.exception.RetailStoreServiceException;

public class RetailStoreService {
	
	RetailStoreDAO retailDao;
	private List<Product> products;
	
	public Product convertToProduct(String[] product) throws RetailStoreServiceException {
		return null;
	}
	
	public boolean importProduct(Product product) throws RetailStoreServiceException {
		try {
			retailDao.addProduct(product);
			return true;
		} catch (RetailStoreDAOException e) {
			throw new RetailStoreServiceException("",e);
		} catch (Exception e) {
			throw new RetailStoreServiceException("",e);
		}
	}
	
	public List<Product> exportAllProducts() throws RetailStoreServiceException {
		try {
			List<String[]> rawProducts = retailDao.getAllProducts();
			products = null;
			
			for(String[] rawProduct : rawProducts) {
				products.add(this.convertToProduct(rawProduct));
			}
			return products;
			
		} catch (RetailStoreDAOException e) {
			throw new RetailStoreServiceException("",e);
		} catch (Exception e) {
			throw new RetailStoreServiceException("",e);
		}
	}
	
	public List<Product> exportAllProductsByCategory(String category) throws RetailStoreServiceException {
		// 1. Call the method getAllProductsByCategory() from RetailStoreDAO and return the list of products under the category.
		
		try {
			List<String[]> rawProducts = retailDao.getAllProductsByCategory(category);
			products = null;
			
			for(String[] rawProduct : rawProducts) {
				products.add(this.convertToProduct(rawProduct));
			}
			return products;
			
		} catch (RetailStoreDAOException e) {
			throw new RetailStoreServiceException("",e);
		} catch (Exception e) {
			throw new RetailStoreServiceException("",e);
		}
	}
	
	public List<Product> exportAndSortProducts(Comparator<Product> comparator) throws RetailStoreServiceException {
		try {
			List<String[]> rawProducts = retailDao.getAllProducts();
			products = null;
			
			for(String[] rawProduct : rawProducts) {
				products.add(this.convertToProduct(rawProduct));
			}
			
			products.sort(comparator);
			
			return products;
			
		} catch (RetailStoreDAOException e) {
			throw new RetailStoreServiceException("",e);
		} catch (Exception e) {
			throw new RetailStoreServiceException("",e);
		}
	}
}
