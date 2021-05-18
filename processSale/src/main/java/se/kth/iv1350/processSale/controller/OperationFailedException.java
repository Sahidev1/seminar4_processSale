
package se.kth.iv1350.processSale.controller;

/**
 * This class is a checked exception that is thrown when a operation fails
 *
 * @author Ali Sahibi
 */
public class OperationFailedException extends Exception {

    /**
     * Constructor for the exception class
     * 
     * @param msg to be displayed when the exception is thrown
     * @param cause the original cause of the exception
     */
    public OperationFailedException(String msg, Exception cause) {
        super(msg, cause);
    }
}
