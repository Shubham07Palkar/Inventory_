package org.example.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.class.getName());
    static{
        try{
            FileHandler fileHandler = new FileHandler("C:\\Users\\ShubhamPalkar\\OneDrive - Atyeti Inc\\Documents\\SpringBoot-Dev\\spring data jpa and hibernate\\InventoryManagement\\src\\main\\java\\org\\logs.log",true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Failed to Initialized logger: "+e.getMessage());
        }

    }
    public static void logInfo(String message){
        logger.info(message);
    }

    public static void logWarn(String message, int id){
        logger.warning(message);
    }
}
