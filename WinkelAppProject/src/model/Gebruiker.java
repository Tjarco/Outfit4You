/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tjarco
 */
public class Gebruiker {
    private int klantId;
    private String voornaam;
    private String achternaam;
    private String wachtwoord;  
    private String adres;
    private String postcode;
    private String woonplaats;
    
    private boolean isMedewerker;
    private boolean isManger;
    private boolean isActive;
    
    public Gebruiker(){};

    public Gebruiker(int klantId, String voorNaam, String achternaam, String wachtwoord, String adres, String postcode, String woonplaats, boolean isMedewerker, boolean isManger, boolean isActive) {
        this.klantId = klantId;
        this.voornaam = voorNaam;
        this.achternaam = achternaam;
        this.wachtwoord = wachtwoord;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.isMedewerker = isMedewerker;
        this.isManger = isManger;
        this.isActive = isActive;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsManger() {
        return isManger;
    }

    public void setIsManger(boolean isManger) {
        this.isManger = isManger;
    }

    public boolean isIsMedewerker() {
        return isMedewerker;
    }

    public void setIsMedewerker(boolean isMedewerker) {
        this.isMedewerker = isMedewerker;
    }

    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voorNaam) {
        this.voornaam = voorNaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    
    
    
}
