/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import main.WinkelApplication;
import model.Product;

/**
 *
 * @author Tjarco
 */

/**
 * @version 1.1
 * @author Bono
 * 
 * Producten verwijderen toegevoegd
 * updateTable() method toegevoegd om sneller data toe te voegen/verversen
 */
public class ProductenOverzicht extends javax.swing.JPanel {

    /**
     * Creates new form Vooraad
     */
    public ProductenOverzicht() {
        initComponents();
        updateTable(true, true, false, false);

        jtZoekveld.getDocument().addDocumentListener(new ZoekListener());
        jtZoekveld.addKeyListener(new SnelToetsListener());
        jtZoekveldCat.getDocument().addDocumentListener(new ZoekListener());
        jtZoekveldCat.addKeyListener(new SnelToetsListener());
        
        jtZoekveld.requestFocusInWindow();


    }

     /**
     * @version 1.0
     * @author Bono
     * 
     * @param doProduct Specificeer of de producten tabel ververst moet worden
     * @param doCategorie Specificeer of de categorie tabel ververst moet worden
     * @param refreshProduct Specificeer of de product tabel leeggehaald moet worden
     * @param refreshCategorie Specifiveer of de categorie tabel leeggehaald moet worden
     * 
     * <p>Gebruik deze methode om JTable te verversen met nieuwe data.<p>
     */
    private void updateTable(boolean doProduct, boolean doCategorie, boolean refreshProduct, boolean refreshCategorie) 
    {
        //Producten
        if(doProduct)
        {
            List<Product> products = main.WinkelApplication.getQueryManager().getAllProducts();

            //Maakt een tabelModel aan om de data te beheren
            DefaultTableModel productModel = (DefaultTableModel) jtProducten.getModel();

            if(refreshProduct)
            {
                productModel.setRowCount(0);
            }

            for (Product p : products) 
            {
                productModel.addRow(new Object[]{
                    
                    new Integer(p.getProduct_id()),
                    p.getNaam(),
                    p.getOmschrijving(),
                    p.getIs_actief()
                        
                });
            }
        }

        //Categorie
        if(doCategorie)
        {
            List<model.Category> categorys = main.WinkelApplication.getQueryManager().getCategories();

            //Maakt een tabel model aan om de data te beheren
            DefaultTableModel catModel = (DefaultTableModel) jtCategorie.getModel();

            if(refreshCategorie)
            {
                catModel.setRowCount(0);
            }

            for (model.Category c : categorys) 
            {
                catModel.addRow(new Object[]
                {
                    new Integer(c.getCategoryId()),
                    c.getName(),
                    c.getDescription()
                });
            }
        }
    }

    /**
     * De listener klasse van de zoekvelden. Deze klasse wordt gebruikt om naar
     * aanpassingen in de zoekvelden te luisteren, en vervolgens de rij te
     * selecteren die met het ingetypte gedeetle overeenkomt.
     *
     */
    private class ZoekListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            if (e.getDocument().equals(jtZoekveld.getDocument())) {
                searchProduct(jtZoekveld.getText(), jcProductVeld.getSelectedItem());
            } else {
                searchCategory(jtZoekveldCat.getText(), jcCategoryVeld.getSelectedItem());
            }
        }

        public void removeUpdate(DocumentEvent e) {
            if (e.getDocument().equals(jtZoekveld.getDocument())) {
                searchProduct(jtZoekveld.getText(), jcProductVeld.getSelectedItem());
            } else {
                searchCategory(jtZoekveldCat.getText(),jcCategoryVeld.getSelectedItem());
            }
        }

        public void changedUpdate(DocumentEvent e) {
            if (e.getDocument().equals(jtZoekveld.getDocument())) {
                searchProduct(jtZoekveld.getText(), jcProductVeld.getSelectedItem());
            } else {
                searchCategory(jtZoekveldCat.getText(),jcCategoryVeld.getSelectedItem());
            }
        }
    }

    /**
     * De listener voor sneltoetsen.
     *
     * Als de gebruiker typt om producten te zoeken kunnen de sneltoetsen omhoog
     * en naar beneden gebuikt worden om te navigeren door de rijen.
     *
     * Met de enter toets kan het geselecteerde product gewijzigd worden
     *
     */
    private class SnelToetsListener implements KeyListener {

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            if (e.getSource().equals(jtZoekveld)) {

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    int row = jtProducten.getSelectedRow();
                    try {
                        jtProducten.setRowSelectionInterval(row + 1, row + 1);
                    } catch (Exception ex) {
                        //Do nothing: last row is selected.
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    int row = jtProducten.getSelectedRow();
                    try {
                        jtProducten.setRowSelectionInterval(row - 1, row - 1);
                    } catch (Exception ex) {
                        //Do nothing: last row is selected.
                    }
                }
            } else if (e.getSource().equals(jtZoekveldCat)) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    int row = jtCategorie.getSelectedRow();
                    try {
                        jtCategorie.setRowSelectionInterval(row + 1, row + 1);
                    } catch (Exception ex) {
                        //Do nothing: last row is selected.
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    int row = jtCategorie.getSelectedRow();
                    try {
                        jtCategorie.setRowSelectionInterval(row - 1, row - 1);
                    } catch (Exception ex) {
                        //Do nothing: last row is selected.
                    }
                }
            }


        }

        public void keyReleased(KeyEvent e) {
        }
    }

    /**
     * Zoekt een Product in de tabel producten.
     *
     * Er worden twee parameters meegegeven:
     *
     * @param product
     * @param field
     *
     * de eerste parameter is een string waarnaar gezocht moet worden. De tweede
     * paramter is het veld waarin gezocht moet worden.
     */
    private void searchProduct(String product, Object field) {
        int rows = jtProducten.getModel().getRowCount();

        int col = 0;
        if (field.equals(jcProductVeld.getItemAt(1))) {
            col = 0;
        } else if (field.equals(jcProductVeld.getItemAt(0))) {
            col = 1;
        } else if (field.equals(jcProductVeld.getItemAt(2))) {
            col = 2;
        } else if (field.equals(jcProductVeld.getItemAt(3))) {
            col = 3;
        }

        for (int i = rows - 1; i >= 0; i--) {
            String value = String.valueOf(jtProducten.getModel().getValueAt(i, col));

            try {

                if (value.toLowerCase().contains(product.toLowerCase()) && product.length() != 0) {
                    jtProducten.setRowSelectionInterval(i, i);
                } else if (product.length() == 0) {
                    jtProducten.setRowSelectionInterval(0, 0);
                }
            } catch (Exception e) {
                //Do nothing
            }
        }

    }

    private void searchCategory(String category, Object field) {
        
        int col = 0;
        
        if (field.equals(jcCategoryVeld.getItemAt(1))) {
            col = 0;
        } else if (field.equals(jcCategoryVeld.getItemAt(0))) {
            col = 1;
        } else if (field.equals(jcCategoryVeld.getItemAt(2))) {
            col = 2;
        } else if (field.equals(jcCategoryVeld.getItemAt(3))) {
            col = 3;
        }
        
        int rows = jtCategorie.getModel().getRowCount();       

        for (int i = rows - 1; i >= 0; i--) {
            String value = String.valueOf(jtCategorie.getModel().getValueAt(i, col));

            try {

                if (value.toLowerCase().contains(category.toLowerCase()) && category.length() != 0) {
                    jtCategorie.setRowSelectionInterval(i, i);
                } else if (category.length() == 0) {
                    jtCategorie.setRowSelectionInterval(0, 0);
                }
            } catch (Exception e) {
                //Do nothing
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtCategorie = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jtZoekveldCat = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jcCategoryVeld = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProducten = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtZoekveld = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jcProductVeld = new javax.swing.JComboBox();
        buttonProductVerwijderen = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel6.setMaximumSize(new java.awt.Dimension(800, 400));
        jPanel6.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel6.setPreferredSize(new java.awt.Dimension(600, 400));

        jtCategorie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categorie_id", "Naam", "Beschrijving", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtCategorie);

        jLabel2.setText("Zoek Categorie");

        jButton4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton4.setText("Voeg een cattegorie toe");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Wijzig een cattegorie");

        jcCategoryVeld.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Naam", "Categorie_id", "Beschrijving", "Status" }));

        jLabel4.setText("op:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jtZoekveldCat, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7)
                        .addComponent(jcCategoryVeld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jButton5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton4))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtZoekveldCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcCategoryVeld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 44, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(1, 35, 1, 0);
        jPanel1.add(jPanel6, gridBagConstraints);

        jPanel5.setMaximumSize(new java.awt.Dimension(800, 400));
        jPanel5.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 400));

        jtProducten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product_id", "Naam", "Beschrijving", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtProducten);

        jLabel1.setText("Zoek Product");

        jButton1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton1.setText("Voeg een product toe");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Wijzig een product");

        jLabel3.setText("op");

        jcProductVeld.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Naam", "Product_id", "Beschrijving", "Status" }));

        buttonProductVerwijderen.setText("Verwijderen");
        buttonProductVerwijderen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonProductVerwijderenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcProductVeld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonProductVerwijderen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcProductVeld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonProductVerwijderen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 42, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 44);
        jPanel1.add(jPanel5, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(main.WinkelApplication.BACKGROUND);
        jPanel2.setPreferredSize(new java.awt.Dimension(961, 70));

        jLabel14.setFont(main.WinkelApplication.TITEL);
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Product overzicht");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1182, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(main.WinkelApplication.BACKGROUND);

        jButton2.setFont(main.WinkelApplication.FONT_14_BOLD);
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/backButton.png"))); // NOI18N
        jButton2.setText("Terug");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton2)
                .addContainerGap(1208, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        main.WinkelApplication.getInstance().showPanel(new NieuwProduct());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        main.WinkelApplication.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    //Verwijderen product (klik op knop "verwijderen")
    private void buttonProductVerwijderenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonProductVerwijderenMouseClicked
        //Product_id ophalen uit de table
        int row = jtProducten.getSelectedRow();
        int col = 0;
        int id = (Integer) jtProducten.getModel().getValueAt(row, col);
        
        //Verwijderen product (actief = false)
        Product product = WinkelApplication.getQueryManager().getProduct(id);
        if(product.getIs_actief())
        {
            product.setIs_actief(false);
            
            //feedback window voor gebruiker
            if(WinkelApplication.getQueryManager().setProduct(product))
            {
                JOptionPane.showMessageDialog(null, "Het product is verwijderd", "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Er is een fout opgetreden\nHet product is niet verwijderd\nNeem contact op met uw systeem administrator voor meer informatie", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else //Product is al verwijderd (inactief)
        {
            JOptionPane.showMessageDialog(null, "Dit product is al inacief", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //refresh jtable
        updateTable(true, false, true, false);
    }//GEN-LAST:event_buttonProductVerwijderenMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonProductVerwijderen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox jcCategoryVeld;
    private javax.swing.JComboBox jcProductVeld;
    private javax.swing.JTable jtCategorie;
    private javax.swing.JTable jtProducten;
    private javax.swing.JTextField jtZoekveld;
    private javax.swing.JTextField jtZoekveldCat;
    // End of variables declaration//GEN-END:variables
}
