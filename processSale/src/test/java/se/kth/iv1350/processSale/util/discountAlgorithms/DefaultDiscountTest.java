/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.processSale.util.discountAlgorithms;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.integration.Item;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.util.Amount;

/**
 *
 * @author Ali Sahibi
 */
public class DefaultDiscountTest {
    private Sale sale = new Sale();
    List<Item> list;
    Amount amt;
    
    @BeforeEach
    public void setUp() {
    
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calculateDiscount method, of class DefaultDiscount.
     */
    @Test
    public void testCalculateDiscount() {
        list = sale.getListOfItems();
        try{
            amt = sale.getTotalPrice();
        } catch (NullPointerException e){
            fail("nullpointer thrown");
        }
    }   
}
