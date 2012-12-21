/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tjarco
 */
public class Klant {
    private int klantId;
    private String naam;
    private String adres;
    private String postcode;
    private String woonplaats;

    /**
     * @return the klantId
     */
    
    public Klant(){}
    
    public Klant(int klantId, String naam, String adres, String postcode, String woonplaats){
        this.klantId = klantId;
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
    }
    
    public int getKlantId() {
        return klantId;
    }

    /**
     * @param klantId the klantId to set
     */
    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    /**
     * @return the naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * @param naam the naam to set
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * @return the adres
     */
    public String getAdres() {
        return adres;
    }

    /**
     * @param adres the adres to set
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the Woonplaats
     */
    public String getWoonplaats() {
        return woonplaats;
    }

    /**
     * @param Woonplaats the Woonplaats to set
     */
    public void setWoonplaats(String Woonplaats) {
        this.woonplaats = Woonplaats;
    }
            
    
}
