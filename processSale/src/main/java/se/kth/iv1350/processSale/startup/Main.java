package se.kth.iv1350.processSale.startup;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.IntegrationCreator;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.view.View;

/**
 * This is the main class of the program
 *
 * @author Ali Sahibi
 */
public class Main {

    /**
     * This the main method of the class
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IntegrationCreator integrationCreator = new IntegrationCreator();
        Printer printer = new Printer ();
        Controller contr = new Controller(integrationCreator, printer);
        View view = new View (contr);  
        view.hardCodedCalls();
    }
}
