package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.controller.OperationFailedException;
import se.kth.iv1350.processSale.integration.CostumerDTO;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.ItemNotFoundException;
import se.kth.iv1350.processSale.util.Amount;
import static se.kth.iv1350.processSale.util.DatabaseCrasher.CRASH_DATABASE_IMMEDIATELY;
import se.kth.iv1350.processSale.util.LogHandler;

/**
 *  View is the programs user interface
 * 
 * @author Ali Sahibi
 */
public class View {
    private Controller contr;
    private ErrorMessageHandler showErrorMsg = new ErrorMessageHandler ();
    private LogHandler logger;
    
    /** 
     * Constructor for the class View
     * 
     * @param contr Controller object used as argument 
     */
    public View(Controller contr){
        this.contr = contr;
        this.logger = LogHandler.getLogHandler();
    }
    
    public void hardCodedCalls (){
        contr.newSale();
        System.out.println ("newSale() method called" + "\n");
        
        final String ITEM_NOT_FOUND = "Item could not be found"
                + " in the item registry, Item is invalid";
        final String COULD_NOT_SEARCH = "Could not search for item";
        
        try {
            String itemIdentifier = "AX531319";
            ItemDTO searchedItem = new ItemDTO (itemIdentifier);
            contr.searchItem (searchedItem);
            System.out.println ("Total cost: " + contr.getTotalPrice() + "\n");
        } catch (ItemNotFoundException ItemExc){
            showErrorMsg.displayError(ITEM_NOT_FOUND);
        } catch (OperationFailedException OPexc){
            displayAndLog (COULD_NOT_SEARCH, OPexc);
        }
        
        try {
            String identifierOfAlreadySearchedItem = "AX531319";
            ItemDTO alreadySearchedItem = new ItemDTO (identifierOfAlreadySearchedItem);
            contr.searchItem(alreadySearchedItem);
            System.out.println ("Total cost: " + contr.getTotalPrice() + "\n");
        } catch (ItemNotFoundException ItemExc){
            showErrorMsg.displayError(ITEM_NOT_FOUND);
        } catch (OperationFailedException OPexc){
            displayAndLog (COULD_NOT_SEARCH, OPexc);
        }
        
        try{
            String itemIdentifierOfMultipleItems = "BX029510";
            int quantityOfItem = 7;
            ItemDTO searchMultipleOfItem = new ItemDTO (itemIdentifierOfMultipleItems);
            contr.searchItem(searchMultipleOfItem, quantityOfItem); 
            System.out.println("Total cost: " + contr.getTotalPrice() + "\n");
        } catch (ItemNotFoundException ItemExc){
            showErrorMsg.displayError(ITEM_NOT_FOUND);
        } catch (OperationFailedException OPexc){
            displayAndLog (COULD_NOT_SEARCH, OPexc);
        }
        
        try{
            String invalidItemIdentifier = "INVALIDIDENTIFIER000";
            ItemDTO searchedInvalidItem = new ItemDTO (invalidItemIdentifier);
            contr.searchItem (searchedInvalidItem);
            System.out.println ("Total cost: " + contr.getTotalPrice() + "\n");
        } catch (ItemNotFoundException ItemExc){
            showErrorMsg.displayError(ITEM_NOT_FOUND);
        } catch (OperationFailedException OPexc){
            displayAndLog (COULD_NOT_SEARCH, OPexc);
        }
        
        try{
            ItemDTO databaseCrasher = new ItemDTO (CRASH_DATABASE_IMMEDIATELY);
            contr.searchItem (databaseCrasher);
            System.out.println ("Total cost: " + contr.getTotalPrice() + "\n");
        } catch (ItemNotFoundException ItemExc){
            showErrorMsg.displayError(ITEM_NOT_FOUND);
        } catch (OperationFailedException OPexc){
            displayAndLog (COULD_NOT_SEARCH, OPexc);
        }
        
        CostumerDTO costumerRequestingDiscount = new CostumerDTO ("John Doe",
        "19880509", "DoeTown evergreenstreet 22", "2391000102");
        Amount updatedPrice = contr.requestDiscount (costumerRequestingDiscount);
        System.out.println ("Discountrequest was made");
        System.out.println ("Total cost: " + updatedPrice + "\n");
        
        Amount paymentFromCostumer = new Amount (600);
        contr.makePayment(paymentFromCostumer);
    }
    
    private void displayAndLog (String msg, Exception exc){
        showErrorMsg.displayError(msg);
        logger.writeToLog(exc);
    }
}
