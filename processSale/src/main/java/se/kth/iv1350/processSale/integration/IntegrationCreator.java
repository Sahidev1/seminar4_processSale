package se.kth.iv1350.processSale.integration;

/**
 * This class creates one instance of ExternalInventory, ExternalAccounting, 
 * ItemRegistry, and CostumerRegistry
 * 
 * @author Ali Sahibi
 */
public class IntegrationCreator {
    private final ExternalInventory externalInventory;
    private final ExternalAccounting externalAccounting;
    private final CostumerRegistry costumerRegistry;
    private final ItemRegistry itemRegistry;

    /** 
     * Constructor for the class IntegrationCreator
     * 
     * When the constructor is called it creates four new
     * objects tasked with interacting with external systems
     */
    public IntegrationCreator() {
        this.externalInventory = new ExternalInventory();
        this.externalAccounting = new ExternalAccounting();
        this.costumerRegistry = new CostumerRegistry();
        this.itemRegistry = new ItemRegistry();
    }
    
    /**
     * Getter for the externalInventory
     * 
     * @return the externalInventory
     */
    public ExternalInventory getExternalInventory() {
        return externalInventory;
    }

    /**
     * Getter for the externalAccounting
     * 
     * @return the externalAccounting
     */
    public ExternalAccounting getExternalAccounting() {
        return externalAccounting;
    }

    /**
     * Getter for the costumerRegistry
     * 
     * @return the costumerRegistry
     */
    public CostumerRegistry getCostumerRegistry() {
        return costumerRegistry;
    }
    
    /**
     * Getter for the itemRegistry
     * 
     * @return the itemRegistry
     */
    public ItemRegistry getItemRegistry() {
        return itemRegistry;
    }    
}
