package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.util.Percentage;

/**
 * A CostumerDTO holds data about a Costumer
 * 
 * @author Ali Sahibi
 */
public class CostumerDTO {
    private final String name;
    private final String dateOfBirth;
    private final String adress;
    private final String socialSecurityNumber;
    private Percentage baseDiscountForCostumer;
    
    /** 
     * Constructor for the CostumerDTO class
     * 
     * @param name of the Costumer
     * @param dateOfBirth of the Costumer
     * @param adress of the Costumer
     * @param socialSecurityNumber of the Costumer
     */
    public CostumerDTO(String name, String dateOfBirth, String adress, String socialSecurityNumber) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
        this.socialSecurityNumber = socialSecurityNumber;
    }
    
    /**
     * Constructor for the CostumerDTO class
     * 
     * @param name of the costumer
     * @param dateOfBirth of the costumer
     * @param adress of the costumer
     * @param socialSecurityNumber of the costumer
     * @param baseDiscountForCostumer the base discount costumer has right to
     */
    public CostumerDTO(String name, String dateOfBirth, String adress, String socialSecurityNumber,
    Percentage baseDiscountForCostumer) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
        this.socialSecurityNumber = socialSecurityNumber;
        this.baseDiscountForCostumer = baseDiscountForCostumer;
    }

    /**
     * Getter method for name of costumer
     * 
     * @return name of costumer
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the Date of birth of costumer
     * 
     * @return date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Getter method for the address of the costumer
     * 
     * @return address of costumer
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Getter method for the social security number of the costumer
     * 
     * @return social security number
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Getter method for the base discount for a costumer
     * 
     * @return the discount percentage
     */
    public Percentage getBaseDiscountForCustomer() {
        return baseDiscountForCostumer;
    }
    
    /**
     * Sets the base discount for the costumer
     * It's only possible to do so if the base discount has not been given yet
     * 
     * @param percentDiscount 
     */
    public void setDiscount (Percentage percentDiscount){
        if (baseDiscountForCostumer == null){
            baseDiscountForCostumer = percentDiscount;
        }
    }
    
    /**
     * This method checks if two DTOs are describing the same costumer
     * 
     * @param costumerDTO the costumer to compare to
     * @return true if it's the same costumer, else false
     */
    public boolean equals (CostumerDTO costumerDTO){
        return socialSecurityNumber.equals(costumerDTO.getSocialSecurityNumber());
    }
}
