package se.kth.iv1350.processSale.integration;

/**
 * This class holds all the information about the retail store where the
 * sale happens
 * 
 * @author Ali Sahibi
 */
public class StoreDTO {
    private final String storeName = "MaxiStore";
    private final String streetAdress = "CoolStreet 22";
    private final String city = "Townville";
    private final String zip = "922 12";

    /**
     * Getter for the store name
     * 
     * @return string with name of store
     */
    public String getStoreName() {
        return storeName;
    }
    
    /**
     * Getter for the store streetAdress
     * 
     * @return the street adress of the store
     */
    public String getStreetAdress() {
        return streetAdress;
    }
    
    /**
     * Getter for the city in which the store is located in
     * 
     * @return string with city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for the Zip code
     * 
     * @return string with zip code
     */
    public String getZip() {
        return zip;
    } 
}
