/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import model.Product;
import model.Statistiek;
import model.StatistiekGenerator;

/**
 *
 * @author Tjarco
 */
public class Rapporten extends javax.swing.JPanel {
    private StatistiekGenerator statistiekGenerator = new StatistiekGenerator();
    
    /**
     * Creates new form Rapporten
     */
    public Rapporten() {
        initComponents();
        initOmzetPanel();
        
    }
    
    
    private void initLeeftijdsgroepPanel() {
       this.jpLeeftijdGrafiek.removeAll();
       this.jpLeeftijdGrafiek.add(statistiekGenerator.getLeeftijdsgroepGrafiek(jComboBox2.getSelectedItem().toString()));
       this.revalidate();
       this.repaint();
    }
    
    private void initOmzetPanel(){
        jlblOmzet.setText(statistiekGenerator.getOmzet());
        jlblAantalKlanten.setText(statistiekGenerator.getAantalKlanten());
        jlblProductenVerkocht.setText(statistiekGenerator.getTotaalAantalProductenVerkocht());
    }
    
    private void initProductPanel(Product p){
      jpChartProducten.removeAll();
      jpChartProducten.add(statistiekGenerator.getProductenGrafiek(p));
      System.out.println("test");
      
      String[] info = statistiekGenerator.getAantalProductenInfo(p);
      jlblAantalVerkochtProduct.setText(info[0]);
      jlblPrijsProduct.setText(main.WinkelApplication.CURRENCY.format(p.getPrijs()));
      jlblOmzetProduct.setText(info[1]);
      
      
      repaint();
      revalidate();
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

        jPanel2 = new javax.swing.JPanel();
        jpOmzet = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlbOmzet = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblOmzet = new javax.swing.JLabel();
        jlblProductenVerkocht = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlblAantalKlanten = new javax.swing.JLabel();
        jpProducten = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jlbOmzet1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtZoekveld = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlblOmzetProduct = new javax.swing.JLabel();
        jlblAantalVerkochtProduct = new javax.swing.JLabel();
        jlblPrijsProduct = new javax.swing.JLabel();
        jpChartProducten = new javax.swing.JPanel();
        jpLeeftijdsgroepen = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jlbOmzet2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jpLeeftijdGrafiek = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jbBack = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jpOmzet.setBackground(new java.awt.Color(255, 255, 255));
        jpOmzet.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        jpOmzet.setMaximumSize(new java.awt.Dimension(700, 800));
        jpOmzet.setMinimumSize(new java.awt.Dimension(300, 800));
        jpOmzet.setPreferredSize(new java.awt.Dimension(430, 800));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jlbOmzet.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        jlbOmzet.setText("Omzet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 190, 29, 176);
        jPanel1.add(jlbOmzet, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel1.setText("Op dit moment is de volgende omzet behaald:");

        jLabel2.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel2.setText("Op dit moment is volgende hoeveelheid producten verkocht:");

        jlblOmzet.setFont(new java.awt.Font("Calibri", 3, 40)); // NOI18N
        jlblOmzet.setForeground(new java.awt.Color(0, 204, 0));

        jlblProductenVerkocht.setFont(new java.awt.Font("Calibri", 3, 40)); // NOI18N
        jlblProductenVerkocht.setForeground(new java.awt.Color(0, 204, 0));

        jLabel3.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel3.setText("En zijn er de volgende hoeveelheid klanten geregistreerd:");

        jlblAantalKlanten.setFont(new java.awt.Font("Calibri", 3, 40)); // NOI18N
        jlblAantalKlanten.setForeground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jpOmzetLayout = new javax.swing.GroupLayout(jpOmzet);
        jpOmzet.setLayout(jpOmzetLayout);
        jpOmzetLayout.setHorizontalGroup(
            jpOmzetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpOmzetLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jpOmzetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpOmzetLayout.createSequentialGroup()
                        .addGroup(jpOmzetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(jpOmzetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jlblProductenVerkocht, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlblAantalKlanten, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jpOmzetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlblOmzet, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jpOmzetLayout.setVerticalGroup(
            jpOmzetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOmzetLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jlblOmzet, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblProductenVerkocht, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblAantalKlanten, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 274, Short.MAX_VALUE))
        );

        jPanel2.add(jpOmzet, java.awt.BorderLayout.WEST);

        jpProducten.setBackground(new java.awt.Color(255, 255, 255));
        jpProducten.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jlbOmzet1.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        jlbOmzet1.setText("Producten");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 65, 31, 93);
        jPanel7.add(jlbOmzet1, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel4.setText("Selecteer product:");

        jtZoekveld.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setText("Zoek op:");

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Product id", "Naam", "Beschrijving" }));

        jButton1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton1.setText("Toon rapporten");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel6.setText("Aantal verkocht:");

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel7.setText("Omzet:");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel8.setText("Prijs:");

        jlblOmzetProduct.setFont(new java.awt.Font("Calibri", 3, 16)); // NOI18N
        jlblOmzetProduct.setForeground(new java.awt.Color(0, 204, 0));

        jlblAantalVerkochtProduct.setFont(new java.awt.Font("Calibri", 3, 16)); // NOI18N
        jlblAantalVerkochtProduct.setForeground(new java.awt.Color(0, 204, 0));

        jlblPrijsProduct.setFont(new java.awt.Font("Calibri", 3, 16)); // NOI18N
        jlblPrijsProduct.setForeground(new java.awt.Color(0, 204, 0));

        jpChartProducten.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpChartProductenLayout = new javax.swing.GroupLayout(jpChartProducten);
        jpChartProducten.setLayout(jpChartProductenLayout);
        jpChartProductenLayout.setHorizontalGroup(
            jpChartProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );
        jpChartProductenLayout.setVerticalGroup(
            jpChartProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpProductenLayout = new javax.swing.GroupLayout(jpProducten);
        jpProducten.setLayout(jpProductenLayout);
        jpProductenLayout.setHorizontalGroup(
            jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProductenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpChartProducten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpProductenLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jpProductenLayout.createSequentialGroup()
                                .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jlblAantalVerkochtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpProductenLayout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlblPrijsProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(33, 33, 33)
                                        .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblOmzetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jpProductenLayout.createSequentialGroup()
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton1))
                                        .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(96, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpProductenLayout.setVerticalGroup(
            jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProductenLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtZoekveld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(12, 12, 12)
                .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpProductenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblAantalVerkochtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblPrijsProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblOmzetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpChartProducten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 274, Short.MAX_VALUE))
        );

        jPanel2.add(jpProducten, java.awt.BorderLayout.CENTER);

        jpLeeftijdsgroepen.setBackground(new java.awt.Color(255, 255, 255));
        jpLeeftijdsgroepen.setMaximumSize(new java.awt.Dimension(700, 800));
        jpLeeftijdsgroepen.setMinimumSize(new java.awt.Dimension(100, 800));
        jpLeeftijdsgroepen.setPreferredSize(new java.awt.Dimension(430, 800));

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jlbOmzet2.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        jlbOmzet2.setText("Leeftijdsgroepen");
        jPanel9.add(jlbOmzet2, new java.awt.GridBagConstraints());

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setText("Leeftijdsgroep:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jonger dan 15", "Tussen de 15 en de 20 jaar", "Tussen de 20 en de 25 jaar", "Tussen de 25 en de 30 jaar", "Tussen de 30 en de 50 jaar", "Tussen de 50 en de 65 jaar", "Ouder dan 65" }));

        jButton2.setText("Toon rapporten");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jpLeeftijdGrafiek.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpLeeftijdGrafiekLayout = new javax.swing.GroupLayout(jpLeeftijdGrafiek);
        jpLeeftijdGrafiek.setLayout(jpLeeftijdGrafiekLayout);
        jpLeeftijdGrafiekLayout.setHorizontalGroup(
            jpLeeftijdGrafiekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        jpLeeftijdGrafiekLayout.setVerticalGroup(
            jpLeeftijdGrafiekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpLeeftijdsgroepenLayout = new javax.swing.GroupLayout(jpLeeftijdsgroepen);
        jpLeeftijdsgroepen.setLayout(jpLeeftijdsgroepenLayout);
        jpLeeftijdsgroepenLayout.setHorizontalGroup(
            jpLeeftijdsgroepenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpLeeftijdsgroepenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLeeftijdsgroepenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLeeftijdsgroepenLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpLeeftijdsgroepenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jpLeeftijdGrafiek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jpLeeftijdsgroepenLayout.setVerticalGroup(
            jpLeeftijdsgroepenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLeeftijdsgroepenLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jpLeeftijdsgroepenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(68, 68, 68)
                .addComponent(jpLeeftijdGrafiek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 277, Short.MAX_VALUE))
        );

        jPanel2.add(jpLeeftijdsgroepen, java.awt.BorderLayout.EAST);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(main.WinkelApplication.BACKGROUND);
        jPanel3.setPreferredSize(new java.awt.Dimension(961, 70));

        jLabel14.setFont(main.WinkelApplication.TITEL);
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Rapporten");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(952, Short.MAX_VALUE))
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
                .addContainerGap(919, Short.MAX_VALUE))
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

    private void jbBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBackActionPerformed
        main.WinkelApplication.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_jbBackActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String field = this.jComboBox1.getSelectedItem().toString();
        if(field.equals("Product id")) field = "product_id";
        else if(field.equals("Naam")) field = "naam";
        else if(field.equals("Beschrijving")) field = "omschrijving";
        
        Product p = main.WinkelApplication.getQueryManager().getProduct(field, jtZoekveld.getText());
        
        initProductPanel(p);
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       initLeeftijdsgroepPanel();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbBack;
    private javax.swing.JLabel jlbOmzet;
    private javax.swing.JLabel jlbOmzet1;
    private javax.swing.JLabel jlbOmzet2;
    private javax.swing.JLabel jlblAantalKlanten;
    private javax.swing.JLabel jlblAantalVerkochtProduct;
    private javax.swing.JLabel jlblOmzet;
    private javax.swing.JLabel jlblOmzetProduct;
    private javax.swing.JLabel jlblPrijsProduct;
    private javax.swing.JLabel jlblProductenVerkocht;
    private javax.swing.JPanel jpChartProducten;
    private javax.swing.JPanel jpLeeftijdGrafiek;
    private javax.swing.JPanel jpLeeftijdsgroepen;
    private javax.swing.JPanel jpOmzet;
    private javax.swing.JPanel jpProducten;
    private javax.swing.JTextField jtZoekveld;
    // End of variables declaration//GEN-END:variables

}
