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
    private int id;
    private int huisnummer;
    
    //private String telefoonnummer;
    private String datum_aangemaakt;
    private String datum_gewijzigd;
    private String datum_laatst_ingelogd;
    private String wachtwoord;
    private String email;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String straatnaam;
    private String postcode;
    private String woonplaats;
    private String geboortedatum;
    
    private boolean isMedewerker;
    private boolean isManager;
    private boolean isActief;

    public Gebruiker()
    {

    }
    
    public Gebruiker(int id, int huisnummer, /*String telefoonnummer,*/  String datum_aangemaakt, String datum_gewijzigd, String datum_laatst_ingelogd, String wachtwoord, String email, String voornaam, String tussenvoegsel, String achternaam, String straatnaam, String postcode, String woonplaats, String geboortedatum, boolean isMedewerker, boolean isManager, boolean isActief)
    {
        this.id = id;
        this.huisnummer = huisnummer;
        //this.telefoonnummer = telefoonnummer;
        this.datum_aangemaakt = datum_aangemaakt;
        this.datum_gewijzigd = datum_gewijzigd;
        this.datum_laatst_ingelogd = datum_laatst_ingelogd;
        this.wachtwoord = wachtwoord;
        this.email = email;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.straatnaam = straatnaam;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.geboortedatum = geboortedatum;
        this.isMedewerker = isMedewerker;
        this.isManager = isManager;
        this.isActief = isActief;
    }  

    /**
     * @return gebruiker's id
     */
    public int getId() {
        return id;
    }

    /**
     * @return gebruiker's huisnummer
     */
    public int getHuisnummer() {
        return huisnummer;
    }

    /**
     * @param huisnummer huisnummer van de gebruiker
     */
    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }
    
    
    /**
     * @return gebruiker's telefoonnummer
     */
    /*public String getTelefoonnummer(){
        return telefoonnummer;
    }
    
    /**
     * @param telefoonnummer telefoonnummer van de gebruiker
     */
    /*public void setTelefoonnummer(String telefoonnummer){
        this.telefoonnummer = telefoonnummer;
    }
    
    /**
     * @return gebruiker's datum_aangemaakt
     */
    public String getDatum_aangemaakt() {
        return datum_aangemaakt;
    }

    /**
     * @param datum_aangemaakt datum waarop gebruiker is aangemaakt
     */
    public void setDatum_aangemaakt(String datum_aangemaakt) {
        this.datum_aangemaakt = datum_aangemaakt;
    }

    /**
     * @return gebruiker's datum_gewijzigd
     */
    public String getDatum_gewijzigd() {
        return datum_gewijzigd;
    }

    /**
     * @param datum_gewijzigd datum waarop gebruiker als laatst is gewijzigd
     */
    public void setDatum_gewijzigd(String datum_gewijzigd) {
        this.datum_gewijzigd = datum_gewijzigd;
    }

    /**
     * @return gebruiker's datum_laatst_ingelogd
     */
    public String getDatum_laatst_ingelogd() {
        return datum_laatst_ingelogd;
    }

    /**
     * @param datum_laatst_ingelogd datum waarop de gebruiker als laatst is ingelogd
     */
    public void setDatum_laatst_ingelogd(String datum_laatst_ingelogd) {
        this.datum_laatst_ingelogd = datum_laatst_ingelogd;
    }

    /**
     * @return the gebruiker's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email email van de gebruiker
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the gebruiker's tussenvoegsel
     */
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    /**
     * @param tussenvoegsel tussenvoegsel van de gebruiker
     */
    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    /**
     * @return the gebruiker's straatnaam
     */
    public String getStraatnaam() {
        return straatnaam;
    }

    /**
     * @param straatnaam gebruikers straatnaam
     */
    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    /**
     * @return the gebruiker's geboortedatum
     */
    public String getGeboortedatum() {
        return geboortedatum;
    }

    /**
     * @param geboortedatum geboortedatum gebruiker
     */
    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    /**
     * @return boolean isMedewerker
     */
    public boolean isMedewerker() {
        return isMedewerker;
    }

    /**
     * @param isMedewerker boolean
     */
    public void setMedewerker(boolean isMedewerker) {
        this.isMedewerker = isMedewerker;
    }

    /**
     * @return boolean isManager
     */
    public boolean isManager() {
        return isManager;
    }

    /**
     * @param isManager boolean isManager
     */
    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    /**
     * @return boolean isActief
     */
    public boolean isActief() {
        return isActief;
    }

    /**
     * @param isActief boolean isActief
     */
    public void setActief(boolean isActief) {
        this.isActief = isActief;
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
     * @return the woonplaats
     */
    public String getWoonplaats() {
        return woonplaats;
    }

    /**
     * @param woonplaats the woonplaats to set
     */
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    /**
     * @return the achternaam
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * @param achternaam the achternaam to set
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * @return the voornaam
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * @param voornaam the voornaam to set
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * @return the wachtwoord
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * @param wachtwoord the wachtwoord to set
     */
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}