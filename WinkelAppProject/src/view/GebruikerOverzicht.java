/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import main.WinkelApplication;
import model.Gebruiker;

/**
 * @version 1.0
 * @author Tjarco
 * 
 * Een klasse die het overzicht geeft van alle klanten in de database. Er kunnen vanaf hier nieuwe 
 * klanten worden toegevoegd. Ook kunenn klanten gewijzigd worden.
 */

/**
 * @version 1.1
 * @author Bono
 * 
 * Klanten verwijderen toegevoegd
 * updateTable() method toegevoegd om sneller data toe te voegen/verversen
 */
public class GebruikerOverzicht extends javax.swing.JPanel {

    /**
     * Maakt het form aan en zet de data in de tabel
     */
    public GebruikerOverzicht() 
    {
        initComponents();

        updateTable(false);
        
        jtZoekveld.getDocument().addDocumentListener(new ZoekListener());
        jtZoekveld.addKeyListener(new SnelToetsListener());
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
        List<Gebruiker> klanten = WinkelApplication.getQueryManager().getKlantenList();
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        
        //Tabel leeg maken
        if(refresh)
        {
            model.setRowCount(0);
        }
        
        for (Gebruiker klant : klanten) 
        {
            model.addRow(new Object[]{new Integer(klant.getId()),
            klant.getVoornaam() + klant.getAchternaam(),
            klant.getStraatnaam(),
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jcKlantenZoekOpties = new javax.swing.JComboBox();
        jtZoekveld = new javax.swing.JTextField();
        buttonVerwijderenGebruiker = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

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
                "Klant_id", "Naam", "Adres", "Postcode", "Woonplaats", "Medewerker"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Klant Toevoegen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        jButton2.setText("Medew. Toevoegen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonVerwijderenGebruiker)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(159, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(416, Short.MAX_VALUE)
                    .addComponent(jcKlantenZoekOpties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(415, Short.MAX_VALUE)))
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
                    .addComponent(jButton1)
                    .addComponent(buttonVerwijderenGebruiker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
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
                .addContainerGap(734, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(main.WinkelApplication.BACKGROUND);

        jButton3.setFont(main.WinkelApplication.FONT_14_BOLD);
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/backButton.png"))); // NOI18N
        jButton3.setText("Terug");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton3)
                .addContainerGap(862, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel5, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        WinkelApplication.getInstance().showPanel(new NieuweGebruiker(NieuweGebruiker.KLANTEN_OVERZICHT));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        WinkelApplication.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jcKlantenZoekOptiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcKlantenZoekOptiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcKlantenZoekOptiesActionPerformed

    private void buttonVerwijderenGebruikerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonVerwijderenGebruikerMouseClicked
        //verkrijg id van de geselecteerde gebruiker
        int row = jTable1.getSelectedRow();
        int id = (Integer) jTable1.getModel().getValueAt(row, 0);
        
        //Zet de gebruiker op inactief
        Gebruiker gebruiker = WinkelApplication.getQueryManager().getGebruiker(id);
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
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
           WinkelApplication.getInstance().showPanel(new NieuweGebruiker(NieuweGebruiker.KLANTEN_OVERZICHT_MEDEWERKER));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonVerwijderenGebruikerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerwijderenGebruikerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonVerwijderenGebruikerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonVerwijderenGebruiker;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox jcKlantenZoekOpties;
    private javax.swing.JTextField jtZoekveld;
    // End of variables declaration//GEN-END:variables
}
