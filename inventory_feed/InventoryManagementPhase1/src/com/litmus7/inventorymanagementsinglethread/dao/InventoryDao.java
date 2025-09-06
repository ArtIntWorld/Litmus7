package com.litmus7.inventorymanagementsinglethread.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.litmus7.inventorymanagementsinglethread.constants.SQLConstants;
import com.litmus7.inventorymanagementsinglethread.dto.Inventory;
import com.litmus7.inventorymanagementsinglethread.exception.InventoryException;
import com.litmus7.inventorymanagementsinglethread.util.DatabaseConnectionUtil;

public class InventoryDao {
	
	public boolean insertInventory(List<Inventory> inventories) throws InventoryException {
		
		try(Connection conn = DatabaseConnectionUtil.getConnection()){
		
			conn.setAutoCommit(false);
			
			try(PreparedStatement ps = conn.prepareStatement(SQLConstants.INSERT_INVENTORY);) {	
				
				for(Inventory inventory : inventories) {
					ps.setInt(1, inventory.getId());
					ps.setString(2, inventory.getProductName() );
					ps.setInt(3, inventory.getQuantity());
					ps.setDouble(4, inventory.getPrice());
					ps.addBatch();
				}
				
				ps.executeBatch();				
				
			} catch(SQLException e) {
				conn.rollback();
				return false;
			}
			
			conn.commit();
			return true;
			
		} catch(SQLException e) {
			throw new InventoryException(100, e);
		}
	}

}
