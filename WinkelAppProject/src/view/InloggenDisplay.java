
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.WinkelApplication;
import model.Gebruiker;
import model.Session;

/**
 * @version 1.0
 * @author Tjarco
 * 
 * Het inlog panel dat in de categorie panel komt te staan.
 */
public class InloggenDisplay extends JPanel {
    JLabel InUitlogLabel;
    private JLabel logIn;
    private JLabel logUit;
    private JLabel profiel;
    private Font labels ;
    private GridBagConstraints gbc;
    private boolean isingelogd;
    
    
        private JPanel gegevensPanel=new JPanel();
        
    //private InloggenRegistreren inloggenRegistreren=new InloggenRegistreren();
    
    //De kleuren voor de labels
    private final Color foreground = Color.getHSBColor(0.7f, 0.2f, 0.8f);
    private final Color foregroundHover = Color.getHSBColor(0.7f, 0.1f, 1f);
    

    
    
    //Zet de componenten op de panel, als de gebruiker als is ingelogd kan hij/zij zijn/haar profiel bekijken of
    //uitloggen.
    public InloggenDisplay(){
        
        
        this.setLayout(new GridBagLayout());
        this.setBackground(WinkelApplication.BACKGROUND);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(0,70));
        
        gbc = new GridBagConstraints();
        gbc.weightx = 1d;
        labels = new Font("Arial", Font.BOLD, 16);
            
        addTitle();
        gbc.anchor = GridBagConstraints.LINE_END;
            addProfiel();
        addInUitLoglabel();
            profiel.setVisible(Session.getIngelogd());
           // if (Session.getIngelogd()==false){profiel.setVisible(false);}
            //else {profiel.setVisible(true);}
        
        //addInloggen();
        //addProfiel();
        //addUitloggen();
        /*
        if(Session.getKlant() == null){
            addInloggen();
        }
        else{
           addInloggen();
           addProfiel(); 
           addUitloggen();  
           logIn.setVisible(false);
        }
       */
        this.setVisible(true);
        
    }
    
    // inlog label en uitlog label
    private void addInUitLoglabel(){
        InUitlogLabel =new JLabel();
        InUitlogLabel.setFont(labels);
        InUitlogLabel.setForeground(foreground);
        InUitlogLabel.addMouseListener(new ClickListener());
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx=2;
        gbc.gridy=0;
        
        if (Session.getIngelogd()==true){
            InUitlogLabel.setText("Uitloggen");
        }
        else if (Session.getIngelogd()==false){InUitlogLabel.setText("Inloggen");}
                
        
        
        add(InUitlogLabel, gbc);
        
    }
    
    
    //Zet de Inlog label in de panel /// ----------------------------
    private void addInloggen(){
        logIn = new JLabel("Inloggen");
        logIn.setFont(labels);
        logIn.setForeground(foreground);
        logIn.addMouseListener(new ClickListener());
        logIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx=1;
        gbc.gridy=0;
        add(logIn, gbc);
    }
    
    //Zet de uitlog label in de panel  /////--------------------------
    private void addUitloggen(){
        logUit = new JLabel("Uitloggen");
        logUit.setFont(labels);
        logUit.setForeground(foreground);
        logUit.addMouseListener(new ClickListener());
        gbc.insets= new Insets(15,15,15,15);
        gbc.gridx = 3;
        gbc.gridy =0;
        gbc.weightx = 0;
        add(logUit,gbc);
    }
   
    //Zet de label om het profiel te kunnen bekijken in de panel
    private void addProfiel(){
        profiel = new JLabel("Profiel");
        profiel.setFont(labels);
        profiel.setForeground(foreground);
        profiel.addMouseListener(new ClickListener());
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx =1;
        gbc.gridy =0;
        add(profiel, gbc);
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
    public void loadGegevensPanel(){///-----------------
        gegevensPanel.setSize(500,500);
        gegevensPanel.setLayout(new FlowLayout());
        JLabel lNaam=new JLabel("Naam");
        
        JTextField tfNaam=new JTextField();
        
        JButton bWijzigenNaam=new JButton("Wijzigen");
        
        JLabel rest=new JLabel("Dit word nog aangevuld, ben nog niet klaar (Alex)");
        
        gegevensPanel.add(lNaam);
        gegevensPanel.add(tfNaam);
        gegevensPanel.add(bWijzigenNaam);
        
        gegevensPanel.add(rest);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx =2;
        gbc.gridy =2;
        
        add(gegevensPanel,gbc);
    }
    
    private class ClickListener implements MouseListener{

        public void mouseClicked(MouseEvent e) {
           
            if(e.getSource().equals(logIn)){ ///// ----------------
                main.WinkelApplication.getInstance().showPanel(new InloggenRegistreren());
           }
           else if(e.getSource().equals(logUit)){//-------------------
               Session.stopSession();
               logUit.setVisible(false);
               profiel.setVisible(false);
               logIn.setVisible(true);
           }
           else if(e.getSource().equals(profiel)){
                main.WinkelApplication.getInstance().showPanel(new KlantGegevens());
           }
           else if (e.getSource().equals(InUitlogLabel)){
               if (Session.getIngelogd()==true){
                
                   //main.WinkelApplication.getInstance().showPanel(new MainMenu());
                Session.setIngelogd(false);
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
