/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.Percentage;

/**
 *
 * @author Ali Sahibi
 */
public class ItemTest {
    private Item validItem0;
    private Item validItem1;
    private Item invalidItem;
    
    @BeforeEach
    public void setUp() {
        ItemDTO itemDTO0 = new ItemDTO ("AX356235", "Apple", new Amount(25), new Percentage(11));
        validItem0 = new Item (itemDTO0);
        
        ItemDTO itemDTO1 = new ItemDTO ("ZCXV2311", "toy", new Amount(28), new Percentage(14));
        validItem1 = new Item (itemDTO1);

        invalidItem = new Item();
        invalidItem.setIsItemValid(false);
    }
    
    @AfterEach
    public void tearDown() {
        validItem0 = null;
        validItem1 = null;
        invalidItem = null;
    }
    
    @Test
    public void testEquals (){
        Item itemCopyOfItem0 = new Item(validItem0);
        boolean expResult = true;
        boolean result = validItem0.equals(itemCopyOfItem0);
        assertEquals (expResult, result, "Test is saying that two identical "
                + "object are not equal");
    }
    
    @Test
    public void testEqualsItemAreNotEqual (){
        boolean expResult = false;
        boolean result = validItem0.equals(validItem1);
        assertEquals (expResult, result, "Test is saying that two different"
                + "objects are equal");
    }
    
    @Test
    public void testEqualsInvalidItem (){
        try {
            validItem0.equals(invalidItem);
            fail("An invalid item with with itemIdentifier pointing to a null value"
                    + "equaled an item with a valid itemIdentifier");
        }
        catch (NullPointerException e){
            
        }
    }
    
    @Test
    public void testIncrementQuantityOfItem (){
        int starterQuantity = validItem0.getQuantity();
        validItem0.incrementQuantityOfItem();
        int incrementedQuantity = validItem0.getQuantity();
        int expValue = starterQuantity + 1;
        boolean condition = expValue == incrementedQuantity;
        assertTrue (condition, "The quantity did not get incremented");
    }
    
    @Test
    public void testUpdateQuantityOfItem (){
        int starterQuantity = validItem0.getQuantity();
        int quantityToAdd = 11;
        validItem0.updateQuantityOfItem(11);
        int expQuantity = starterQuantity + quantityToAdd;
        int updatedQuantity = validItem0.getQuantity();
        boolean condition = expQuantity == updatedQuantity;
        assertTrue (condition, "The updated quantity of the item was not correct");
    }
    
    @Test
    public void testUpdateQuantityOfItemWithZero (){
        int starterQuantity = validItem0.getQuantity();
        int quantityToAdd = 0;
        validItem0.updateQuantityOfItem(0);
        int expQuantity = starterQuantity + quantityToAdd;
        int updatedQuantity = validItem0.getQuantity();
        boolean condition = expQuantity == updatedQuantity;
        assertTrue (condition, "The updated quantity of the item was not correct");        
    }
    
    @Test
    public void testToStringItemIsValid (){
        String invalidItemMessage = "Item is invalid";
        boolean expResult = false;
        boolean result = invalidItemMessage.equals(validItem0.toString());
        assertEquals (expResult, result, "Valid item returned the string"
                + "for an invalid item");
    }
    
    @Test
    public void testToStringItemIsInvalid (){
        String invalidItemMessage = "Item is invalid";
        boolean expResult = true;
        boolean result = invalidItemMessage.equals(invalidItem.toString());
        assertEquals (expResult, result, "Valid item returned the string"
                + "for an invalid item");        
    }
    
    @Test
    public void testGetIsItemValid (){
        boolean expResult = true;
        boolean result = validItem0.getIsItemValid();
        assertEquals (expResult, result, "A valid item is identified as invalid");
    }
    
    @Test
    public void testGetIsItemValidInvalidItem (){
        boolean expResult = false;
        boolean result = invalidItem.getIsItemValid();
        assertEquals (expResult, result, "A valid item is identified as invalid");
    }

   
}
