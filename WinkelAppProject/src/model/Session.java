
package model;

/**
 * @author Tjarco
 * @version 1.0
 * 
 * De klasse die de actieve sessie bijhoudt. 
 *
 * @author Bono
 * @version 1.1
 * 
 * Klant vervangen voor gebruiker
 * Niet nuttige methodes weggehaald
 *
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * @version 1.2
 * 
 * Controleert of gebruiker is ingelogd
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
    
    /**
     * Stopt de sessie van de gebruiker
     */
    public static void stopSession(){
        gebruiker = null;
    }
    /**
     * Checkt of de gebruiker momenteel is ingelogd.
     * @return boolean, true wanneer gebruiker is ingelogd, false wanneer gebruiker niet is ingelogd
     */
    public boolean isIngelogd() {
        if(gebruiker == null) {
            return false;
        }else{
            return true;
        }  
    }
}
