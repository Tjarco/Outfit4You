package model;

import java.util.List;

/**
 * @author Tjarco
 * @version 1.0
 * 
 * Deze klasse wordt gebruikt om van verschillende statistieken conclusies te trekken.
 */
public class StatistiekGenerator {
    private List<Product> producten;
    
    public StatistiekGenerator() {
        producten = main.WinkelApplication.getQueryManager().getAllProducts();
 }
    public String getOmzet(){
        
        String omzet;
        
        double omzetDouble =0;
        for(int i = 0; i < producten.size(); i++){
            
            int id = producten.get(i).getProduct_id();
            double prijs = producten.get(i).getPrijs();
            
            int verkocht = main.WinkelApplication.getQueryManager().getAantalVerkocht(id);
                        
            omzetDouble += (verkocht * prijs);
            
        }
        
        omzet = main.WinkelApplication.CURRENCY.format(omzetDouble);
        
        return omzet;
    }
    
    public String getAantalKlanten(){
        String aantal;
        
        int i = main.WinkelApplication.getQueryManager().getGebruikersList().size();
        
        aantal = Integer.toString(i) + " gebruikers";
        
        return aantal;
    }
    
    public String getAantalProductenVerkocht(){
        String aantal;
        
        int a = 0;
        for(int i =0; i<producten.size(); i++){
            a += main.WinkelApplication.getQueryManager().getAantalVerkocht(producten.get(i).getProduct_id());
        }
        aantal = Integer.toString(a) + " producten";
        
        return aantal;
    }
    
    
}
