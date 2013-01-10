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

    public String[] getAantalProductenInfo(Product p) {
        String[] info = new String[2];

        Statistiek s = main.WinkelApplication.getQueryManager().getStatistiek(p.getProduct_id());

        int aantal = s.getTotaal_verkocht();
        double prijs = p.getPrijs();
        double omzet = aantal * prijs;

        info[0] = Integer.toString(aantal) + " producten";
        info[1] = main.WinkelApplication.CURRENCY.format(omzet);

        return info;
    }

    public ChartPanel getProductenGrafiek(Product product) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        Statistiek s = WinkelApplication.getQueryManager().getStatistiek(product.getProduct_id());

        pieDataset.setValue("Jonger dan 15", (double) s.getJonger_dan_15());
        pieDataset.setValue("Tussen 15 en 20", (double) s.getTussen_15_20());
        pieDataset.setValue("Tussen 20 en 25", (double) s.getTussen_20_25());
        pieDataset.setValue("Tussen 25 en 30", (double) s.getTussen_25_30());
        pieDataset.setValue("Tussen 30 en 50", (double) s.getTussen_30_50());
        pieDataset.setValue("Tussen 50 en 65", (double) s.getTussen_50_65());
        pieDataset.setValue("Ouder dan 65", (double) s.getOuder_dan_65());


        JFreeChart chart = ChartFactory.createPieChart("Welke leeftijdsgroepen kopen dit product?", pieDataset, true, true, true);
        chart.removeLegend();
        chart.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
        PiePlot p = (PiePlot) chart.getPlot();


        ChartPanel pane = new ChartPanel(chart);
        pane.setSize(450, 280);

        return pane;

    }

    public ChartPanel getLeeftijdsgroepGrafiek(String leeftijdsgroep) {

        int leeftijd = 0;
        if (leeftijdsgroep.equals("Jonger dan 15")) {
            leeftijd = 0;
        } else if (leeftijdsgroep.equals("Tussen de 15 en de 20 jaar")) {
            leeftijd = 1;
        } else if (leeftijdsgroep.equals("Tussen de 20 en de 25 jaar")) {
            leeftijd = 2;
        } else if (leeftijdsgroep.equals("Tussen de 25 en de 30 jaar")) {
            leeftijd = 3;
        } else if (leeftijdsgroep.equals("Tussen de 30 en de 50 jaar")) {
            leeftijd = 4;
        } else if (leeftijdsgroep.equals("Tussen de 50 en de 65 jaar")) {
            leeftijd = 5;
        } else if (leeftijdsgroep.equals("Ouder dan 65")) {
            leeftijd = 6;
        }

        List<Statistiek> s = main.WinkelApplication.getQueryManager().getStatistieken();


        DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        System.out.println(s.size());

        for (int i = 0; i >= producten.size(); i++) {
            int product_id = producten.get(i).getProduct_id();            
            System.out.println("test");
            
            int a;
            for( a = 0; a<=s.size(); a++){
                if(s.get(a).getProduct_id() == product_id){
                    break;
                }
            }

            switch (leeftijd) {
                case 0:
                    pieDataset.setValue(producten.get(product_id).getNaam(), (double) s.get(a).getJonger_dan_15());
                    break;
                case 1:
                    pieDataset.setValue(producten.get(product_id).getNaam(), (double) s.get(a).getTussen_15_20());
                    break;
                case 2:
                    pieDataset.setValue(producten.get(product_id).getNaam(), (double) s.get(a).getTussen_20_25());
                    break;
                case 3:
                    pieDataset.setValue(producten.get(product_id).getNaam(), (double) s.get(a).getTussen_25_30());
                    break;
                case 4:
                    pieDataset.setValue(producten.get(product_id).getNaam(), (double) s.get(a).getTussen_30_50());
                    break;
                case 5:
                    pieDataset.setValue(producten.get(product_id).getNaam(), (double) s.get(a).getTussen_50_65());
                    break;
                case 6:
                    pieDataset.setValue(producten.get(product_id).getNaam(), (double) s.get(a).getOuder_dan_65());
                    break;
                default: pieDataset.setValue("", 0); break;
            }
        }

        JFreeChart chart = ChartFactory.createPieChart("Wat koopt deze leeftijdsgroep?", pieDataset, true, true, true);
        chart.removeLegend();
        chart.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
        PiePlot p = (PiePlot) chart.getPlot();

        ChartPanel pane = new ChartPanel(chart);
        pane.setSize(400, 280);

        return pane;
    }
}
