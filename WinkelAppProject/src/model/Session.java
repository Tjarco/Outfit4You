
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
    static public Gebruiker gebruiker = null;
    static private boolean isIngelogd;
    
    public static Gebruiker getGebruiker(){
        return gebruiker;
    }
    
    /**
     * Start de sessie voor de meegegeven gebruiker.
     * @param sessionGebruiker 
     */
    public static void startSesionFor(Gebruiker sessionGebruiker){
        Session.isIngelogd = true;
        gebruiker = sessionGebruiker;
    }
    
    /**
     * Stopt de sessie van de gebruiker
     */
    public static void stopSession(){
        gebruiker = null;
    }

    public Session() {
        Session.isIngelogd = false;
    }
    /**
     * Checkt of de gebruiker momenteel is ingelogd.
     * @return boolean, true wanneer gebruiker is ingelogd, false wanneer gebruiker niet is ingelogd
     */
    public static boolean isIngelogd() {
        if(Session.isIngelogd == false) {
            return false;
        }else{
            return true;
        }  
    }
}
