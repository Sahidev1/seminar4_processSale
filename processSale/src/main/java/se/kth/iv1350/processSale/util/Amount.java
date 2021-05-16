
package se.kth.iv1350.processSale.util;

/**
 * This class represent an amount of money
 * 
 * @author Ali Sahibi
 */
public class Amount {
    private double value;
    
    /** 
     * Constructor for the class Amount
     * 
     * @param value a double 
     */
    public Amount (double value){
        this.value = value;
    }

    /** 
     * Getter method that retrieves the value of the amount
     * 
     * @return value as double
     */
    public double getValue() {
        return value;
    }
    
    /** 
     * toString method for the class Amount
     * 
     * @return a String which describes an Amount object
     */
    @Override
    public String toString(){
        return String.valueOf(value);
    }
    
    /** 
     * This method performs an addition of two amount objects
     * 
     * @param amount the other amount object to be added
     * @return a sum of the two amounts
     */
    public Amount add (Amount amount){
        return new Amount(value + amount.getValue());
    }
    
    /**
     * This method performs subtraction of two amount objects
     * 
     * @param amount the other amount which is being subtracted
     * @return difference of the two amounts
     */
    public Amount subtract (Amount amount){
        return new Amount(value - amount.getValue());
    }
    
    /**
     * This method performs multiplication of two amount objects
     * 
     * @param amount to be multiplied with
     * @return the product
     */
    public Amount multiply (Amount amount){
        return new Amount(value * amount.getValue());
    }
}
