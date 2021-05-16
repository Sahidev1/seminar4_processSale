package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.Receipt;

/**
 * This class is responsible for making calls to an external printer
 *
 * @author Ali Sahibi
 */
public class Printer {
    
    /** 
     * Constructor the the class Printer
     */
    public Printer (){
        
    }
    
    /** 
     * This method make a call to the external printer to make a new print
     * 
     * @param receipt the printed object is a receipt
     */
    public void printReceipt (Receipt receipt){
        System.out.println("*************************** PRINTER OUTPUT ***************************");
        System.out.println(receipt);
        System.out.println("**********************************************************************");
    } 
}
