package com.litmus7.inventorymanagementphase3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventorymanagementphase3.constants.SQLConstants;
import com.litmus7.inventorymanagementphase3.dto.Product;
import com.litmus7.inventorymanagementphase3.exception.InventoryDaoException;
import com.litmus7.inventorymanagementphase3.util.DatabaseConnectionUtil;

public class InventoryDao {
	
	private static final Logger logger = LogManager.getLogger(InventoryDao.class);
	
	public void insertInventory(List<Product> products) throws InventoryDaoException {
		
		logger.debug("Beginning insertInventory() with {} records.", products.size());
		
		try(Connection conn = DatabaseConnectionUtil.getConnection()){
			logger.info("Connection established succesfully.");
		
			conn.setAutoCommit(false);
			
			try(PreparedStatement ps = conn.prepareStatement(SQLConstants.INSERT_INVENTORY);) {	
				
				logger.info("Statement prepared for execution.");
				
				for(Product product : products) {
					ps.setInt(1, product.getId());
					ps.setString(2, product.getProductName() );
					ps.setInt(3, product.getQuantity());
					ps.setDouble(4, product.getPrice());
					ps.addBatch();
				}
				
				ps.executeBatch();
				logger.info("Successfully inserted {} records.", products.size());
				
			} catch(SQLException e) {
				logger.warn("Failed insertion due to error in record.", e);
				
				try { 	
					conn.rollback();
					logger.info("Transaction rollbacked due to error.");
				} catch(SQLException rb) {
					logger.error("Rollback failed due to some error.", rb);
	                throw new InventoryDaoException(105, rb);
				}
				
				throw new InventoryDaoException(100, e);
			}
			
			
			logger.info("Transaction committed after successful insertion of records.");
			conn.commit();
			
		} catch(SQLException e) {
			logger.error("Error during database connecntion.", e);
			throw new InventoryDaoException(106, e);
		}
	}
}
