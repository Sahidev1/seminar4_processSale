
package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.model.SaleObserver;
import se.kth.iv1350.processSale.util.Amount;

/**
 * This class keeps track of the total revenue during the programs runtime
 *
 * @author Ali Sahibi
 */
public class TotalRevenueView implements SaleObserver {
    private Amount totalRevenue;
    
    /**
     * Constructor for the class
     */
    public TotalRevenueView (){
        totalRevenue = new Amount(0);
    }
    
    /**
     * This method updates the total revenue and prints it
     * 
     * @param revenue the revenue to add to the total
     */
    @Override
    public void updateTotalRevenue (Amount revenue){
        totalRevenue = totalRevenue.add(revenue);
        printTotalRevenue();
    }
    
    private void printTotalRevenue(){
        System.out.println ("#############################################");
        System.out.println("Total revenue: " + totalRevenue.toString());
        System.out.println ("#############################################");
    }
}
