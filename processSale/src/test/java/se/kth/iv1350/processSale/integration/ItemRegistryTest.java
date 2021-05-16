/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.Percentage;

/**
 *
 * @author Ali Sahibi
 */
public class ItemRegistryTest {
    private final ItemRegistry itemRegistry = new ItemRegistry();
    private ItemDTO validItemDTO;
    private ItemDTO invalidItemDTO;
    
    public ItemRegistryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        validItemDTO = new ItemDTO ("AX356235", "Apple", new Amount (22), new Percentage (6));
        invalidItemDTO = new ItemDTO ("INVALID000", "INVALID", new Amount(0), new Percentage (0));
    }
    
    @AfterEach
    public void tearDown() {
        validItemDTO = null;
        invalidItemDTO = null;
    }

    @Test
    public void testSearchItemDTOCanItFindAmatchInRegistry () throws ItemNotFoundException{
        int quantity = 1;
        String itemIdentifierOfValidItem = validItemDTO.getItemIdentifier();
        Item foundItem = itemRegistry.searchItem(validItemDTO, quantity);
        String itemIdentifierOfFoundItem = foundItem.getItemDTO().getItemIdentifier();
        boolean expResult = true; 
        boolean result = itemIdentifierOfValidItem.equals(itemIdentifierOfFoundItem);
        assertEquals (expResult, result, "The method could not find a match that existed"
        + " In the database and that it should have found");    
    }
    
    @Disabled
    @Test
    public void testSearchItemDTODoesItReturnAnInvalidItemIfNoMatchInRegistry () throws ItemNotFoundException{
        int quantity = 1;
        Item foundItem = itemRegistry.searchItem(invalidItemDTO, quantity);
        boolean condition = foundItem.getIsItemValid();
        assertFalse (condition, "The method did not return an invalid item" + 
        " for searching an item that did not exist");
    }
    
    @Test
    public void testSearchItemDTODoesItReturnAnItemWithCorrectQuantity () throws ItemNotFoundException{
        int quantity = 5;
        Item foundItem = itemRegistry.searchItem (validItemDTO, quantity);
        int quantityOfFoundItem = foundItem.getQuantity();
        boolean condition = quantity == quantityOfFoundItem;
        assertTrue (condition, "The item does not have the correct quantity");
    }
    
}
