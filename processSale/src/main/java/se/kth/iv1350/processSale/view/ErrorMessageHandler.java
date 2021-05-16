
package se.kth.iv1350.processSale.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class displays error messages to the user of the program
 *
 * @author Ali Sahibi
 */
public class ErrorMessageHandler {
    
    /**
     * Constructor for the class
     */
    public ErrorMessageHandler (){
        
    } 
    
    /**
     * This method displays the error message
     * 
     * @param msg the message to be displayed
     */
    void displayError (String msg){
        StringBuilder errorString = new StringBuilder();
        errorString.append(timeOfError()).append(" Error: ");
        errorString.append(msg);
        System.out.println(errorString);
    }
    
    private String timeOfError (){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(formattedTime);
    }
}
