/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.controller.OperationFailedException;
import se.kth.iv1350.processSale.integration.IntegrationCreator;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.ItemNotFoundException;
import se.kth.iv1350.processSale.integration.Printer;

/**
 *
 * @author Ali Sahibi
 */
public class ErrorMessageHandlerTest {
    private IntegrationCreator integrations = new IntegrationCreator();
    private Printer printer = new Printer();
    private Controller contr = new Controller (integrations, printer);
    private ErrorMessageHandler errorMsg = new ErrorMessageHandler ();

    @BeforeEach
    public void setUp() {
        contr.newSale();
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void doesErrorMessageHandlerDisplayError (){
        boolean condition;
        
        ByteArrayOutputStream streamToString = new ByteArrayOutputStream();
        PrintStream original = System.out;
        PrintStream alternateStream = new PrintStream (streamToString);
        System.setOut(alternateStream);
        String theErrorMsg = "An arbitrary error message";
        
        try{
            String invalidItemIdentifier = "INVALIDIDENTIFIER000";
            ItemDTO searchedInvalidItem = new ItemDTO (invalidItemIdentifier);
            contr.searchItem (searchedInvalidItem);
            System.out.println ("Total cost: " + contr.getTotalPrice() + "\n");
        } catch (ItemNotFoundException ItemExc){
            errorMsg.displayError(theErrorMsg);
            condition = streamToString.toString().contains(theErrorMsg);
            assertTrue (condition, "ErrorMessageHandler printed the wrong error"
                    + "message");
        } catch (OperationFailedException OPexc){
            
        }
        System.out.flush();
        System.setOut (original);
    }
}
