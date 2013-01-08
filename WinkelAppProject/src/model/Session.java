
package model;

/**
 * @author Tjarco
 * @version 1.0
 * 
 * De klasse die de actieve sessie bijhoudt. 
 */

/**
 * @author Bono
 * @version 1.1
 * 
 * Klant vervangen voor gebruiker
 * Niet nuttige methodes weggehaald
 */
public class Session {
    static private Gebruiker gebruiker = null;
    
    public static Gebruiker getGebruiker(){
        return gebruiker;
    }
    
    /**
     * Start de sessie voor de meegegeven gebruiker.
     * @param sessionGebruiker 
     */
    public static void startSesionFor(Gebruiker sessionGebruiker){
        gebruiker = sessionGebruiker;
    }
    
    public static void stopSession(){
        gebruiker = null;
    }
}
