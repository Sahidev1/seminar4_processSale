
package se.kth.iv1350.processSale.integration;

/**
 * This is class is a checked exception that is thrown when an item
 * couldn't be found in the item registry
 * 
 * @author Ali Sahibi
 */
public class ItemNotFoundException extends Exception {
    
    /**
     * Constructor for the class
     * 
     * @param invalidItem the item which could not be found
     */
    public ItemNotFoundException (ItemDTO invalidItem){
        super("Could not find Item with this ItemIdentifier: " +
                invalidItem.getItemIdentifier());
    }
}
