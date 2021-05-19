package se.kth.iv1350.processSale.model;

import java.util.List;
import se.kth.iv1350.processSale.integration.CostumerDTO;
import se.kth.iv1350.processSale.integration.CostumerRegistry;
import se.kth.iv1350.processSale.integration.IntegrationCreator;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.util.Percentage;
import se.kth.iv1350.processSale.util.discountAlgorithms.DefaultDiscount;
import se.kth.iv1350.processSale.util.discountAlgorithms.HalloweenDiscount;

/**
 * This class is used to deal with discount related logic in the case of a
 * discount request during the sale
 *
 * @author Ali Sahibi
 */
public class Discount {
    private Sale sale;
    private DiscountCalculator discountCalc;
    private CostumerDTO costumerDTO;
    private CostumerRegistry costumerRegistry;
    private Percentage calculatedDiscount;
    
    /** 
     * Constructor for the class Discount
     */
    public Discount (){
        this.calculatedDiscount = new Percentage (0);
    }

    /**
     * Getter method for the calculated discount 
     *
     * @return calculated discount in percent
     */
    Percentage getCalculatedDiscount() {
        return calculatedDiscount;
    }
    
    /** 
     * This method handles a discount request
     * 
     * @param costumerDTO the costumer that is requesting a discount
     * @param sale the sale which is requesting a discount
     */
    void discountRequest (CostumerDTO costumerDTO, Sale sale){
        this.sale = sale;
        this.costumerDTO = costumerDTO;
        discountCalc = new HalloweenDiscount ();
        calculatedDiscount = discountCalc.calculateDiscount(this.sale, this.costumerDTO);
    }
    
    /**
     * This method gives the discount object access to the costumerRegistry
     * 
     * @param integrations IntegrationsCreator object which holds getter for
     * costumerRegistry
     */
    public void giveAccessToCostumerRegistry (IntegrationCreator integrations){
        this.costumerRegistry = integrations.getCostumerRegistry();
    }
    
}
