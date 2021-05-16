package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.ExternalAccounting;
import se.kth.iv1350.processSale.integration.ExternalInventory;
import se.kth.iv1350.processSale.integration.IntegrationCreator;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.util.Amount;

/**
 * This class deals with the payment process of the sale
 * 
 * @author Ali Sahibi
 */
public class Payment {
    private Amount change;
    private SaleInformation saleInformation;
    private final Receipt receipt;
    private IntegrationCreator integrations;
    private CashRegister cashRegister;
    
    /**
     * Constructor for the class Payment
     */
    public Payment (){
        this.receipt = new Receipt();
    }
    
    /**
     * Getter method for the amount of change during the sale process
     * 
     * @return the change
     */
    Amount getChange (){
        return  change;
    }
    
    /**
     * This method updates all the external Systems
     * 
     */
    void updateExternalSystems (){
        ExternalInventory inventory = integrations.getExternalInventory();
        ExternalAccounting accounting = integrations.getExternalAccounting();
        inventory.updateInventory(saleInformation);
        accounting.updateAccounting(saleInformation);
        cashRegister.addToBalance(saleInformation.getPaymentAmount());
        calculateChange();  
    }
    
    /**
     * This method updates the receipt based on the data in this payment object
     */
    void updateReceipt (){
        receipt.updateReceipt(this);
    }
    
    /**
     * Getter method for saleInformation
     * 
     * @return saleInformation 
     */
    SaleInformation getSaleInformation (){
        return this.saleInformation;
    }
    
    /**
     * This method retrieves an IntegrationCreator to give access to classes
     * in the integration layer
     * 
     * @param integrations gives access to integration classes
     * @param cashRegister gives access to the cash register
     * @param printer gives receipt object access to it
     */
    void givePaymentParts (IntegrationCreator integrations,
    CashRegister cashRegister, Printer printer){
        this.integrations = integrations;
        this.cashRegister = cashRegister;
        receipt.accessPrinter(printer);
    }
    
    /**
     * This method gives the payment object access to the saleInformation object
     * 
     * @param saleInformation 
     */
    void giveSaleInformation (SaleInformation saleInformation){
        this.saleInformation = saleInformation;
    }
    
    /**
     * Calculates how much change to give back to costumer
     */
    private void calculateChange (){
        this.change = saleInformation.calculateChange();
    }    
}
