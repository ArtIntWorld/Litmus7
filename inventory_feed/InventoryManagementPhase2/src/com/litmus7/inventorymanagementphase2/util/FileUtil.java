package com.litmus7.inventorymanagementphase2.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.inventorymanagementphase2.exception.FileUtilException;

public class FileUtil {
	
	private static final Logger logger = LogManager.getLogger(FileUtil.class);
	
	public static List<String> getAllCSVFiles(String folderPath) throws FileUtilException{

		if(folderPath == null || folderPath.isBlank()) {
			logger.error("The folder path is empty");
			throw new FileUtilException(107);
		}
		
		Path directoryPath = Paths.get(folderPath);
		
	    try {
	    	
	    	if (!Files.exists(directoryPath)) {
	    		logger.error("The folder path [{}] doesn't exist.", directoryPath);
	    		throw new FileUtilException(202); 
	    	}

            if (!Files.isDirectory(directoryPath)) { 
            	logger.error("The folder path [{}] is not a directory.", directoryPath);
            	throw new FileUtilException(203);
            }
	    	
	        List<String> csvPaths = Files.walk(directoryPath) 
	                                    .filter(Files::isRegularFile) 
	                                    .filter(path -> path.toString().toLowerCase().endsWith(".csv"))
	                                    .map(Path::toAbsolutePath) 
	                                    .map(Path::toString)
	                                    .collect(Collectors.toList());

	        if (csvPaths.isEmpty()) {
	            logger.warn("No CSV files found in directory [{}].", directoryPath);
	            throw new FileUtilException(204);
	        } else {
	            logger.info("Found {} CSV file(s) in directory [{}].", csvPaths.size(), directoryPath);
	        }
	        
	        return csvPaths;

	    } catch (IOException e) {
	    	logger.error("Failed to read files from directory [{}].", directoryPath);
	        throw new FileUtilException(201, e);
	    }

	}
	
	public static void moveFile(String source, String target) throws FileUtilException {
		
		try {
			
			Path sourcePath = Paths.get(source);
			Path targetPath = Paths.get(target);
			
			if (!Files.exists(sourcePath)) {
				logger.error("The source path [{}] is not found.", sourcePath);
	            throw new FileUtilException(601);
	        }
	        if (!Files.isRegularFile(sourcePath)) {
	        	logger.error("The source path [{}] is invalid.", sourcePath);
	            throw new FileUtilException(602);
	        }
	        
	        if (!Files.exists(targetPath)) {
	        	logger.error("Folder path not found: " + targetPath);
	            throw new FileUtilException(101);
	        }
	        if (!Files.isDirectory(targetPath)) {
	        	logger.error("The given path is not a directory: " + targetPath);
	            throw new FileUtilException(102);
	        }
	        if (!Files.isWritable(targetPath)) {
	        	logger.error("Invalid file path (no write permission): " + targetPath);
	            throw new FileUtilException(602);
	        }
	        
	        Path finalPath = targetPath.resolve(sourcePath.getFileName());
			
			Files.move(sourcePath, finalPath, StandardCopyOption.REPLACE_EXISTING);
			
			logger.info("Moved file from [{}] to [{}].", source, target);
			
		} catch(IOException e) {
			logger.error("Error while moving the file.", e);
			throw new FileUtilException(603, e);
		}
	}
}


