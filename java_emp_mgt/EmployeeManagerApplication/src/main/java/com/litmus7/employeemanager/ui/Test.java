package com.litmus7.employeemanager.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test{
    private static final Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        logger.trace("TRACE level message");
        logger.debug("DEBUG level message");
        logger.info("INFO level message");
        logger.warn("WARN level message");
        logger.error("ERROR level message");
        logger.fatal("FATAL level message");
        System.out.print(8);
    }
}
