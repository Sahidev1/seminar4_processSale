package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.CostumerDTO;
import se.kth.iv1350.processSale.integration.DatabaseConnectionException;
import se.kth.iv1350.processSale.integration.IntegrationCreator;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.ItemNotFoundException;
import se.kth.iv1350.processSale.integration.ItemRegistry;
import se.kth.iv1350.processSale.model.CashRegister;
import se.kth.iv1350.processSale.model.Discount;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

/**
 * The controller handles calls from the view to the other layers
 * 
 * @author Ali Sahibi
 */
public class Controller {
    private final IntegrationCreator integrations;
    private final Printer printer;
    private final CashRegister cashRegister;
    private Sale sale;
    private Discount discount;

    
    /** 
     * Constructor for the Controller
     * 
     * @param integrations is an IntegrationsCreator object
     * @param printer is a Printer object
     */
    public Controller (IntegrationCreator integrations, Printer printer){
        this.integrations = integrations;
        this.printer = printer;  
        this.cashRegister = new CashRegister();
    }
    
    /** 
     * This method starts a new sale
     * It also sends references of integrations, cashRegister, printer 
     * to the payment object
     * 
     * @return a reference to the sale in controller
     */
    public void newSale(){
        this.sale = new Sale();
        sale.givePaymentParts (integrations, cashRegister, printer);
    }
    
    /** 
     * This method searches for an item in the the database
     * and also holds the quantity of the searched item,
     * if the item has already been added it returns that item from the list of 
     * items in sale
     * 
     * @param searchedItem an itemDTO object
     * @param quantity quantity of the itemDTO type searched
     * @throws ItemNotFoundException when an item couldn't be found
     * @throws OperationFailedException when the operation could not be performed
     */
    public void searchItem (ItemDTO searchedItem, int quantity) throws 
            ItemNotFoundException, OperationFailedException{
        ItemRegistry itemReg = integrations.getItemRegistry ();
        
        try {
            Item foundItem = itemReg.searchItem(searchedItem, quantity);
            sale.addItemToSale(foundItem);


            if (sale.hasItemAlreadyBeenAdded(foundItem)){
                System.out.println(sale.getItemFromTheList(foundItem));
                return;
            }
        
            System.out.println (foundItem);
        } catch (DatabaseConnectionException DBexc){
            throw new OperationFailedException ("Could not search for "
                    + " item", DBexc);
        }
    }
    
    /** 
     * This method searches for an item in the database
     * if the item has already been added it returns that item from the list of 
     * items in sale  
     * 
     * @param searchedItem an itemDTO object
     * @throws ItemNotFoundException when an item couldn't be found
     * @throws OperationFailedException when the operation could not be performed
     */
    public void searchItem (ItemDTO searchedItem) throws ItemNotFoundException, OperationFailedException{
        int quantityOfOne = 1;
        searchItem (searchedItem, quantityOfOne);
    }    
    
    /** 
     * This method is used when there is a discount request
     * A new discount object is created that gets a reference to integrations
     * 
     * @param costumerDTO is a data of a costumers personal details
     * @return Amount the new price after the discount request
     */
    public Amount requestDiscount (CostumerDTO costumerDTO){
        this.discount = new Discount ();
        discount.giveAccessToCostumerRegistry (integrations);
        Amount updatedPrice = sale.discountRequest(costumerDTO, this.discount);
        return updatedPrice;
    }
    
    /** 
     * This method retrieves the total price of the Sale
     * 
     * @return total price of sale
     */
    public Amount getTotalPrice (){
        return sale.getTotalPrice();
    }
    
    /** 
     * This method is called to deal with the payment of the sale
     * 
     * @param paymentAmount the amount to pay for the sale
     */
    public void makePayment (Amount paymentAmount){
        sale.makePayment(paymentAmount);
    }
}


