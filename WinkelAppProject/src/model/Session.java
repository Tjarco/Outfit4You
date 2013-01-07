/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tjarco
 */
public class Session {
    static private Gebruiker klant = null;
    static private boolean isIngelogd;
    static private int ingelogdeKlantId=0;
    
    public static Gebruiker getKlant(){
        return klant;
    }
    
    public static void startSesionFor(Gebruiker sessionKlant){
        klant = sessionKlant;
    }
    
    public static void stopSession(){
        klant = null;
    }
    public static void setIngelogd(boolean isIngelogd2){
        isIngelogd=isIngelogd2;
    }
    public static boolean getIngelogd(){
        return isIngelogd;
    }
    public static void setIngelogdeKlant(int klantId){
        ingelogdeKlantId=klantId;
    }
    public static int getIngelogdeKlant(){
        return ingelogdeKlantId;
    }
}
