/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import main.WinkelApplication;

/**
 *
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * @version 1.0
 * @since 11-1-2013
 * 
 * Bestelling klasse. Hiermee kan een bestelling uit de QueryManager in deze klasse worden geladen en later makkelijk hieruit worden gehaald.
 * 
 * @author Bono
 * @version 1.1
 * 
 * Status toegevoegd
 */
public class Bestelling {
    private int order_id;
    private String naam;
    private String adres;
    private String postcode;
    private String woonplaats;
    private String notes;
    private String betaalmethode;
    private String datum;
    private String code;
    private List<String> products;
    boolean status; //Geeft aan of de bestelling betaald is

    public Bestelling() {
        
    }

    public Bestelling(int order_id, String naam, String adres, String postcode, String woonplaats, String notes, String betaalmethode, String code, List<String> products, boolean status) {
        this.order_id = order_id;
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.notes = notes;
        this.betaalmethode = betaalmethode;
        this.code = code;
        this.products = products;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getNaam() {
        return naam;
    }

    public String getAdres() {
        return adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String getNotes() {
        return notes;
    }

    public String getBetaalmethode() {
        return betaalmethode;
    }

    public String getDatum() {
        return datum;
    }

    public String getCode() {
        return code;
    }

    public List<String> getProducts() {
        return products;
    }
    
    /**
     * @param status boolean geeft aan of de bestelling betaald is
     */
    public void setStatus(boolean status)
    {
        this.status = status;
    }

    /**
     * @return boolean status, geeft aan of de bestelling betaald is
     */
    public boolean getStatus()
    {
        return this.status;
    }
}
