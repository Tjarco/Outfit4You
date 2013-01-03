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
import model.Gebruiker;

/**
 * @version 1
 * @author Administrator
 * 
 * @version 2.0
 * @author Tjarco
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
    private model.Gebruiker klant;
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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting LookAndFeelClassName: " + e);
        }
        // create and initialize the connectivity
        dbManager = new Dbmanager();
        dbManager.openConnection();
        queryManager = new QueryManager(dbManager);

        // create an empty basket
        basket = new model.Basket();
    }

    public void startup() {
        mainWindow = new JFrame(NAME);
       // Zet het scherm fullScreen
        mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
        klant = this.queryManager.getGebruiker(1);
        /** Make the window closing [x] button on the frame active */
        mainWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                shutdown();
            }
        });

        mainWindow.getContentPane().setLayout(new BorderLayout());
        showPanel(new view.CategoryList());

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
     * 
     * @return the Gebruiker
     */
   public static Gebruiker getKlant(){
       return getInstance().klant;
   }

    /**
     * @return the basket
     */
    public static model.Basket getBasket() {
        return getInstance().basket;
    }

    public static void main(String args[]) {
        final WinkelApplication applicatie = WinkelApplication.getInstance();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    applicatie.initialize();
                    applicatie.startup();
                } catch (Exception e) {
                    System.out.println("Application" + applicatie.getClass().getName() + "failed to launch");
                    
                }
            }
        });
    }
}
