package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.util.Amount;

/**
 *
 * @author Ali Sahibi
 */
public interface SaleObserver {
    
    void updateTotalRevenue (Amount paymentAmount);      
}
