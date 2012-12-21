/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connectivity.QueryManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import main.WinkelApplication;
import model.Category;
import model.Product;

/**
 *
 * @author Alex
 */
public class KlantGegevens extends JPanel{
    JLabel lTitel=new JLabel("Profiel");
    
    JLabel lNaam=new JLabel("Naam");
    JTextField tfNaam=new JTextField();
    JButton bNaamWijzigen=new JButton("Wijzigen");
    //JPanel gegevensPanel=new JPanel();
    
    
    JLabel rest=new JLabel("De rest komt nog, ben nog niet klaar zoals dit wel te zien is. (Alex)");
    
    
    private GridBagConstraints gbc;
    
    JPanel items;
    JButton jbTerug;
    
    public KlantGegevens(){
        
        super();
        setLayout(new BorderLayout());
        initComponents();
        this.setBackground(Color.white);
        this.setOpaque(true);
    
    
    
    }

    /** create the gui for this page */
    private void initComponents() {
        initPanels();
        addInlog();
       // addBasket(); 
        addBackButton();
        add(addGegevensPanel());
    }
    
    //Initialiseerd de panels om de producten weer te kunnen geven
    private void initPanels(){
        //Maak de items panel aan
        items = new JPanel(new BorderLayout());
        add(items, BorderLayout.LINE_START);
                
        //Maak de productdetails panel aan    
        JPanel productDetails = new JPanel(new GridBagLayout());
        productDetails.setBackground(Color.white);
        productDetails.setOpaque(true);                
        this.add(productDetails, BorderLayout.CENTER);
    }

   /**
    * Voegt de back button om naar het hoofdmenu te gaan. 
    * (Alleen voor de medewerkers)
    */
    private void addBackButton(){
        JPanel down = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        //de dimensie van de panel (de breedte is nul, want deze wordt door de borderlayout bepaald)
        down.setPreferredSize(new Dimension(0,70));
        down.setBackground(WinkelApplication.BACKGROUND);
        down.setOpaque(true);
        
        
        jbTerug = new JButton("Terug");
        jbTerug.setFont(new Font("Calibri", Font.PLAIN, 18));
        jbTerug.setIcon(new ImageIcon(getClass().getResource("/pictures/backButton.png")));
        jbTerug.setContentAreaFilled(false);
        jbTerug.setBorderPainted(false);
        jbTerug.setFocusPainted(false);
        jbTerug.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //jbTerug.addMouseListener(new CategoryList.BackButtonListener());       
        down.add(jbTerug);        
        
        add(down, BorderLayout.PAGE_END);
    }

    private void addBasket() {
        BasketDisplay basket = new BasketDisplay();
        this.add(basket, BorderLayout.EAST);
    }
    
    /**
     * Voegt de inlog panel toe
     */
    private void addInlog(){
        InloggenDisplay logIn = new InloggenDisplay();
        this.add(logIn, BorderLayout.NORTH);
    }
    private JPanel addGegevensPanel(){
        JPanel gegevensPanel=new JPanel();
        //gegevensPanel.setLayout(gbc);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty=1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx =2;
        gbc.gridy =2;
        
        
        gegevensPanel.add(lTitel,gbc);
        
        gbc.gridy =3;
        gegevensPanel.add(lNaam,gbc);
        gegevensPanel.add(tfNaam,gbc);
        gbc.gridx =3;
        gegevensPanel.add(bNaamWijzigen,gbc);
        gbc.gridx =4;
        
        gegevensPanel.add(rest);
        revalidate();
        repaint();
        
        return gegevensPanel;
    }
}
