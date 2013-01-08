
package model;

/**
 * @author Tjarco
 * @version 1.0
 * 
 * De klasse die de actieve sessie bijhoudt. 
 */
public class Session {
    static private Gebruiker gebruiker = null;
    static private boolean isIngelogd;
    static private int ingelogdeKlantId=0;
    
    public static Gebruiker getKlant(){
        return gebruiker;
    }
    
    public static void startSesionFor(Gebruiker sessionGebruiker){
        gebruiker = sessionGebruiker;
    }
    
    public static void stopSession(){
        gebruiker = null;
    }
    public static void setIngelogd(boolean isIngelogd){
        Session.isIngelogd = isIngelogd;
    }
    public static boolean getIngelogd(){
        return isIngelogd;
    }
    public static void setIngelogdeGebruiker(int klantId){
        ingelogdeKlantId=klantId;
    }
    public static int getIngelogdeGebruiker(){
        return ingelogdeKlantId;
    }
}
