package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.util.Percentage;

/**
 * This is the class responsible for making calls to the database
 * containing costumer data
 *
 * @author Ali Sahibi
 */
public class CostumerRegistry {
    private static final CostumerRegistry costumerReg = new CostumerRegistry ();
    private final List<CostumerDTO> costumerData = new ArrayList<>();
    
    /**
     * Getter method for the CostumerRegistry object
     * 
     * @return the CostumerRegistry object
     */
    public static CostumerRegistry getCostumerRegistry (){
        return costumerReg;
    }
    
    /** 
     * Constructor for the class CostumerRegistry
     */
    private CostumerRegistry() {
        retrieveData ();
    }
    
    /** 
     * This method searches for a costumer based on the given costumerDTO,
     * if there's a match it sets the correct base discount for the searched costumer
     * if there's no match it sets the base discount for the customer to 0 percent
     * 
     * @param customerInformation used to search for a match
     */
    public void searchCostumer (CostumerDTO customerInformation){
        if (isCostumerInRegistry(customerInformation)){          
            CostumerDTO matchedCostumerInDataBase = getCostumerFromRegistry (customerInformation);
            customerInformation.setDiscount (matchedCostumerInDataBase.getBaseDiscountForCustomer());
        }
        else {
            Percentage zeroPercent = new Percentage (0);
            customerInformation.setDiscount (zeroPercent);
        }   
    }
    
    /**
     * This method determines wether the searched for costumer does exist
     * in the database
     * 
     * @param costumerInformation the searched for costumerDTO
     * @return true if costumer is in database, else false
     */
    private boolean isCostumerInRegistry (CostumerDTO costumerInformation){
        boolean isCostumerInRegistry = false;
        for (CostumerDTO customer : costumerData){
            if (costumerInformation.equals(customer)){
                isCostumerInRegistry = true;
            }
        }
        return isCostumerInRegistry;
    }
    
    /**
     * This method searches for a costumer in the database
     * 
     * @param costumerInformation the costumer that is searched for
     * @return if the costumer is found the costumerDTO is returned, else null 
     */
    private CostumerDTO getCostumerFromRegistry (CostumerDTO costumerInformation){
        for (CostumerDTO costumer : costumerData){
            if (costumerInformation.equals(costumer)){
                return costumer;
            }
        }
        return null;
    }
    
    /**
     * This method makes a call to the database to retrieve the data and fills
     * the costumerData list with it.
     * 
     * In this case we have created fake values that are read from a fake database
     */
    private void retrieveData (){
        Percentage baseDiscountCostumer0 = new Percentage (5);
        CostumerDTO costumer0 = new CostumerDTO ("John Doe",
        "19880509", "DoeTown evergreenstreet 22", "2391000102", baseDiscountCostumer0);
        costumerData.add (costumer0);
        
        Percentage baseDiscountCostumer1 = new Percentage (2);
        CostumerDTO costumer1 = new CostumerDTO ("Mary Jane", "19780123",
        "Janetown cubestreet 11", "091023111", baseDiscountCostumer1);
        costumerData.add(costumer1);
    }
}
