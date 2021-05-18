package se.kth.iv1350.processSale.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class handles the logging of exceptions
 * 
 * @author Ali Sahibi
 */
public class LogHandler {
    private static final String FILE = "logged.txt";
    private static final LogHandler logg = new LogHandler ();
    private PrintWriter logger;
    
    /**
     * Getter method for LogHandler
     * 
     * @return the logHandler object
     */
    public static LogHandler getLogHandler(){
        return logg;
    }
    
    private LogHandler (){
        try {
            logger = new PrintWriter(new FileWriter(FILE), true);
        } catch (IOException IOe){
            System.out.println("Failed to create logger");
            IOe.printStackTrace();
        }
    }
    
    /**
     * This method writes information about an exception to the log
     * 
     * @param exception the exception the information is written about
     */
    public void writeToLog (Exception exception){
        StringBuilder logString = new StringBuilder ();
        logString.append(timeOf());
        logString.append(" Exception: ");
        logString.append(exception.getMessage());
        logger.println(logString);
        exception.printStackTrace(logger);
    }
    
    private String timeOf (){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(formattedTime);
    }  
}
