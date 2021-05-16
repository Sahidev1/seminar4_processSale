package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.Percentage;

/**
 * The itemDTO holds information about an Item object
 *
 * @author Ali Sahibi
 */
public class ItemDTO {
    private String itemIdentifier;
    private String itemDescription;
    private Amount price;
    private Percentage itemVAT;

    /** 
     * Constructor for ItemDTO 
     * 
     * @param itemIdentifier of the item
     * @param itemDescription of them item
     * @param price of the item
     * @param itemVAT , VAT of the item
     */
    public ItemDTO(String itemIdentifier, String itemDescription, Amount price, Percentage itemVAT) {
        this.itemIdentifier = itemIdentifier;
        this.itemDescription = itemDescription;
        this.price = price;
        this.itemVAT = itemVAT;
    }
    
    /**
     * Constructor for itemDTO when only item identifier is given
     * 
     * @param itemIdentifier which given
     */
    public ItemDTO(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    /** 
     * Getter method that retrieves ItemIdentifier
     * 
     * @return String with item identifier
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }

    /** 
     * Getter method that retrieves ItemDescription
     * 
     * @return String with item description
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /** 
     * Getter method that retrieves Price
     * 
     * @return Amount, the price in Amount
     */
    public Amount getPrice() {
        return price;
    }

    /** 
     * Getter method that retrieves itemVAT
     * 
     * @return String with VAT 
     */
    public Percentage getItemVAT() {
        return itemVAT;
    }
    
    /**
     * Returns decimal value of the percent VAT, so the VAT is 4% it returns 
     * 0.04
     * 
     * @return decimal value of percentage
     */
    public double getItemVATDecimalValue (){
        return itemVAT.getPercentValue() / 100;
    }
            
    /** 
     * toString method for itemDTO
     * 
     * @return a String with information about the itemDTO object
     */
    @Override
    public String toString() {
        StringBuilder itemDTOString = new StringBuilder();
        
        itemDTOString.append("itemIdentifier: ").append (itemIdentifier);
        newLine (itemDTOString);
        
        itemDTOString.append("itemDescription: ").append (itemDescription);
        newLine (itemDTOString);
        
        itemDTOString.append("price: ").append (price);
        newLine (itemDTOString);
        
        itemDTOString.append("itemVAT: ").append (itemVAT);
        newLine (itemDTOString);
        
        return itemDTOString.toString();
    }
    
    private void newLine (StringBuilder stringBuilder){
        stringBuilder.append ("\n");
    }
    
}
