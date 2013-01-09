package model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.WinkelApplication;

/**
 * @author Tjarco
 * 
 * De statastieken van een bepaald product
 */
public class Statistiek {
    private int id;
    private int product_id;
    private int jonger_dan_15;
    private int tussen_15_20;
    private int tussen_20_25;
    private int tussen_25_30;
    private int tussen_30_50;
    private int tussen_50_65;
    private int ouder_dan_65;

    
    public Statistiek(){}
    
    public Statistiek(int id, int product_id, int jonger_dan_15, int tussen_15_20, int tussen_20_25, int tussen_25_30, int tussen_30_50, int tussen_50_65, int ouder_dan_65) {
        this.id = id;
        this.product_id = product_id;
        this.jonger_dan_15 = jonger_dan_15;
        this.tussen_15_20 = tussen_15_20;
        this.tussen_20_25 = tussen_20_25;
        this.tussen_25_30 = tussen_25_30;
        this.tussen_30_50 = tussen_30_50;
        this.tussen_50_65 = tussen_50_65;
        this.ouder_dan_65 = ouder_dan_65;
    }
    
    public Statistiek(Gebruiker gebruiker, Product product){
        this.product_id = product.getProduct_id();
        setLeeftijdGroep(gebruiker);
    }

    /**
     * Bepaalt de leeftijd van een gebruiker door de huidige datum te vergelijken met de geboortedatum.
     * @param g 
     */
    private void setLeeftijdGroep(Gebruiker g){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now;
        Date birth;
        try {
            now = sdf.parse(WinkelApplication.getCurrentTimeStamp().substring(0,10));
            birth = sdf.parse(g.getGeboortedatum().substring(0,10));
            
            int sec = (int) (now.getTime()/1000 - birth.getTime()/1000);
            int min = (int) (sec/60);
            int uren = (int) (min/60);
            int dagen = (int) (uren/24);
            int weken = (int) (dagen/7);
            int jaren = (int) (weken/52);
            System.out.println(jaren);
            
            if(jaren<15){
                this.jonger_dan_15++;
            } 
            else if(jaren>=15 && jaren<20){
                this.tussen_15_20++;
            }
            else if(jaren>=20 && jaren<25){
                this.tussen_20_25++;
            }
            else if(jaren>=25 && jaren<30){
                this.tussen_25_30++;
            }
            else if(jaren>=30 && jaren <50){
                this.tussen_30_50++;
            }
            else if(jaren>=50 && jaren< 65){
                this.tussen_50_65++;
            }
            else{
                this.ouder_dan_65++;
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(Statistiek.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJonger_dan_15() {
        return jonger_dan_15;
    }

    public void setJonger_dan_15(int jonger_dan_15) {
        this.jonger_dan_15 = jonger_dan_15;
    }

    public int getOuder_dan_65() {
        return ouder_dan_65;
    }

    public void setOuder_dan_65(int ouder_dan_65) {
        this.ouder_dan_65 = ouder_dan_65;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getTussen_15_20() {
        return tussen_15_20;
    }

    public void setTussen_15_20(int tussen_15_20) {
        this.tussen_15_20 = tussen_15_20;
    }

    public int getTussen_20_25() {
        return tussen_20_25;
    }

    public void setTussen_20_25(int tussen_20_25) {
        this.tussen_20_25 = tussen_20_25;
    }

    public int getTussen_25_30() {
        return tussen_25_30;
    }

    public void setTussen_25_30(int tussen_25_30) {
        this.tussen_25_30 = tussen_25_30;
    }

    public int getTussen_30_50() {
        return tussen_30_50;
    }

    public void setTussen_30_50(int tussen_30_50) {
        this.tussen_30_50 = tussen_30_50;
    }

    public int getTussen_50_65() {
        return tussen_50_65;
    }

    public void setTussen_50_65(int tussen_50_65) {
        this.tussen_50_65 = tussen_50_65;
    }

    
}
