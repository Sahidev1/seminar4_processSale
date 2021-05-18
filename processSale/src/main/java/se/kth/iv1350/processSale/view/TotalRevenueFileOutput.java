
package se.kth.iv1350.processSale.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import se.kth.iv1350.processSale.model.SaleObserver;
import se.kth.iv1350.processSale.util.Amount;

/**
 * This class logs the total revenue in a text file
 *
 * @author Ali Sahibi
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private Amount totalRevenue;
    private static final String FILEPATH = "TotalRevenue.txt";
    private PrintWriter fileWriter;
    
    /**
     * Constructor for the class
     */
    public TotalRevenueFileOutput (){
        totalRevenue = new Amount (0);
        try {
            fileWriter = new PrintWriter (new FileWriter(FILEPATH), true);
        } catch (IOException IOE){
            IOE.printStackTrace();
        }
    }
    
    /**
     * Updates the total revenue and writes it to the text file
     * 
     * @param revenue the revenue to add to the total
     */
    @Override
    public void updateTotalRevenue (Amount revenue){
        totalRevenue = totalRevenue.add(revenue);
        writeToFile ();
    }
    
    private void writeToFile (){
        fileWriter.println("Total revenue: " + totalRevenue + "\n");
    }
}
