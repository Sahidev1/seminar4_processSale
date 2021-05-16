/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.model;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.integration.CostumerDTO;
import se.kth.iv1350.processSale.integration.IntegrationCreator;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.integration.ItemDTO;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.util.Amount;
import se.kth.iv1350.processSale.util.Percentage;

/**
 *
 * @author Ali Sahibi
 */
public class SaleTest {
    private Sale sale;
    private Item anItem;
    private CostumerDTO costumer;
    
    @BeforeEach
    public void setUp() {
        sale = new Sale ();
        anItem = new Item (new ItemDTO ("Identity23", "thing", 
        new Amount (223), new Percentage (15)));

        costumer = new CostumerDTO ("John Doe",
        "19880509", "DoeTown evergreenstreet 22", "2391000102");
    }
    
    @AfterEach
    public void tearDown() {
        sale = null;
        anItem = null;
        costumer = null;
    }

    @Test
    public void testAddItemToSaleDoesItemGetAdded (){
        String itemIdentifierOfAnItem = anItem.getItemIdentifier();
        sale.addItemToSale(anItem);
        Item itemInSale = sale.getListOfItems().get(0);
        String itemIdentifierOfItemInSale = itemInSale.getItemIdentifier();
        boolean expResult = true;
        boolean result = itemIdentifierOfAnItem.equals(itemIdentifierOfItemInSale);
        assertEquals (expResult, result, "The item did not get added to the sale");
    }
    
    @Test
    public void testAddItemToSaleDoesItIncrementQuantity (){
        sale.addItemToSale(anItem);
        Item copyOfAnItem = new Item (anItem);
        sale.addItemToSale(copyOfAnItem);
        Item anItemRetrievedFromTheSale = sale.getItemFromTheList(anItem);
        int quantity = anItemRetrievedFromTheSale.getQuantity();
        boolean expResult = true;
        boolean result = (quantity == 2);
        assertEquals (expResult, result, "The quantity of the item did not get"
        + "incremented");
    }
    
    @Test
    public void testAddItemToSaleDoesItCreateAnewItemIfItemAlreadyExists(){
        sale.addItemToSale(anItem);
        Item copyOfAnItem = new Item (anItem);
        sale.addItemToSale(copyOfAnItem);
        List<Item> itemsInSale = sale.getListOfItems();
        int numberOfItemsInList = itemsInSale.size();
        boolean condition = (numberOfItemsInList == 1);
        assertTrue (condition, "It creates a new item instead of just incrementing"
                + "the quantity of the item in the sale");
    }
    
    @Test
    public void testAddItemToSaleDoesItCorrectlyAddAquantityOfItem (){
        int quantity = 7;
        sale.addItemToSale(anItem);
        Item copyOfAnItem = new Item (anItem);
        sale.addItemToSale(anItem, quantity);
        Item anItemRetrievedFromTheSale = sale.getItemFromTheList(anItem);
        int totalQuantityOfItem = anItemRetrievedFromTheSale.getQuantity();
        int totalExpectedQuantity = 8;
        boolean condition = totalQuantityOfItem == totalExpectedQuantity;
        assertTrue (condition, "The quantity of the item in the sale is incorrect");
    }
    
    @Test
    public void testAddItemDoesItAddAnInvalidItemToTheSale (){
        anItem.setIsItemValid(false);
        sale.addItemToSale(anItem);
        int itemsInSale = sale.getListOfItems().size();
        int expectedItemInSale = 0;
        boolean condition = itemsInSale == expectedItemInSale;
        assertTrue (condition, "An invalid item was added to the sale");
    } 
    
    @Test
    public void testGetItemFromTheList (){
        sale.addItemToSale(anItem);
        Item  foundItem = sale.getItemFromTheList(anItem);
        boolean condition = (anItem == foundItem);
        assertTrue (condition, "Wrong item received");
    }
    
    @Test 
    public void testGetItemFromTheListItemNotInTheList (){
        Item itemNotOnList = new Item (new ItemDTO ("Identity11", "thing2", 
        new Amount (222), new Percentage (15)));
        sale.addItemToSale(anItem);
        Item foundItem = sale.getItemFromTheList(itemNotOnList);
        assertNull (foundItem, "an item was retrieved from the list where it"
                + "shouldn't exist");
    }
    
    @Test 
    public void testGetItemFromTheListDoesItGetCorrectItem (){
        Item item0 = new Item (new ItemDTO ("Identity11", "thing2", 
        new Amount (222), new Percentage (15)));   
        
        Item item1 = new Item (new ItemDTO ("Identity177", "thing2", 
        new Amount (222), new Percentage (15)));   
        
        sale.addItemToSale(anItem);
        sale.addItemToSale(item0);
        sale.addItemToSale(item1);
        
        Item foundItem = sale.getItemFromTheList(item0);
        boolean condition = (foundItem == item0);
        assertTrue (condition, "Wrong item was returned");
    }
    
    @Test 
    public void testHasItemAlreadyBeenAdded (){
        sale.addItemToSale(anItem);
        boolean expResult = true;
        boolean result = sale.hasItemAlreadyBeenAdded(anItem);
        assertEquals (expResult, result,"the item has been added, this return"
                + " value indicates that it hasn't been added");
    }
    
    @Test
    public void testHasItemAlreadyBeenAddedItemHasNotBeenAdded (){
        Item item0 = new Item (new ItemDTO ("Identity11", "thing2", 
        new Amount (222), new Percentage (15)));  
        
        sale.addItemToSale(anItem);
        boolean expResult = false;
        boolean result = sale.hasItemAlreadyBeenAdded(item0);
        assertEquals (expResult, result, "The item has not been added to the "
                + "sale");
    }
    
    @Test 
    public void testGivePaymentPartsDoesPaymentGetParts (){
        IntegrationCreator integrations = new IntegrationCreator();
        CashRegister cashRegister = new CashRegister();
        Printer printer = new Printer();
        sale.givePaymentParts(integrations, cashRegister, printer);
        
        try {
            PrintStream sysOutStream = System.out;
            System.setOut( new PrintStream( new OutputStream(){
                public void write (int integer){
                    // No op, just to block System.out call from method below
                }
            }));
            
            sale.makePayment(new Amount (33));
            
            System.setOut(sysOutStream);
        }
        catch (NullPointerException e){
            fail("Payment object did not get the reference to the objects"
                    + "it needed to perform the operation in the try block");
        }  
    }
    
    @Test
    public void testDiscountRequest (){
        IntegrationCreator integrations = new IntegrationCreator();
        Discount discountObj = new Discount ();
        
        Amount priceOfItem0 = new Amount (22);
        Percentage VATofItem0 = new Percentage (6);
        ItemDTO itemDTO0 = new ItemDTO ("AX356235", "Apple", priceOfItem0, VATofItem0);
        Item itemInRegistry = new Item (itemDTO0);
        itemInRegistry.setQuantityOfItem(12);
        
        sale.addItemToSale(itemInRegistry);
        discountObj.giveAccessToCostumerRegistry(integrations);
        Amount discountamt = sale.discountRequest(costumer, discountObj);
        
        double costumerDiscount = costumer.getBaseDiscountForCustomer().getPercentValue() + 
                2; // 2 percent discount is added for price above 100
        double expValue = priceOfItem0.getValue() * ( 1 + 
                (VATofItem0.getPercentValue() / 100)) * itemInRegistry.getQuantity() * 
                (1 - costumerDiscount / 100);
        
        boolean condition = expValue == discountamt.getValue();
        assertTrue (condition,"The returned price is incorrect");
    }
    
    @Test
    public void testMakePayment (){
        IntegrationCreator integrations = new IntegrationCreator();
        CashRegister cashRegister = new CashRegister();
        Printer printer = new Printer();
        sale.givePaymentParts(integrations, cashRegister, printer);    
        
        Amount paymentamt = new Amount(750);
        
        PrintStream sysOutStream = System.out;
        System.setOut( new PrintStream( new OutputStream(){
            public void write (int integer){
                // No op, just to block System.out call from method below
            }
        }));

        sale.makePayment(paymentamt);

        System.setOut(sysOutStream);
        
        Amount expValue = paymentamt;
        Amount valueFromSale = sale.getPaymentAmount();
        boolean condition = expValue.getValue() == valueFromSale.getValue();
        assertTrue (condition, "Sale object returned an payment amount that "
                + "is different then what was put in");
    }
}
