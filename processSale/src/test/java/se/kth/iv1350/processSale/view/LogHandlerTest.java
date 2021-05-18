
package se.kth.iv1350.processSale.view;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
import static se.kth.iv1350.processSale.util.DatabaseCrasher.CRASH_DATABASE_IMMEDIATELY;
import se.kth.iv1350.processSale.util.LogHandler;

/**
 *
 * @author Ali Sahibi
 */
public class LogHandlerTest {
    private IntegrationCreator integrations = new IntegrationCreator();
    private Printer printer = new Printer();
    private Controller contr = new Controller (integrations, printer);
    private LogHandler logg;
    
    @BeforeEach
    public void setUp() {
        contr.newSale();
        logg = LogHandler.getLogHandler();
    }
    
    @AfterEach
    public void tearDown() {
        logg = null;
    }
    
    @Test
    public void doesLogHandlerLogCorrectly (){ 
        boolean condition;
        StringBuilder logContent = new StringBuilder();
        String expectedLog;
        
        ByteArrayOutputStream logShouldContain = new ByteArrayOutputStream();
        PrintStream original = System.out;
        PrintStream alternateStream = new PrintStream (logShouldContain);
        
        try{
            ItemDTO databaseCrasher = new ItemDTO (CRASH_DATABASE_IMMEDIATELY);
            contr.searchItem (databaseCrasher);
        } catch (ItemNotFoundException ItemExc){

        } catch (OperationFailedException OPexc){
            logg.writeToLog(OPexc);
            OPexc.printStackTrace(alternateStream);
        } 
        
        expectedLog = logShouldContain.toString();
        
        try {
            FileReader loggSniffer = new FileReader ("logged.txt"); 
            int data = loggSniffer.read();
            while (data != -1){
                logContent.append((char) data);
                data = loggSniffer.read();
            }

            condition = logContent.toString().contains(expectedLog);
            assertTrue (condition, "Log contains wrong message");
        } catch (IOException IOexc){
            IOexc.printStackTrace();
        }
    }
}
