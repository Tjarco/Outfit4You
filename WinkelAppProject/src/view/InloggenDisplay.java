package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.WinkelApplication;
import model.Gebruiker;
import model.Session;

/**
 * @version 1.0
 * @author Tjarco
 * 
 * Het inlog panel dat in de categorie panel komt te staan.
 * 
 * @version 1.1
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * 
 * Fixed unused imports
 * Cleaned source code
 */
public class InloggenDisplay extends JPanel {
    JLabel InUitlogLabel;
    private JLabel logIn;
    private JLabel logUit;
    private JLabel profiel;
    private static JLabel lIngelogdAls;
    private Font labels ;
    private GridBagConstraints gbc;
    private Gebruiker gebruiker;
    private JPanel gegevensPanel=new JPanel();
    
    //De kleuren voor de labels
    private final Color foreground = Color.getHSBColor(0.7f, 0.2f, 0.8f);
    private final Color foregroundHover = Color.getHSBColor(0.7f, 0.1f, 1f);
    
    public InloggenDisplay()
    {
        System.out.println("getting user");
        this.gebruiker = Session.getGebruiker();
        
        this.setLayout(new GridBagLayout());
        this.setBackground(WinkelApplication.BACKGROUND);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(0,70));
        
        gbc = new GridBagConstraints();
        gbc.weightx = 1d;
        labels = new Font("Arial", Font.BOLD, 16);
            
        System.out.println("adding title");
        addTitle();
        
        gbc.anchor = GridBagConstraints.LINE_END;
        
        System.out.println("adding profile");
        //addProfiel();
        System.out.println("in uitlog label");
        //addInUitLoglabel();
        addPanelInUitlog_profiel();
        System.out.println("checking if ingelogd");
        if(gebruiker != null && gebruiker.getId() > 0)
        {
            addIngelogdAls();
        }
        
        profiel.setVisible(Session.getGebruiker() == null ? false : true);
        this.setVisible(true);
        
    }
    
    // inlog label en uitlog label
    private void addInUitLoglabel(){
        InUitlogLabel = new JLabel();
        InUitlogLabel.setFont(labels);
        InUitlogLabel.setForeground(foreground);
        InUitlogLabel.addMouseListener(new ClickListener());
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx=7;
        gbc.gridy=0;
        if (Session.getGebruiker() != null && Session.getGebruiker().getId() > 0)
        {
            InUitlogLabel.setText("Uitloggen");
        }
        else
        {
            InUitlogLabel.setText("Inloggen");
        }
    }
    
    //Zet de label om het profiel te kunnen bekijken in de panel
    private void addProfiel(){
        profiel = new JLabel("Profiel");
        profiel.setFont(labels);
        profiel.setForeground(foreground);
        profiel.addMouseListener(new ClickListener());
        gbc.gridx =6;
        gbc.gridy =0;
    }
    
    // add de "ingelogd als: ___" label
    private void addIngelogdAls(){
        lIngelogdAls= new JLabel("Ingelogd als: "+ gebruiker.getVoornaam());
        lIngelogdAls.setFont(labels);
        lIngelogdAls.setForeground(foreground);
        lIngelogdAls.addMouseListener(new ClickListener());
        gbc.anchor = GridBagConstraints.LINE_START;
        //gbc.insets = new Insets(15,15,15,15);
        gbc.gridx =1;
        gbc.gridy =0;
        if (Session.getGebruiker() != null && Session.getGebruiker().getId() > 0)
        {
            lIngelogdAls.setVisible(true);
        }
        else
        {
            lIngelogdAls.setVisible(false);
        }
        add(lIngelogdAls, gbc);
    }
    
    public static void updateLoginDisplay(String gebruikersnaam){
        lIngelogdAls.setText("Ingelogd als: "+ gebruikersnaam);
    }
    
    //Zet die titel van de applicatie in het frame 
    private void addTitle(){
        JLabel titel = new JLabel("Winkelapplicatie");
        titel.setFont(main.WinkelApplication.TITEL);
        titel.setForeground(Color.white);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx =0;
        gbc.gridy =0;
        add(titel, gbc);
    }
    
    private void addPanelInUitlog_profiel(){
        JPanel panel = new JPanel();
        JLabel lEmpty=new JLabel("          ");
        addInUitLoglabel();
        addProfiel();
        gbc.gridx=7;
        gbc.gridy=0;
        panel.setSize(200, 5);
        panel.setOpaque(false);
        panel.add(profiel);
        panel.add(lEmpty);
        panel.add(InUitlogLabel);
        add(panel,gbc);
        
    }
    
    private class ClickListener implements MouseListener{

        public void mouseClicked(MouseEvent e) {
           
           if(e.getSource().equals(profiel)){
                main.WinkelApplication.getInstance().showPanel(new KlantGegevens());
           }
           else if (e.getSource().equals(InUitlogLabel))
           {
               if (Session.getGebruiker() != null && Session.getGebruiker().getId() > 0)
               {
                   //main.WinkelApplication.getInstance().showPanel(new MainMenu());
                   Session.stopSession();
                   InUitlogLabel.setText("Inloggen");
                   profiel.setVisible(false);
                   main.WinkelApplication.getInstance().showPanel(new CategoryList());
               }
               else {
                main.WinkelApplication.getInstance().showPanel(new InloggenRegistreren());
               }
           }
            
        }

        public void mousePressed(MouseEvent e) {
            //no event required
        }

        public void mouseReleased(MouseEvent e) {
            //no event required
        }
        
        //Change the color of the labels if the users hovers them.
        public void mouseEntered(MouseEvent e) {
           if(e.getSource().equals(logIn)){//------------
                logIn.setForeground(foregroundHover);
           }
           else if(e.getSource().equals(logUit)){//--------------
               logUit.setForeground(foregroundHover);
           }
           else if(e.getSource().equals(profiel)){
               profiel.setForeground(foregroundHover);
           }
           else if (e.getSource().equals(InUitlogLabel)){
               InUitlogLabel.setForeground(foregroundHover);
           }
        }

        public void mouseExited(MouseEvent e) {
           if(e.getSource().equals(logIn)){///--------------
                 logIn.setForeground(foreground);
           }
           else if(e.getSource().equals(logUit)){///--------
               logUit.setForeground(foreground);
           }
           else if(e.getSource().equals(profiel)){
               profiel.setForeground(foreground);
           }
           else if(e.getSource().equals(InUitlogLabel)){
               InUitlogLabel.setForeground(foreground);
           }
        }
    
    }    
}
