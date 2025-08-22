package com.litmus7.retailstoremanagement.controller;

import java.util.Comparator;
import java.util.List;

import com.litmus7.retailstoremanagement.constant.ApplicationStatusCode;
import com.litmus7.retailstoremanagement.dto.Product;
import com.litmus7.retailstoremanagement.dto.Response;
import com.litmus7.retailstoremanagement.exception.RetailStoreServiceException;
import com.litmus7.retailstoremanagement.service.RetailStoreService;

public class RetailStoreController {
	
	RetailStoreService retailService;
	
	public Response<Boolean> importProductDetails(String[] rawProduct){
		
		try {
			Product product = retailService.convertToProduct(rawProduct);
			boolean importStatus = retailService.importProduct(product);
			
			if (importStatus) {
	            return new Response<>(ApplicationStatusCode.SUCCESS, ApplicationStatusCode.IMPORT_SUCCESS, true);
	        } else {
	            return new Response<>(ApplicationStatusCode.INTERNAL_SERVER_ERROR, ApplicationStatusCode.IMPORT_FAILURE, false);
	        }
			
		} catch (RetailStoreServiceException e) {
	        return new Response<>(ApplicationStatusCode.BAD_REQUEST, ApplicationStatusCode.IMPORT_FAILURE);
	    } catch (Exception e) {
	        return new Response<>(ApplicationStatusCode.INTERNAL_SERVER_ERROR, ApplicationStatusCode.FILE_WRITE_FAILURE);
	    }
	}
	
	public Response<List<Product>> exportAllProductsDetails(){
		try {
			List<Product> products = retailService.exportAllProducts();
			if (products != null && !products.isEmpty()) {
	            return new Response<>(ApplicationStatusCode.SUCCESS, ApplicationStatusCode.EXPORT_SUCCESS, products);
	        } else {
	            return new Response<>(ApplicationStatusCode.NOT_FOUND, ApplicationStatusCode.EXPORT_FAILURE);
	        }
			
		} catch (RetailStoreServiceException e) {
			return new Response<>(ApplicationStatusCode.BAD_REQUEST, ApplicationStatusCode.EXPORT_FAILURE);
		} catch (Exception e) {
			return new Response<>(ApplicationStatusCode.INTERNAL_SERVER_ERROR, ApplicationStatusCode.FILE_READ_FAILURE);
		}
	}
	
	public Response<List<Product>> exportAllProductsDetailsByCategory(String category){
		try {
			List<Product> products = retailService.exportAllProductsByCategory(category);
			if (products != null && !products.isEmpty()) {
	            return new Response<>(ApplicationStatusCode.SUCCESS, ApplicationStatusCode.EXPORT_SUCCESS, products);
	        } else {
	            return new Response<>(ApplicationStatusCode.NOT_FOUND, ApplicationStatusCode.EXPORT_FAILURE);
	        }
			
		} catch (RetailStoreServiceException e) {
			return new Response<>(ApplicationStatusCode.BAD_REQUEST, ApplicationStatusCode.EXPORT_FAILURE);
		} catch (Exception e) {
			return new Response<>(ApplicationStatusCode.INTERNAL_SERVER_ERROR, ApplicationStatusCode.FILE_READ_FAILURE);
		}
	}
	
	public Response<List<Product>> exportSortedProductsDetails(Comparator<Product> comparator){
		try {
			List<Product> products = retailService.exportAndSortProducts(comparator);
			if (products != null && !products.isEmpty()) {
	            return new Response<>(ApplicationStatusCode.SUCCESS, ApplicationStatusCode.EXPORT_SUCCESS, products);
	        } else {
	            return new Response<>(ApplicationStatusCode.NOT_FOUND, ApplicationStatusCode.EXPORT_FAILURE);
	        }
			
		} catch (RetailStoreServiceException e) {
			return new Response<>(ApplicationStatusCode.BAD_REQUEST, ApplicationStatusCode.EXPORT_FAILURE);
		} catch (Exception e) {
			return new Response<>(ApplicationStatusCode.INTERNAL_SERVER_ERROR, ApplicationStatusCode.FILE_READ_FAILURE);
		}
	}
}
