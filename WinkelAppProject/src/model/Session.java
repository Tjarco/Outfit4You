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
    static private Klant klant = null;
    static private boolean isIngelogd;
    
    public static Klant getKlant(){
        return klant;
    }
    
    public static void startSesionFor(Klant sessionKlant){
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
}
