/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.Product;

/**
 *
 * @author Tjarco
 */
public class Voorraad extends javax.swing.JPanel {

    /**
     * Creates new form Voorraad
     */
    public Voorraad() {
        initComponents();
        addData();

        jtZoekveld.getDocument().addDocumentListener(new ZoekListener());
        jtZoekveld.addKeyListener(new SnelToetsListener());
        
        //Geef het zoekveld de focus
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent ce) {
                jtZoekveld.requestFocusInWindow();
            }
        });

    }

    /*
     * Voegt de data uit de database toe aan de tabellen
     */
    private void addData() {
        List<Product> products = main.WinkelApplication.getQueryManager().getAllProducts();

        //Maakt een tabelModel aan om de data te beheren
        DefaultTableModel data = (DefaultTableModel) jtProducten.getModel();
        for (Product p : products) {
            data.addRow(new Object[]{
                        new Integer(p.getProduct_id()),
                        p.getNaam(),
                        p.getOmschrijving(),
                        p.getVoorraad()
                    });
        }




    }

    /**
     * De listener voor sneltoetsen. Er kan tijdens het typen in het zoekveld
     * met de toetsen naar boven en beneden een rij geselecteerd worden
     */
    private class SnelToetsListener implements KeyListener {

        public void keyTyped(KeyEvent e) {
            //Niet nodig
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                int row = jtProducten.getSelectedRow();

                try {
                    jtProducten.setRowSelectionInterval(row + 1, row + 1);
                } catch (Exception ex) {
                    //Geen actie, de laatse rij is geselecteerd
                }
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                int row = jtProducten.getSelectedRow();

                try {
                    jtProducten.setRowSelectionInterval(row - 1, row - 1);
                } catch (Exception ex) {
                    //Geen actie, de eerste rij is geselecteerd
                }
            } else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                veranderVoorraad();
            }
        }

        public void keyReleased(KeyEvent e) {
            //Niet nodig
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
            searchProduct(jtZoekveld.getText(), jComboBox1.getSelectedItem());

        }

        public void removeUpdate(DocumentEvent e) {
            searchProduct(jtZoekveld.getText(), jComboBox1.getSelectedItem());
        }

        public void changedUpdate(DocumentEvent e) {
            searchProduct(jtZoekveld.getText(), jComboBox1.getSelectedItem());
        }

        /**
         * Zoekt een Product in de tabel producten.
         *
         * @param product , de parameter geeft aan waarnaar gezocht moet worden.
         */
        private void searchProduct(String product, Object field) {
            int rows = jtProducten.getModel().getRowCount();
            int col = 1;

            if (field.equals(jComboBox1.getItemAt(1))) {
                col = 0;
            } else if (field.equals(jComboBox1.getItemAt(0))) {
                col = 1;
            } else if (field.equals(jComboBox1.getItemAt(2))) {
                col = 2;
            } else if (field.equals(jComboBox1.getItemAt(3))) {
                col = 3;
            }

            for (int i = rows - 1; i >= 0; i--) {
                String value = (String) (jtProducten.getModel().getValueAt(i, col));

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
    }
    /**
     * Verander de voorraad met een Input dialog. 
     * 
     * Valideer de input met een regex. Als de input niet correct is, laat het nog een keer 
     * de input dialog zien.
     */
    private void veranderVoorraad() {

        try {
            String product = String.valueOf(jtProducten.getModel().getValueAt(jtProducten.getSelectedRow(), 1));

            String output = "*";
            String format = "[\\d]+";
            while (!output.matches(format) && !output.equals("")) {
                output = JOptionPane.showInputDialog("wat is de voorraad van ' " + product + "'?");
            }            
            main.WinkelApplication.getQueryManager().UpdateProducts(product, Integer.parseInt(output));
            
            jtProducten.getModel().setValueAt(Integer.parseInt(output), jtProducten.getSelectedRow(), 3);
            
        } catch (Exception e) {
            //Doe niks, er is geen rij geselecteerd.
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
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProducten = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtZoekveld = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel5.setMaximumSize(new java.awt.Dimension(1000, 400));
        jPanel5.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel5.setPreferredSize(new java.awt.Dimension(800, 400));

        jtProducten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product_id", "Naam", "Beschrijving", "Voorraad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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

        jButton3.setText("Wijzig Vooraad");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Naam", "Product_idd", "Beschrijving", "Voorraad" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(jPanel5, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(main.WinkelApplication.BACKGROUND);
        jPanel2.setPreferredSize(new java.awt.Dimension(961, 70));

        jLabel14.setFont(main.WinkelApplication.TITEL);
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Voorraad beheer");

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        main.WinkelApplication.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.veranderVoorraad();
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtProducten;
    private javax.swing.JTextField jtZoekveld;
    // End of variables declaration//GEN-END:variables
}
