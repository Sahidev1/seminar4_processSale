
package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.util.Amount;
import static se.kth.iv1350.processSale.util.DatabaseCrasher.CRASH_DATABASE_IMMEDIATELY;
import se.kth.iv1350.processSale.util.Percentage;

/**
 *
 * @author Ali Sahibi
 */
public class ExceptionTestItemRegistry {
    private ItemRegistry itemRegistry = new ItemRegistry();
    private ItemDTO validItemDTO;
    private ItemDTO invalidItemDTO;
    
    @BeforeEach
    public void setUp() {
        validItemDTO = new ItemDTO ("AX356235", "Apple", new Amount (22), new Percentage (6));
        invalidItemDTO = new ItemDTO ("INVALID000", "INVALID", new Amount(0), new Percentage (0));
    }
    
    @AfterEach
    public void tearDown() {
        validItemDTO = null;
        invalidItemDTO = null;
    }

    @Test
    public void testIfValidItemThrowsException (){
        try {
            Item foundItem = itemRegistry.searchItem(validItemDTO);
        } catch (ItemNotFoundException itemExc){
            fail("A valid itemDTO caused searchItem method to throw a "
                    + "ItemNotFoundException");
        }    
    }
    
    @Test
    public void testIfInvalidItemThrowsException (){
        try {
            Item foundItem = itemRegistry.searchItem(invalidItemDTO);
            fail("An invalid itemDTO in method searchItem returned an item which"
                    + " does not exist in the database");
        } catch (ItemNotFoundException itemExc){
            
        }        
    }
    
    @Test
    public void testIfItemNotFoundExceptionHasRightMsg (){
        boolean condition;
        String exceptionMsg;
        String expectedMsg = "Could not find Item with this ItemIdentifier: " +
                invalidItemDTO.getItemIdentifier();
        try {
            Item foundItem = itemRegistry.searchItem(invalidItemDTO);
        } catch (ItemNotFoundException itemExc){
            exceptionMsg = itemExc.getMessage();
            condition = exceptionMsg.equals(expectedMsg);
            assertTrue(condition, "exception contains wrong message");
        }        
    }
    
    @Test
    public void testIfSuccesfullDatabaseConnectionThrowsException (){
        try {
            Item foundItem = itemRegistry.searchItem(validItemDTO);
        } catch (ItemNotFoundException itemExc){
            fail("Exception thrown despite successful databse connection and "
                    + "unexpected type of exception thrown");
        }
        catch (DatabaseConnectionException DBexc){
            fail("Succesfull connection caused an exception");
        }
    }
    
    @Test
    public void testIfUnsuccessfulDatabaseConnectionThrowsException (){
        ItemDTO databaseCrasher = new ItemDTO(CRASH_DATABASE_IMMEDIATELY);
        try {
            Item foundItem = itemRegistry.searchItem(databaseCrasher);
            fail("The database has crashed, searchItem method should not operate");
        } catch (ItemNotFoundException itemExc){
            fail("Wrong excpetion thrown");
        }
        catch (DatabaseConnectionException DBexc){
            
        }
    }

    @Test
    public void testIfDatabaseConnectionExceptionHasRightMsg (){
        boolean condition;
        String exceptionMsg;
        String expectedMsg = "Failed to establish a connection to the database";
        ItemDTO databaseCrasher = new ItemDTO (CRASH_DATABASE_IMMEDIATELY);
        try {
            Item foundItem = itemRegistry.searchItem(databaseCrasher);
        } catch (ItemNotFoundException itemExc){
            fail("Wrong expetion thrown");
        }
        catch (DatabaseConnectionException DBexc){
            exceptionMsg = DBexc.getMessage();
            condition = exceptionMsg.equals(expectedMsg);
            assertTrue (condition, "The exception returned the wrong message");
        }
    }    
}
