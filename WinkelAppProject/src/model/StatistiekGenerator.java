package model;

import java.util.List;

/**
 * @author Tjarco
 * @version 1.0
 * 
 * Deze klasse wordt gebruikt om van verschillende statistieken conclusies te trekken.
 */
public class StatistiekGenerator {
    
    public String getOmzet(){
        List<Product> producten = main.WinkelApplication.getQueryManager().getAllProducts();
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
}
