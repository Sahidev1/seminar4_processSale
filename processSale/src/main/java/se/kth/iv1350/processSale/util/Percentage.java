package se.kth.iv1350.processSale.util;

/**
 * This class represents a percentage
 * 
 * @author Ali Sahibi
 */
public class Percentage {
    private double percent;
    private final String percentSymbol = "%";
    
    /** 
     * Constructor for the class Percentage
     * 
     * @param percent 
     */
    public Percentage (double percent){
        this.percent = percent;
    }

    /**
     * Getter method for percent
     * 
     * @return percent as double
     */
    public double getPercentValue() {
        return percent;
    }
    
    /** 
     * toString method for the class Percentage
     * 
     * @return a String which describes a percentage
     */
    @Override
    public String toString (){
        return String.valueOf(percent) + percentSymbol;
    }
    
    /**
     * Addition method for the class Percentage
     * 
     * @param percent to add to this percentage
     * @return sum of the two percentages
     */
    public Percentage add(Percentage percent){
        return new Percentage (this.percent + percent.getPercentValue());
    }
}
