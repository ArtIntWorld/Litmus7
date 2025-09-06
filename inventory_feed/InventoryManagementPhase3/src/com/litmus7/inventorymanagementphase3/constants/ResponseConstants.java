package com.litmus7.inventorymanagementphase3.constants;

public class ResponseConstants {
	public static final Integer OK = 200;
	public static final Integer BAD_REQUEST = 400;
	
	public static final String NULL_FOLDER_MESSAGE = "The folder path is empty.";
	
	public static final String INSERTED_MESSAGE = "Successfully processed whole files.";
	public static final String PARTIAL_INSERTED_MESSAGE = "Successfully processed some of the files.";
	public static final String NO_INSERTED_MESSAGE = "Whole file was not processed due to error.";
	public static final String INTERRUPTED_MESSAGE = "The process haas been interrrupted.";
	public static final String TIMOUT_MESSAGE = "The process has been terminated due to timeout.";
}
