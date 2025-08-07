package com.litmus7.employeemanager.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
	
	public static List<String[]> readCSV(String filename) throws IOException{
        String line;
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br =  new BufferedReader(new FileReader(filename))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] celldata = line.split(",");
                
                data.add(celldata);
            }
            return data;
        } catch (IOException e) {
            throw new IOException("Failed to read CSV file " + filename + " : " + e.getMessage(), e);
        }
    }
}
