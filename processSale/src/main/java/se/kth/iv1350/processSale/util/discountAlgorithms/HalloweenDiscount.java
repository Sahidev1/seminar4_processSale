
package se.kth.iv1350.processSale.util.discountAlgorithms;

import java.util.List;
import se.kth.iv1350.processSale.integration.CostumerDTO;
import se.kth.iv1350.processSale.integration.CostumerRegistry;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.model.DiscountCalculator;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Percentage;


/**
 * Discount algorithm used during halloween
 *
 * @author Ali Sahibi
 */
public class HalloweenDiscount implements DiscountCalculator {
    private CostumerRegistry costumerReg;
    private Percentage discount;
    private Sale currentSale;
    private List<Item> itemsInSale;
    
    /**
     * Constructor for the class
     */
    public HalloweenDiscount() {
        costumerReg = CostumerRegistry.getCostumerRegistry();
        this.discount = new Percentage (0);
    }
    
    public Percentage calculateDiscount (Sale sale, CostumerDTO costumer){
        currentSale = sale;
        costumerReg.searchCostumer(costumer);
        itemsInSale = currentSale.getListOfItems();
        discount = discount.add(costumer.getBaseDiscountForCustomer());
        runDiscountLogic();
        return discount;
    }
    
    private void runDiscountLogic (){
        DiscountBasedOnNumberOfItems();
        DiscountBasedOnTotalPrice ();
        DiscountBasedOnTypeOfItems ();
    }
    
    private void DiscountBasedOnNumberOfItems (){
        int numberOfSaleItem = getNumberOfItemsInSale ();
        if (numberOfSaleItem >= 3 && numberOfSaleItem < 10){
            discount.add(new Percentage(7));
        }
        else if (numberOfSaleItem >= 10){
            discount.add(new Percentage(12));
        }
    }

    private int getNumberOfItemsInSale (){
        int quantity = 0;
        for (Item item : itemsInSale){
            quantity = quantity + item.getQuantity();
        }
        return quantity;
    }

    private void DiscountBasedOnTotalPrice() {
        double totalPrice = currentSale.getTotalPrice().getValue();
        if (totalPrice >= 100 && totalPrice < 400){
            discount.add(new Percentage(4));
        }
        else if (totalPrice >= 400){
            discount.add(new Percentage(8));
        }
    }

    private void DiscountBasedOnTypeOfItems() {
        String itemIdentifierPumpkin = "BX029510";
        String itemIdentifierJackoLantern = "VZ114037";
        if (doesSaleContain(itemIdentifierPumpkin)){
            discount.add(new Percentage (2));
        }
        else if (doesSaleContain(itemIdentifierJackoLantern)){
            discount.add(new Percentage (3));
        }
    }
    
    private boolean doesSaleContain (String itemIdentifier){
        boolean found = false;
        for (Item saleItem : itemsInSale){
            if (saleItem.getItemIdentifier().equals(itemIdentifier)){
                found = true;
                break;
            }
        }
        return found;
    }
}
