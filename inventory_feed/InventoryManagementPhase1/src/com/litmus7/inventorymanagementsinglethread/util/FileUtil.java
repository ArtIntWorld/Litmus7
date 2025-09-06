package com.litmus7.inventorymanagementsinglethread.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventorymanagementsinglethread.exception.InventoryException;

public class FileUtil {
	
	private static final Logger logger = LogManager.getLogger(FileUtil.class);
	
	public static List<String> getAllCSVFiles(String folderPath) throws InventoryException{

		Path directoryPath = Paths.get(folderPath);
		
	    try {
	    	
	    	if (!Files.exists(directoryPath)) {
	    		logger.error("The folder path [{}] doesn't exist.", directoryPath);
	    		throw new InventoryException(202); 
	    	}

            if (!Files.isDirectory(directoryPath)) { 
            	logger.error("The folder path [{}] is not a directory.", directoryPath);
            	throw new InventoryException(203);
            }
	    	
	        List<String> csvPaths = Files.walk(directoryPath) 
	                                    .filter(Files::isRegularFile) 
	                                    .filter(path -> path.toString().toLowerCase().endsWith(".csv"))
	                                    .map(Path::toAbsolutePath) 
	                                    .map(Path::toString)
	                                    .collect(Collectors.toList());

	        if (csvPaths.isEmpty()) {
	            logger.warn("No CSV files found in directory [{}].", directoryPath);
	            throw new InventoryException(204);
	        } else {
	            logger.info("Found {} CSV file(s) in directory [{}].", csvPaths.size(), directoryPath);
	        }
	        
	        return csvPaths;

	    } catch (IOException e) {
	    	logger.error("Failed to read files from directory [{}].", directoryPath);
	        throw new InventoryException(201, e);
	    }

	}
	
	public static void moveFile(String source, String target) throws InventoryException {
		
		try {
			
			Path sourcePath = Paths.get(source);
			Path destinationPath = Paths.get(target).resolve(sourcePath.getFileName());
			
			if (!Files.exists(sourcePath)) {
				logger.error("The file path [{}] is not found.", sourcePath);
	            throw new InventoryException(601);
	        }

	        if (!Files.isRegularFile(sourcePath)) {
	        	logger.error("The file path [{}] is invalid.", sourcePath);
	            throw new InventoryException(602);
	        }
			
			Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
			
			logger.info("Moved file from [{}] to [{}].", source, target);
			
		} catch(IOException e) {
			logger.error("Error while moving the file.", e);
			throw new InventoryException(603, e);
		}
	}
	
}


