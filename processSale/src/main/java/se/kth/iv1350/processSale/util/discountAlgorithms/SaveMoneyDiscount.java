
package se.kth.iv1350.processSale.util.discountAlgorithms;

import java.util.List;
import se.kth.iv1350.processSale.integration.CostumerDTO;
import se.kth.iv1350.processSale.integration.CostumerRegistry;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.model.DiscountCalculator;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.Percentage;

/**
 * Discount algorithm used when the store wants to save money
 *
 * @author Ali Sahibi
 */
public class SaveMoneyDiscount implements DiscountCalculator{  
    private CostumerRegistry costumerReg;
    private Percentage discount;
    private Sale currentSale;
    private List<Item> itemsInSale;
    private Amount totalPrice;
    
    /**
     * Constructor for the class
     */
    public SaveMoneyDiscount() {
        costumerReg = CostumerRegistry.getCostumerRegistry();
        this.discount = new Percentage (0);
    }
    
    @Override
    public Percentage calculateDiscount (Sale sale, CostumerDTO costumer){
        currentSale = sale; 
        costumerReg.searchCostumer(costumer);
        itemsInSale = sale.getListOfItems();   
        discount = discount.add(costumer.getBaseDiscountForCustomer());
        runDiscountLogic();
        return discount;
    } 
    
    private void runDiscountLogic (){
        setDiscountBasedOnNumberOfItems ();
        setDiscountBasedOnTotalPrice();        
    }
    
    private void setDiscountBasedOnTotalPrice (){
        totalPrice = currentSale.getTotalPrice();
        if (totalPrice.getValue() >= 200 && totalPrice.getValue() < 1000){
            discount = discount.add(new Percentage(1));
        }
        else if (totalPrice.getValue() >= 1000){
            discount = discount.add(new Percentage (3));
        }
    }
    
    private void setDiscountBasedOnNumberOfItems (){
        int numberOfItems = getNumberOfItemsInSale();
        if (numberOfItems >= 8 && numberOfItems < 15){
            discount = discount.add(new Percentage (3));
        }
        else if (numberOfItems >= 15){
            discount = discount.add(new Percentage (4));
        }
    }
    
    private int getNumberOfItemsInSale (){
        int quantity = 0;
        for (Item item : itemsInSale){
            quantity = quantity + item.getQuantity();
        }
        return quantity;
    }
}
