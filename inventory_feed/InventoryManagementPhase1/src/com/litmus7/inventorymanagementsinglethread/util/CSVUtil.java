package com.litmus7.inventorymanagementsinglethread.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.inventorymanagementsinglethread.exception.InventoryException;

public class CSVUtil {
	
	public static List<String[]> extractDataFromCSV(String filePath) throws InventoryException{
		
		String line;
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br =  new BufferedReader(new FileReader(filePath))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] celldata = line.split(",");
                
                data.add(celldata);
            }
            return data;
        } catch (IOException e) {
            throw new InventoryException(201, e);
        }
		
	}
	
}
