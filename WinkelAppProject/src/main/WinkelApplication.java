package main;

import connectivity.Dbmanager;
import connectivity.QueryManager;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1
 * @author Administrator
 * 
 * @version 2.0
 * @author Tjarco
 * 
 * @version 2.1
 * @author Bono
 * 
 * Functie getKlant() weggehaald, nutteloos gebleken.
 * 
 * @version 2.2
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * 
 * Security fix: gebruiker is nu niet meer standaard ingelogd. Werd eerst standard ingelogd als medewerker.
 * Fix imports
 */
public final class WinkelApplication {

    /** Define frame width, height and name*/
    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 800;
    public static final String NAME = "WinkelApplicatie";
    public static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance(Locale.getDefault());
    /** static fonts which are used within the application */
    public static final Font FONT_12_PLAIN = new Font("Calibri", Font.PLAIN, 12);
    public static final Font FONT_12_BOLD = new Font("Calibri", Font.BOLD, 12);
    public static final Font FONT_14_BOLD = new Font("Calibri", Font.BOLD, 14);
    public static final Font FONT_18_BOLD = new Font("Calibri", Font.BOLD, 18);
    public static final Font TITEL = new Font("Calibri", Font.PLAIN, 23);
    /** database manager */
    private Dbmanager dbManager;
    private QueryManager queryManager;
    /** models used in the application */
    private model.Basket basket;
    //** klant */
    private model.Gebruiker gebruiker;
    /** the main window */
    private JFrame mainWindow;
    /** singleton of the application */
    private static WinkelApplication instance = new WinkelApplication();
   //de achtergrondKleur voor de layout
    static public final Color BACKGROUND = Color.getHSBColor(0.7f, 0.1f, 0.2f);
    

    private WinkelApplication() {
    }

    public void initialize() {
        try {
            System.out.println("setting look and feel...");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting LookAndFeelClassName: " + e);
        }
        // create and initialize the connectivity
        System.out.println("Creating new DB manager...");
        dbManager = new Dbmanager();
        System.out.println("Opening connection to DB...");
        dbManager.openConnection();
        System.out.println("Creating new query manager...");
        queryManager = new QueryManager(dbManager);

        // create an empty basket
        System.out.println("Creating basket...");
        basket = new model.Basket();
    }

    public void startup() {
//        Gebruiker g = new Gebruiker();
//        g.setMedewerker(true);
//        Session.startSesionFor(g);
           
        System.out.println("Creating main window...");
        mainWindow = new JFrame(NAME);
       // Zet het scherm fullScreen
        System.out.println("Maximizing window...");
        mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);

        System.out.println("Adding listeners to window...");
        /** Make the window closing [x] button on the frame active */
        mainWindow.addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent event) 
            {
                shutdown();
            }
        });
        
        System.out.println("Setting layout...");
        mainWindow.getContentPane().setLayout(new BorderLayout());
        System.out.println("Creating new category list...");
        showPanel(new view.CategoryList());
        System.out.println("Showing main window...");
        mainWindow.setVisible(true);

    }

    public void showPanel(JPanel panel) {
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
        mainWindow.getContentPane().validate();
        mainWindow.getContentPane().repaint();
    }

    public void exit() {
        mainWindow.setVisible(false);
        shutdown();
    }

    private void shutdown() {
        mainWindow.dispose();
        dbManager.closeConnection();
    }

    /**
     * @return the instance of this class
     */
    public static WinkelApplication getInstance() {
        return instance;
    }

    /**
     * @return the queryManager
     */
    public static QueryManager getQueryManager() {
        return getInstance().queryManager;
    }

    /**
     * @return the basket
     */
    public static model.Basket getBasket() {
        return getInstance().basket;
    }
    
    public static String getCurrentTimeStamp() {
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    return strDate;
}

    public static void main(String[] args) {
        System.out.println("Starting main...");
        final WinkelApplication applicatie = WinkelApplication.getInstance();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("Initializing application...");
                    applicatie.initialize();
                    System.out.println("Application initialized");
                    System.out.println("-----------------------");
                    System.out.println("Starting up application...");
                    applicatie.startup();
                    System.out.println("Application started");
                    System.out.println("-----------------------");
                } catch (Exception e) {
                    System.out.println("Application" + applicatie.getClass().getName() + "failed to launch");
                    
                }
            }
        });
    }
}
