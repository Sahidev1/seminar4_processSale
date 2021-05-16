package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.integration.StoreDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.Percentage;

/**
 * This class holds information about the ongoing sale
 *
 * @author Ali Sahibi
 */
public class SaleInformation {
    private final List<Item> items;
    private Amount totalPrice;
    private Amount paymentAmount;
    private Amount paidInVAT;
    private final Date timeOfSale;
    private final StoreDTO store;
    
    /** 
     * Constructor for the class SaleInformation
     */
    public SaleInformation (){
        this.items = new ArrayList<>();
        this.totalPrice = new Amount(0);
        this.paidInVAT = new Amount(0);
        this.paymentAmount = new Amount (0);
        this.timeOfSale = new Date();
        this.store = new StoreDTO();
    }
    
    /** 
     * Getter method for the total price of the sale
     * 
     * @return amount the total price
     */
    Amount getTotalPrice (){
        return totalPrice;
    }

    /**
     * Getter method for the amount payed by the costumer
     * 
     * @return amount paid by costumer
     */
    Amount getPaymentAmount() {
        return paymentAmount;
    }
    
    /**
     * Getter method for how much was paid in VAT
     * 
     * @return amount paid in VAT
     */
    Amount getPaidInVAT() {
        return paidInVAT;
    }

    /**
     * Getter method for the time of sale
     * 
     * @return date object with time and date of sale
     */
    Date getTimeOfSale() {
        return timeOfSale;
    }
    
    /**
     * Getter method for the store
     * 
     * @return the store object
     */
    StoreDTO getStore() {
        return store;
    }
    
    /**
     * Getter method for the list of items
     * 
     * @return a reference to the list of items
     */
    List<Item> getListOfItems (){
        return items;
    }
    
    /**
     * This method searches an item from List of items in sale using the information
     * from another item outside of the list
     * 
     * @param item which contains item identifier
     * @return the found item if it is found, else null that nothing was found
     */
    Item searchForItem (Item item){ 
        for (Item itemInList : items) {
            if (itemInList.equals(item)){
                return itemInList;
            }
        }
        return null;
    }
    
    /**
     * This method checks wether an item already is in the List of items
     * 
     * @param item is the item that is getting checked
     * @return true if it is in the list, else false
     */
    boolean hasItemAlreadyBeenAdded (Item item){
        for (Item itemInList : items){
            if (itemInList.equals(item)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method increments the quantity of an item in the sale and
     * updates the total price
     * 
     * @param item to increment quantity of
     */
    void incrementQuantityOfItem (Item item){
        item.incrementQuantityOfItem ();
        int quantity = 1;
        updateTotalPrice (item, quantity);
    }
    
    /**
     * This method updates the quantity of an item in the sale and update the
     * total price
     * 
     * @param item
     * @param addedQuantity 
     */
    void updateQuantityOfItem (Item item, int addedQuantity){
        item.updateQuantityOfItem (addedQuantity);
        updateTotalPrice (item, addedQuantity);
    }
    
    /** 
     * This method adds the amount the costumer paid for the sale
     * 
     * @param paymentAmount amount of the payment
     */
    void addPayment (Amount paymentAmount){
        this.paymentAmount = paymentAmount;
    }
    
    /**
     * This method adds an item to the sale information
     * 
     * @param item the item to be added
     */
    void addItem (Item item){
        items.add(item);
        int quantity =  item.getQuantity();
        updateTotalPrice (item, quantity);
    }
    
    /**
     * This method returns a string with all the items in the sale
     * 
     * @return all the items in the sale as a string
     */
    String saleItemsToString (){
        StringBuilder saleItemsString = new StringBuilder ();
        for (Item itemInList : items){
            saleItemsString.append (itemInList);
            saleItemsString.append ("\n");
        }
        return saleItemsString.toString();
    }
    
    /**
     * Updates the total price based on the discount for the costumer
     * 
     * @param discount the discount object with information about the discount
     */
    void updateTotalPriceBasedOnDiscount (Discount discount){
        Percentage discountPercent = discount.getCalculatedDiscount();
        totalPrice = calculateTotalPriceBasedOnDiscount (discountPercent);
    }
    
    private Amount calculateTotalPriceBasedOnDiscount (Percentage discount){
        return discountFactor(discount).multiply(totalPrice);
    }
    
    private Amount discountFactor (Percentage discount){
        return new Amount (1.00 - (discount.getPercentValue() / 100));
    }
        
    /**
     * This method calculates the change to give back to the costumer
     * 
     * @return the difference between amount paid and the total price
     */
    Amount calculateChange (){
        return paymentAmount.subtract(totalPrice);
    }
    
    /**
     * This method updates the total price when an item has been added to
     * the sale. The method also updates how much of the total price is VAT
     * 
     * @param item the item added to the sale
     * @param quantity the quantity of the item
     */
    private void updateTotalPrice (Item item, int quantity){
        totalPrice = totalPrice.add(new Amount(item.getPrice().getValue() * (1 +
        item.getItemDTO().getItemVATDecimalValue()) * quantity));
        
        paidInVAT = paidInVAT.add(new Amount(item.getPrice().getValue() * 
        item.getItemDTO().getItemVATDecimalValue() * quantity));
    }
    
}
