
package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.integration.DatabaseConnectionException;
import se.kth.iv1350.processSale.integration.IntegrationCreator;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.ItemNotFoundException;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.util.Amount;
import static se.kth.iv1350.processSale.util.DatabaseCrasher.CRASH_DATABASE_IMMEDIATELY;
import se.kth.iv1350.processSale.util.Percentage;

/**
 *
 * @author Ali Sahibi
 */
public class ControllerExceptionTest {
    private IntegrationCreator integrations = new IntegrationCreator();
    private Printer printer = new Printer();
    private Controller contr = new Controller (integrations, printer);
    private ItemDTO validItemDTO;
    private ItemDTO databaseCrasher;
    
    @BeforeEach
    public void setUp() {
        contr.newSale();
        validItemDTO = new ItemDTO ("AX356235", "Apple", new Amount (22), new Percentage (6));  
        databaseCrasher = new ItemDTO (CRASH_DATABASE_IMMEDIATELY);
    }
    
    @AfterEach
    public void tearDown() {
        validItemDTO = null;
        databaseCrasher = null;
    }
    
    @Test
    public void testIfSuccessfullDatabaseConnectionThrowsException (){
        try{
            contr.searchItem(validItemDTO);
        } catch (ItemNotFoundException itemEXC){
            fail("Exception thrown despite successful databse connection and "
                    + "unexpected type of exception thrown");
        }
        catch (OperationFailedException OPexc){
            fail("Exception was thrown despite successfull database connection");
        }
    }
    
    @Test
    public void testIfUnsuccessfullDatabaseConnectionThrowsException(){
        try {
            contr.searchItem (databaseCrasher);
            fail("Unsuccessfull database connection did not throw an exception");
        } catch (ItemNotFoundException itemEXC){
            fail("Wrong exception thrown");
        }
        catch (OperationFailedException OPexc){
            
        }
    }
    
    @Test
    public void testIfOperationFailedExceptionHasCorrectMsg (){
        boolean condition;
        String exceptionMsg;
        String expectedMsg = "Could not search for item";
        try {
            contr.searchItem(databaseCrasher);
        } catch (ItemNotFoundException itemEXC){
            fail("Wrong exception thrown");
        } catch (OperationFailedException OPexc){
            exceptionMsg = OPexc.getMessage();
            condition = exceptionMsg.equals(expectedMsg);
            assertTrue(condition, "The exception has wrong message");
        }
    }
    
    @Test
    public void testIfOperationFailedExceptionCauseIsCorrect (){
        boolean condition;
        String exceptionCauseMsg;
        String expectedMsg = "Failed to establish a connection to the database";
        try {
            contr.searchItem(databaseCrasher);
        } catch (ItemNotFoundException itemEXC){
            fail("Wrong exception thrown");
        } catch (OperationFailedException OPexc){
            exceptionCauseMsg = OPexc.getCause().getMessage();
            condition = exceptionCauseMsg.equals(expectedMsg);
            assertTrue(condition, "The cause is not correct");
        }
    }
    
    @Test
    public void testIfRightExceptionIsThrown (){
        try {
            contr.searchItem(databaseCrasher);
        } catch (ItemNotFoundException itemEXC){
            fail ("Wrong exception thrown");
        } catch (DatabaseConnectionException DBexc){
            fail("Controller is not supposed to throw a DatabaseConnectionException");
        } catch (OperationFailedException OPexc){

        }
    }
}
