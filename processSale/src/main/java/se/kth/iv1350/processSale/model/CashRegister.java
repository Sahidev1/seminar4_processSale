package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;

/**
 * This class holds information about the cash registers balance during the 
 * sale
 *
 * @author Ali Sahibi
 */
public class CashRegister {
    private Amount balance;
    
    /** 
     * Constructor for the class CashRegister
     */
    public CashRegister (){
        retrieveBalance ();
    }
    
    /** 
     * This method adds an amount to the current balance
     * 
     * @param amount to add
     */
    void addToBalance (Amount amount){
        balance = balance.add(amount);
    }
    
    /**
     * This method retrieves the balance of the cashRegister from
     * cashRegister memory/network holding that data
     * 
     * In this case we assume the cashRegister is holding this amount
     */
    private void retrieveBalance (){
        this.balance = new Amount (2511);
    }
}
