
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
 * Default discount calculation algorithm
 *
 * @author Ali Sahibi
 */
public class DefaultDiscount implements DiscountCalculator {
    private CostumerRegistry costumerReg;
    private Percentage discount;
    private Sale currentSale;
    private List<Item> itemsInSale;
    private Amount totalPrice;
    
    /**
     * Constructor for the class
     */
    public DefaultDiscount (){
        costumerReg = CostumerRegistry.getCostumerRegistry();
        this.discount = new Percentage(0);
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
        if (totalPrice.getValue() >= 100 && totalPrice.getValue() < 1000){
            discount = discount.add(new Percentage(2));
        }
        else if (totalPrice.getValue() >= 1000){
            discount = discount.add(new Percentage (5));
        }
    }
    
    private void setDiscountBasedOnNumberOfItems (){
        int numberOfItems = getNumberOfItemsInSale();
        if (numberOfItems >= 5 && numberOfItems < 10){
            discount = discount.add(new Percentage (5));
        }
        else if (numberOfItems >= 10){
            discount = discount.add(new Percentage (8));
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
