package model;


import java.awt.Font;
import java.util.List;
import main.WinkelApplication;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author Tjarco
 * @version 1.0
 *
 * Deze klasse wordt gebruikt om van verschillende statistieken conclusies te
 * trekken.
 */
public class StatistiekGenerator {

    private List<Product> producten;

    public StatistiekGenerator() {
        producten = main.WinkelApplication.getQueryManager().getAllProducts();
    }

    public String getOmzet() {

        String omzet;

        double omzetDouble = 0;
       
        for (int i = 0; i < producten.size(); i++) {

            int id = producten.get(i).getProduct_id();
            double prijs = producten.get(i).getPrijs();

            int verkocht = main.WinkelApplication.getQueryManager().getAantalVerkocht(id);

            omzetDouble += (verkocht * prijs);

        }

        omzet = main.WinkelApplication.CURRENCY.format(omzetDouble);

        return omzet;
    }

    public String getAantalKlanten() {
        String aantal;

        int i = main.WinkelApplication.getQueryManager().getGebruikersList().size();

        aantal = Integer.toString(i) + " gebruikers";

        return aantal;
    }

    public String getTotaalAantalProductenVerkocht() {
        String aantal;

        int a = 0;
        for (int i = 0; i < producten.size(); i++) {
            a += main.WinkelApplication.getQueryManager().getAantalVerkocht(producten.get(i).getProduct_id());
        }
        aantal = Integer.toString(a) + " producten";

        return aantal;
    }
    
    public String getAantalProductenVerkocht(Product p){
        String aantal;
        
        int a = main.WinkelApplication.getQueryManager().getAantalVerkocht(p.getProduct_id());
        
        aantal = Integer.toString(a) + "producten";
        
        return aantal;
    }
    
    public ChartPanel getProductenGrafiek(Product product){
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        Statistiek s = WinkelApplication.getQueryManager().getStatistiek(product.getProduct_id());
        
        
        pieDataset.setValue("Jonger dan 15", (double) s.getJonger_dan_15());
        pieDataset.setValue("Tussen 15 en 20", (double)s.getTussen_15_20());
        pieDataset.setValue("Tussen 20 en 25", (double)s.getTussen_20_25());
        pieDataset.setValue("Tussen 25 en 30", (double)s.getTussen_25_30());
        pieDataset.setValue("Tussen 30 en 50",(double) s.getTussen_30_50());
        pieDataset.setValue("Tussen 50 en 65", (double)s.getTussen_50_65());
        pieDataset.setValue("Ouder dan 65", (double)s.getOuder_dan_65());
              
        
        JFreeChart chart = ChartFactory.createPieChart("Welke leeftijdsgroepen kopen dit product?", pieDataset, true, true, true);
        chart.removeLegend();
        chart.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
        PiePlot p = (PiePlot)chart.getPlot();
       
       
        ChartPanel pane = new ChartPanel(chart);
        pane.setSize(450, 290);
        
        return pane;
    
    }
}
