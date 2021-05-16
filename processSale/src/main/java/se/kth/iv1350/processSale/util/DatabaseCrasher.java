
package se.kth.iv1350.processSale.util;

/**
 * This class is used to simulate a database crash
 *
 * @author Ali Sahibi
 */
public class DatabaseCrasher {
    public static final String CRASH_DATABASE_IMMEDIATELY;
    
    static {
        CRASH_DATABASE_IMMEDIATELY = "0000000000000000000000000000000000000000"; 
    }
}
