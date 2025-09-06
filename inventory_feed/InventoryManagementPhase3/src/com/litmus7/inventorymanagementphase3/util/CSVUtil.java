package com.litmus7.inventorymanagementphase3.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.inventorymanagementphase3.exception.CSVUtilException;

public class CSVUtil {
	
	public static List<String[]> extractDataFromCSV(String filePath) throws CSVUtilException{
		
		String line;
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br =  new BufferedReader(new FileReader(filePath))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] celldata = line.split(",");
                
                data.add(celldata);
            }
            
            if(data.isEmpty()) {
            	throw new CSVUtilException(202);
            }
            
            return data;
        } catch (IOException e) {
            throw new CSVUtilException(201, e);
        }
		
	}
	
}
