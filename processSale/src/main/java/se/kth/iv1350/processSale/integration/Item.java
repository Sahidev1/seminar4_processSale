package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.util.Amount;

/**
 * This class represents the item object which is handled during the sale
 * 
 * @author Ali Sahibi
 */
public class Item {
    private int quantity;
    private boolean isItemValid;
    private ItemDTO itemDTO;
    
    /** 
     * Constructor for an Item object
     * 
     * @param itemDTO with immutable information about the item
     */
    public Item (ItemDTO itemDTO){
        this.itemDTO = itemDTO;
        isItemValid = true;
        this.quantity = 1;
    }
    
    /**
     * Constructor for an Item object
     */
    public Item (){
        isItemValid = true;
    }
    
    /**
     * Copy Constructor that creates a new object that is a copy of the object 
     * in the parameter
     * 
     * @param itemToCopy the Item to copy
     */
    public Item (Item itemToCopy){
        this.quantity = itemToCopy.quantity;
        this.isItemValid = itemToCopy.isItemValid;
        this.itemDTO = itemToCopy.itemDTO;
    }
    
    /**
     * Getter for the quantity
     * 
     * @return a quantity described by an integer
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter for the isItemValid field
     * 
     * @return a boolean telling whether the item is valid or not
     */
    public boolean getIsItemValid() {
        return isItemValid;
    }
    
    /**
     * Getter for the itemDTO  
     * 
     * @return the itemDTO of the item
     */
    public ItemDTO getItemDTO() {
        return itemDTO;
    }
    
    /**
     * Getter for the Item identifier
     * 
     * @return String which contains item identifier
     */
    public String getItemIdentifier(){
        return itemDTO.getItemIdentifier();
    }
    
    /**
     * Getter for the price of the item
     * 
     * @return price in amount 
     */
    public Amount getPrice (){
        return itemDTO.getPrice();
    }
    
    /**
     * Setter for the isItemValid field
     * 
     * @param isItemValid a boolean value
     */
    public void setIsItemValid(boolean isItemValid) {
        this.isItemValid = isItemValid;
    }

    /**
     * Sets the quantity of an item
     * 
     * @param quantity to set
     */
    public void setQuantityOfItem (int quantity){
        this.quantity = quantity;
    }
    
    /** 
     * This method increments the quantity of an item
     */
    public void incrementQuantityOfItem (){
        quantity++;
    }
    
    /**
     * This method updates the quantity of an item
     * 
     * @param addedQuantity 
     */
    public void updateQuantityOfItem (int addedQuantity){
        quantity = quantity + addedQuantity;
    }
    
    /**
     * This method compares two items using the itemIdentifiers of the items
     * 
     * @param item other item to compare to
     * @return boolean true if items are equal, else false
     */
    public boolean equals (Item item){
        return this.getItemIdentifier().equals(item.getItemIdentifier());
    }
    
    /**
     * A toString method that prints out all information about the item in the
     * case the item is valid, if it is invalid it prints that the item is 
     * invalid
     * 
     * @return a string with information about the item
     */
    @Override
    public String toString (){
        if (isItemValid){
            return "Quantity of item: " + quantity + "\n" + itemDTO.toString();
        }
        else{
            return "Item is invalid";
        }
    }
}
