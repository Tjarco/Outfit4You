/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import main.WinkelApplication;
import model.Gebruiker;
import model.Session;

/**
 * @version 1.0
 * @author Tjarco
 * 
 * Een klasse die het overzicht geeft van alle klanten in de database. Er kunnen vanaf hier nieuwe 
 * klanten worden toegevoegd. Ook kunenn klanten gewijzigd worden.
 *
 * @version 1.1
 * @author Bono
 * 
 * Klanten verwijderen toegevoegd
 * updateTable() method toegevoegd om sneller data toe te voegen/verversen
 * 
 * @version 1.2
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * 
 * Fixed unused imports
 * 
 */
public class GebruikerOverzicht extends javax.swing.JPanel {

    Gebruiker gebruiker;
    /**
     * Maakt het form aan en zet de data in de tabel
     */
    public GebruikerOverzicht() 
    {
        initComponents();

        updateTable(false);
        
        jtZoekveld.getDocument().addDocumentListener(new ZoekListener());
        jtZoekveld.addKeyListener(new SnelToetsListener());
        
        if(!Session.getGebruiker().isManager()){
            jbMedewerkerToevoegen.setVisible(false);
        }
    }
    
    /**
     * @version 1.0
     * @author Bono
     * @param refresh Specificeer of de tabel leeg gemaakt moet worden voordat er nieuwe data in komt.
     * 
     * <p>Gebruik deze methode om JTable te verversen met nieuwe data.<p>
     */
    private void updateTable(boolean refresh)
    {        
        List<Gebruiker> klanten = WinkelApplication.getQueryManager().getGebruikersList();
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        
        //Tabel leeg maken
        if(refresh)
        {
            model.setRowCount(0);
        }
        
        for (Gebruiker klant : klanten) 
        {
            model.addRow(new Object[]{new Integer(klant.getId()),
            klant.getVoornaam() + (!klant.getTussenvoegsel().equals("") ? " " + klant.getTussenvoegsel() + " " : " ") + klant.getAchternaam(),
            klant.getEmail(),
            klant.getGeboortedatum(),
            klant.getStraatnaam() + " " + klant.getHuisnummer(),
            klant.getPostcode(),
            klant.getWoonplaats(),
            klant.isMedewerker()});
        }
    }
    
    /**
     * De listener voor sneltoetsen.
     * Er kan tijdens het typen in het zoekveld met de toetsen naar boven en beneden een rij geselecteerd worden
     */
    private class SnelToetsListener implements KeyListener{

        public void keyTyped(KeyEvent e) {
            //Niet nodig
        }

        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                int row = jTable1.getSelectedRow();
                
                try{
                    jTable1.setRowSelectionInterval(row+1, row+1);
                }catch(Exception ex){
                    //Geen actie, de laatse rij is geselecteerd
                }                       
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP){
               int row = jTable1.getSelectedRow();
                
                try{
                    jTable1.setRowSelectionInterval(row-1, row-1);
                }catch(Exception ex){
                    //Geen actie, de eerste rij is geselecteerd
                } 
            }
        }

        public void keyReleased(KeyEvent e) {
            //Niet nodig
        }
    
    }

    /**
     * De listener klasse van het zoekveld. Deze klasse wordt gebruikt om naar aanpassingen in het 
     * zoekveld te luisteren, en vervolgens de rij te selecteren die met het ingetypte gedeetle overeenkomt.
     * 
     */    
   
    private class ZoekListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            SearchKlant(jtZoekveld.getText(), jcKlantenZoekOpties.getSelectedItem());
        }

        public void removeUpdate(DocumentEvent e) {
            SearchKlant(jtZoekveld.getText(), jcKlantenZoekOpties.getSelectedItem());
        }

        public void changedUpdate(DocumentEvent e) {
            SearchKlant(jtZoekveld.getText(), jcKlantenZoekOpties.getSelectedItem());
        }
        
        /**
         * De functie die de gezochte klant selecteerd in de tabel.
         * 
         * Er worden twee parameters meegegeven:
         * @param gebruiker
         * @param field 
         * 
         * De eerste parameter is een zoekterm. De tweede is het veld waarin gezocht moet worden.
         */
        private void SearchKlant(String gebruiker, Object field) {
           
            int col = 1;

        if (field.equals(jcKlantenZoekOpties.getItemAt(1))) {
            col = 0;
        } else if (field.equals(jcKlantenZoekOpties.getItemAt(0))) {
            col = 1;
        } else if (field.equals(jcKlantenZoekOpties.getItemAt(2))) {
            col = 2;
        } else if (field.equals(jcKlantenZoekOpties.getItemAt(3))) {
            col = 3;
        }
        
         int rows = jTable1.getModel().getRowCount();

            for (int i = rows-1; i >=0; i--) {
                String value = String.valueOf(jTable1.getModel().getValueAt(i, col));
   
                try {                   
                    
                    if (value.toLowerCase().contains(gebruiker.toLowerCase()) && gebruiker.length()!=0) {                       
                        jTable1.setRowSelectionInterval(i, i);
                    }  else if (gebruiker.length() == 0) {
                    jTable1.setRowSelectionInterval(0, 0);
                    }
                } catch (Exception e) {
                    //Do nothing
                }
            }
            
            
        }
    }
    
    private void newFrame(String titel, String tekst){
            final JFrame frame=new JFrame(titel);
            JButton bWijzigen=new JButton("Wijzigen");
            JLabel lTekst=new JLabel(tekst);
            final JTextField tfInvoer=new JTextField();
            
            lTekst.setPreferredSize(new Dimension(200,50));
            lTekst.setHorizontalAlignment(JLabel.CENTER);
            tfInvoer.setPreferredSize(new Dimension(200,100));
            tfInvoer.setHorizontalAlignment(JTextField.CENTER);
            
            frame.setVisible(true);
            frame.setSize(300, 160);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            
            bWijzigen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    String kolomnaam = jTable1.getColumnName(jTable1.getSelectedColumn());
                    if (kolomnaam.equals("Naam")){
                        String[] splitArray = tfInvoer.getText().split(" ");
                        ArrayList<String> namesArray = new ArrayList<String>();
                        int i = 0;
                        String tussenvoegsel = "";
                        
                        for(String split : splitArray)
                        {
                            namesArray.add(split);
                        }
                        
                        for(String name : namesArray)
                        {
                            if(i == 0)
                            {
                                gebruiker.setVoornaam(name);
                            }
                            
                            if(i > 0 && i < (namesArray.size() - 1) && i != (namesArray.size() - 1))
                            {
                                tussenvoegsel += name;
                                
                                if(i != (namesArray.size() - 2))
                                {
                                    tussenvoegsel += " ";
                                }
                            }
                            
                            if(i == namesArray.size())
                            {
                                gebruiker.setAchternaam(name);
                            }
                            
                            i++;
                        }
                        
                        gebruiker.setTussenvoegsel(tussenvoegsel);
                        
                        jTable1.setValueAt(tfInvoer.getText(), jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                        
                        WinkelApplication.getQueryManager().setGebruiker(gebruiker);
                        JOptionPane.showMessageDialog(frame,
                                "Naam is succesvol gewijzigd",
                                "Klant wijzigen",
                                JOptionPane.PLAIN_MESSAGE
                                );
                    }
                    else if (kolomnaam.equals("Postcode")){ 
                        gebruiker.setPostcode(tfInvoer.getText());
                        jTable1.setValueAt(tfInvoer.getText(), jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                        WinkelApplication.getQueryManager().setGebruiker(gebruiker);
                        JOptionPane.showMessageDialog(frame,
                                "Postcode is succesvol gewijzigd",
                                "Klant wijzigen",
                                JOptionPane.PLAIN_MESSAGE
                                );
                    }
                    else if (kolomnaam.equals("Adres")){
                        String split[]=tfInvoer.getText().split(" ");
                        gebruiker.setStraatnaam(split[0]);
                        gebruiker.setHuisnummer(Integer.parseInt(split[1]));
                        jTable1.setValueAt(tfInvoer.getText(), jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                        WinkelApplication.getQueryManager().setGebruiker(gebruiker);
                        JOptionPane.showMessageDialog(frame,
                                "Adres is succesvol gewijzigd",
                                "Klant wijzigen",
                                JOptionPane.PLAIN_MESSAGE
                                );
                    }
                    else if (kolomnaam.equals("Woonplaats")){
                        gebruiker.setWoonplaats(tfInvoer.getText());
                        jTable1.setValueAt(tfInvoer.getText(), jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                        WinkelApplication.getQueryManager().setGebruiker(gebruiker);
                        JOptionPane.showMessageDialog(frame,
                                "Woonplaats is succesvol gewijzigd",
                                "Klant wijzigen",
                                JOptionPane.PLAIN_MESSAGE
                                );
                    }
                    else if (kolomnaam.equals("E-mail")){
                        gebruiker.setEmail(tfInvoer.getText());
                        jTable1.setValueAt(tfInvoer.getText(), jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                        WinkelApplication.getQueryManager().setGebruiker(gebruiker);
                        JOptionPane.showMessageDialog(frame,
                                "E-mail is succesvol gewijzigd",
                                "Klant wijzigen",
                                JOptionPane.PLAIN_MESSAGE
                                );
                    }
                    else if (kolomnaam.equals("Geboortedatum")){
                        gebruiker.setGeboortedatum(tfInvoer.getText());
                        jTable1.setValueAt(tfInvoer.getText(), jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                        WinkelApplication.getQueryManager().setGebruiker(gebruiker);
                        JOptionPane.showMessageDialog(frame,
                                "Geboortedatum is succesvol gewijzigd",
                                "Klant wijzigen",
                                JOptionPane.PLAIN_MESSAGE
                                );
                    }
                    frame.dispose();
                }
            });
            
            

            
            frame.add(lTekst, BorderLayout.PAGE_START);
            frame.add(new JLabel("          "), BorderLayout.LINE_START);
            frame.add(tfInvoer, BorderLayout.CENTER);
            frame.add(new JLabel("          "), BorderLayout.LINE_END);
            frame.add(bWijzigen, BorderLayout.PAGE_END);
            
            
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbKlantToevoegen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jcKlantenZoekOpties = new javax.swing.JComboBox();
        jtZoekveld = new javax.swing.JTextField();
        buttonVerwijderenGebruiker = new javax.swing.JButton();
        jbMedewerkerToevoegen = new javax.swing.JButton();
        bWijzigen = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jbBack = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(1200, 400));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 400));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Klant_id", "Naam", "E-mail", "Geboortedatum", "Adres", "Postcode", "Woonplaats", "Medewerker"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jbKlantToevoegen.setText("Klant Toevoegen");
        jbKlantToevoegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbKlantToevoegenActionPerformed(evt);
            }
        });

        jLabel1.setText("Zoek klant met naam:");

        jcKlantenZoekOpties.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Naam", "Id", "Adres", "Postcode" }));
        jcKlantenZoekOpties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcKlantenZoekOptiesActionPerformed(evt);
            }
        });

        buttonVerwijderenGebruiker.setText("Verwijderen");
        buttonVerwijderenGebruiker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonVerwijderenGebruikerMouseClicked(evt);
            }
        });
        buttonVerwijderenGebruiker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerwijderenGebruikerActionPerformed(evt);
            }
        });

        jbMedewerkerToevoegen.setText("Medew. Toevoegen");
        jbMedewerkerToevoegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMedewerkerToevoegenActionPerformed(evt);
            }
        });

        bWijzigen.setText("Wijzigen");
        bWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bWijzigenMouseClicked(evt);
            }
        });
        bWijzigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bWijzigenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(331, 331, 331))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(buttonVerwijderenGebruiker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bWijzigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbMedewerkerToevoegen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbKlantToevoegen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(754, Short.MAX_VALUE)
                    .addComponent(jcKlantenZoekOpties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbKlantToevoegen)
                    .addComponent(buttonVerwijderenGebruiker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbMedewerkerToevoegen)
                    .addComponent(bWijzigen))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jcKlantenZoekOpties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(369, Short.MAX_VALUE)))
        );

        jPanel2.add(jPanel1, new java.awt.GridBagConstraints());

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(main.WinkelApplication.BACKGROUND);
        jPanel3.setPreferredSize(new java.awt.Dimension(961, 70));

        jLabel14.setFont(main.WinkelApplication.TITEL);
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Klantenoverzicht");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(732, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(main.WinkelApplication.BACKGROUND);

        jbBack.setFont(main.WinkelApplication.FONT_14_BOLD);
        jbBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/backButton.png"))); // NOI18N
        jbBack.setText("Terug");
        jbBack.setBorderPainted(false);
        jbBack.setContentAreaFilled(false);
        jbBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBack.setFocusPainted(false);
        jbBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jbBack)
                .addContainerGap(856, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jbBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel5, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jbKlantToevoegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbKlantToevoegenActionPerformed
        WinkelApplication.getInstance().showPanel(new NieuweGebruiker(NieuweGebruiker.KLANTEN_OVERZICHT));
    }//GEN-LAST:event_jbKlantToevoegenActionPerformed

    private void jbBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBackActionPerformed
        WinkelApplication.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_jbBackActionPerformed

    private void jcKlantenZoekOptiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcKlantenZoekOptiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcKlantenZoekOptiesActionPerformed

    private void buttonVerwijderenGebruikerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonVerwijderenGebruikerMouseClicked
        //verkrijg id van de geselecteerde gebruiker
        int row = jTable1.getSelectedRow();
        int id = (Integer) jTable1.getModel().getValueAt(row, 0);
        
        //Zet de gebruiker op inactief
        gebruiker = WinkelApplication.getQueryManager().getGebruiker(id);
        gebruiker.setActief(false);
        
        //Feedback window voor de gebruiker
        if(WinkelApplication.getQueryManager().setGebruiker(gebruiker))
        {
            JOptionPane.showMessageDialog(null, "De gebruiker is verwijderd", "Succes", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Er is een fout opgetreden\nDe gebruiker is niet verwijderd\nNeem contact op met uw systeem administrator voor meer informatie", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //refresh jtable
        this.updateTable(true);
    }//GEN-LAST:event_buttonVerwijderenGebruikerMouseClicked
    
    //Toevoegen van Medewerker
    private void jbMedewerkerToevoegenActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbMedewerkerToevoegenActionPerformed
    {//GEN-HEADEREND:event_jbMedewerkerToevoegenActionPerformed
           WinkelApplication.getInstance().showPanel(new NieuweGebruiker(NieuweGebruiker.KLANTEN_OVERZICHT_MEDEWERKER));
    }//GEN-LAST:event_jbMedewerkerToevoegenActionPerformed

    private void buttonVerwijderenGebruikerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerwijderenGebruikerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonVerwijderenGebruikerActionPerformed

    private void bWijzigenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bWijzigenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bWijzigenMouseClicked

    private void bWijzigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bWijzigenActionPerformed
         int row = jTable1.getSelectedRow();
         int col = jTable1.getSelectedColumn();
         int id = (Integer) jTable1.getModel().getValueAt(row, 0);
         String selectedColumnName;
        
        //Zet de gebruiker op inactief
        gebruiker = WinkelApplication.getQueryManager().getGebruiker(id);
        selectedColumnName= jTable1.getModel().getColumnName(col);
        if (selectedColumnName.equals("Klant_id")){
            JOptionPane.showMessageDialog(this,
                                "U kunt de klant_id niet wijzigen",
                                "Klant wijzigen",
                                JOptionPane.PLAIN_MESSAGE
                                );
        }
        else if (selectedColumnName.equals("Medewerker")){
            JOptionPane.showMessageDialog(this,
                                "U kunt deze kolom niet wijzigen",
                                "Klant wijzigen",
                                JOptionPane.PLAIN_MESSAGE
                                );
        }
        else {
        newFrame("Klant wijzigen", "Waarin wil u '" + jTable1.getValueAt(row, col)+ "' wijzigen?");
        }
    }//GEN-LAST:event_bWijzigenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bWijzigen;
    private javax.swing.JButton buttonVerwijderenGebruiker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbBack;
    private javax.swing.JButton jbKlantToevoegen;
    private javax.swing.JButton jbMedewerkerToevoegen;
    private javax.swing.JComboBox jcKlantenZoekOpties;
    private javax.swing.JTextField jtZoekveld;
    // End of variables declaration//GEN-END:variables
}