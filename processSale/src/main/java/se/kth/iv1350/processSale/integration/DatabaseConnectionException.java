
package se.kth.iv1350.processSale.integration;

/**
 * This class is an unchecked exception that is thrown when there is a connection
 * issue to a database
 *
 * @author Ali Sahibi
 */
public class DatabaseConnectionException extends RuntimeException {
    /**
     * Constructor for the exception
     * 
     * @param message to be displayed when exception is thrown
     */
    public DatabaseConnectionException (String message){
        super(message);
    }
}
