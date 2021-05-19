
package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.CostumerDTO;
import se.kth.iv1350.processSale.util.Percentage;

/**
 * This is a strategy pattern interface which is implemented by classes with discount
 * calculation algorithms 
 *
 * @author Ali Sahibi
 */
public interface DiscountCalculator {
    /**
     * This method calculates the discount using an algorithm
     * 
     * @param sale object which holds information used to calculate the discount
     * @param customer object which holds information about the costumer requesting
     * discount
     * @return calculated discount in Percentage
     */
    Percentage calculateDiscount (Sale sale, CostumerDTO customer);
}
