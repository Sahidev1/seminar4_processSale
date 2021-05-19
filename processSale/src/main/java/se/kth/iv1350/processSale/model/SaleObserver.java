package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;

/**
 * This is an interface for a observer
 *
 * @author Ali Sahibi
 */
public interface SaleObserver {
    
    /**
     * This method updates the total revenue for every sale while the program
     * is running
     * 
     * @param saleRevenue the revenue from the latest sale
     */
    void updateTotalRevenue (Amount saleRevenue);      
}
