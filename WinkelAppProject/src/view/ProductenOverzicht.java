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
 * @author Tjarco
 * @version 1.0
 * 
 * Een overzicht van de categoriën en producten
 *
 * @version 1.1
 * @author Bono
 * 
 * Producten verwijderen toegevoegd
 * updateTable() method toegevoegd om sneller data toe te voegen/verversen
 * 
 * @version 1.2
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * 
 * Product kan nu worden geretourneerd. Medewerker kan aangeven hoeveel product geretourneerd dienen te worden. Vervolgens wordt met een SQL query het aantal geretourneerde producten bij de voorraad worden opgeteld.
 * Voorraad kan nu ook worden geretourneerd. De producten worden dan teruggestuurd naar de leverancier. Een medewerker kan aangeven hoeveel dit er zijn. Vervolgens zal dit aantal van de voorraad van het betreffende product worden afgehaald.
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

        jFrame1 = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jFrame2 = new javax.swing.JFrame();
        jLabel7 = new javax.swing.JLabel();
        wijzigCatButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        wijzigCatOmschrijving = new javax.swing.JTextArea();
        wijzigCatNaam = new javax.swing.JTextField();
        wijzigCatID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jFrame3 = new javax.swing.JFrame();
        variabeleTest = new javax.swing.JLabel();
        testKlasse = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtCategorie = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jtZoekveldCat = new javax.swing.JTextField();
        jbToevoegenCategorie = new javax.swing.JButton();
        jbWijzigCategorie = new javax.swing.JButton();
        jcCategoryVeld = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        bekijkBestellingenVenster = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProducten = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtZoekveld = new javax.swing.JTextField();
        jbProductToevoegen = new javax.swing.JButton();
        jbProductRetour = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jcProductVeld = new javax.swing.JComboBox();
        jbProductVerwijderen = new javax.swing.JButton();
        jbProductWijzigen = new javax.swing.JButton();
        jbProductWijzigen2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jbBack = new javax.swing.JButton();

        jFrame1.setTitle("Nieuwe categorie toevoegen");
        jFrame1.setMinimumSize(new java.awt.Dimension(500, 300));

        jLabel5.setText("Naam:");

        jLabel6.setText("Omschrijving:");

        jButton1.setText("Categorie toevoegen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                        .addComponent(jTextField1)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        jFrame2.setTitle("Categorie wijzigen");
        jFrame2.setMinimumSize(new java.awt.Dimension(500, 300));

        jLabel7.setText("Naam:");

        wijzigCatButton.setText("Categorie wijzigen");
        wijzigCatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wijzigCatButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("Omschrijving:");

        wijzigCatOmschrijving.setColumns(20);
        wijzigCatOmschrijving.setRows(5);
        jScrollPane4.setViewportView(wijzigCatOmschrijving);

        wijzigCatID.setEnabled(false);

        jLabel9.setText("Categorie ID");

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wijzigCatButton)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(wijzigCatNaam)
                    .addComponent(wijzigCatID))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(wijzigCatNaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wijzigCatID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(wijzigCatButton)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jFrame3.setMinimumSize(new java.awt.Dimension(500, 400));

        variabeleTest.setText("jLabel10");

        testKlasse.setText("jButton2");
        testKlasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testKlasseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(variabeleTest, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(testKlasse)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(variabeleTest)
                    .addComponent(testKlasse))
                .addContainerGap(247, Short.MAX_VALUE))
        );

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

        jbToevoegenCategorie.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jbToevoegenCategorie.setText("Voeg een categorie toe");
        jbToevoegenCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbToevoegenCategorieActionPerformed(evt);
            }
        });

        jbWijzigCategorie.setText("Wijzig een categorie");
        jbWijzigCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbWijzigCategorieActionPerformed(evt);
            }
        });

        jcCategoryVeld.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Naam", "Categorie_id", "Beschrijving", "Status" }));

        jLabel4.setText("op:");

        bekijkBestellingenVenster.setText("Wijzig een categorie");
        bekijkBestellingenVenster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bekijkBestellingenVensterActionPerformed(evt);
            }
        });

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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(bekijkBestellingenVenster)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbWijzigCategorie)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jbToevoegenCategorie))
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
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbWijzigCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bekijkBestellingenVenster, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbToevoegenCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jbProductToevoegen.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jbProductToevoegen.setText("Nieuw product");
        jbProductToevoegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProductToevoegenActionPerformed(evt);
            }
        });

        jbProductRetour.setText("Product retourner.");
        jbProductRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProductRetourActionPerformed(evt);
            }
        });

        jLabel3.setText("op");

        jcProductVeld.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Naam", "Product_id", "Beschrijving", "Status" }));

        jbProductVerwijderen.setText("Verwijderen");
        jbProductVerwijderen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbProductVerwijderenMouseClicked(evt);
            }
        });
        jbProductVerwijderen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProductVerwijderenActionPerformed(evt);
            }
        });

        jbProductWijzigen.setText("Wijzig product");
        jbProductWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbProductWijzigenMouseClicked(evt);
            }
        });

        jbProductWijzigen2.setText("Voorraad retourner.");
        jbProductWijzigen2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voorraadRetourneren(evt);
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
                        .addComponent(jbProductVerwijderen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbProductWijzigen2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbProductRetour)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbProductWijzigen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbProductToevoegen))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
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
                    .addComponent(jbProductToevoegen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbProductRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbProductVerwijderen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbProductWijzigen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbProductWijzigen2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 42, Short.MAX_VALUE))
        );

        jbProductRetour.getAccessibleContext().setAccessibleName("Product retourneren");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jbBack)
                .addContainerGap(1208, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jbBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jbProductToevoegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProductToevoegenActionPerformed
        main.WinkelApplication.getInstance().showPanel(new NieuwProduct());
    }//GEN-LAST:event_jbProductToevoegenActionPerformed

    private void jbBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBackActionPerformed
        main.WinkelApplication.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_jbBackActionPerformed

    private void jbToevoegenCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbToevoegenCategorieActionPerformed
        jFrame1.show();
    }//GEN-LAST:event_jbToevoegenCategorieActionPerformed

    //Verwijderen product (klik op knop "verwijderen")
    private void jbProductVerwijderenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbProductVerwijderenMouseClicked
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
    }//GEN-LAST:event_jbProductVerwijderenMouseClicked

    /**
     * @author Vernon de Goede < vernon.de.goede@hva.nl >
     * 
     * Producten retourneren
     * Hiermee kan de voorraad van een product worden veranderd doordat er een aantal producten (de hoeveelheid kan de medewerker zelf invullen d.m.v. een InputDialog) bij de voorraad worden opgeteld.
     */
    private void jbProductRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProductRetourActionPerformed

        //Product_id ophalen uit de table
        int row = jtProducten.getSelectedRow();
        int col = 0;
        int id = (Integer) jtProducten.getModel().getValueAt(row, col);
        int currentVoorraad, newVoorraad;
        
        // Product als object aanmaken
        Product product = WinkelApplication.getQueryManager().getProduct(id);
        currentVoorraad = product.getVoorraad();
        
        String str = JOptionPane.showInputDialog(null, "Aantal producten dat wordt geretourneerd: ", 
      "Product retourneren", 1);
        if(str != null) {
            newVoorraad = Integer.parseInt(str) + currentVoorraad;
            //product.setVoorraad(newVoorraad);
            main.WinkelApplication.getQueryManager().UpdateVoorraadByID(product.getProduct_id(), newVoorraad);
            JOptionPane.showMessageDialog(null, "Er zullen " + str + " producten aan de voorraad worden toegevoegd. De nieuwe voorraad is dan " + newVoorraad, 
          "Product retourneren", 1);
        }else{
            JOptionPane.showMessageDialog(null, "De voorraad is niet veranderd.", 
          "Product retourneren", 1);
        }
        
        // Update tabellen
        updateTable(true, false, true, false);
    }//GEN-LAST:event_jbProductRetourActionPerformed

    /**
     * @author Vernon de Goede < vernon.de.goede@hva.nl >
     * 
     * Voorraad retourneren
     * Hiermee kan de voorraad worden geretourneerd en kunnen producten worden teruggestuurd naar de leverancier. Er kan door een medewerker worden opgegeven hoeveel producten worden teruggestuurd d.m.v. een InputDialog. Vervolgens wordt dit aantal van de voorraad afgetrokken.
     */
    private void voorraadRetourneren(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voorraadRetourneren
        //Product_id ophalen uit de table
        int row = jtProducten.getSelectedRow();
        int col = 0;
        int id = (Integer) jtProducten.getModel().getValueAt(row, col);
        int currentVoorraad, newVoorraad;
        
        // Product als object aanmaken
        Product product = WinkelApplication.getQueryManager().getProduct(id);
        currentVoorraad = product.getVoorraad();
        
        String str = JOptionPane.showInputDialog(null, "Het aantal producten dat terug naar de leverancier moet: ", 
      "Voorraad retourneren", 1);
        if(str != null) {
            newVoorraad =  currentVoorraad - Integer.parseInt(str);
            //product.setVoorraad(newVoorraad);
            main.WinkelApplication.getQueryManager().UpdateVoorraadByID(product.getProduct_id(), newVoorraad);
            JOptionPane.showMessageDialog(null, "Er zullen " + str + " producten van de voorraad worden afgetrokken. De nieuwe voorraad is dan " + newVoorraad, 
          "Voorraad retourneren", 1);
        }else{
            JOptionPane.showMessageDialog(null, "De voorraad is niet veranderd.", 
          "Voorraad retourneren", 1);
        }
        
        // Update tabellen
        updateTable(true, false, true, false);
    }//GEN-LAST:event_voorraadRetourneren

    /**
     * @author Vernon de Goede < vernon.de.goede@hva.nl >
     * @param evt 
     * 
     * ActionPerformed die geladen wordt als er op de knop Toevoegen wordt geklikt in jFrame1. De query wordt dan gerunt waarmee een nieuwe categorie wordt toegevoegd.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String catnaam, catOmschrijving;
        catnaam = jTextField1.getText();
        catOmschrijving = jTextArea1.getText();
        
        WinkelApplication.getQueryManager().addCategory(catnaam, catOmschrijving);
        updateTable(true, false, true, false);
        JOptionPane.showMessageDialog(null, "De categorie is toegevoegd!");
        jFrame1.hide();
    }//GEN-LAST:event_jButton1ActionPerformed

     /**
     * @author Vernon de Goede < vernon.de.goede@hva.nl >
     * @param evt 
     * 
     * Slaat alle gegevens uit de textfields op d.m.v. een methode uit de QueryManger
     */
    private void wijzigCatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wijzigCatButtonActionPerformed
        String wijzigCatNaamValue, wijzigCatOmschrijvingValue;
        int temp, wijzigCatIDValue;
        wijzigCatNaamValue = wijzigCatNaam.getText();
        wijzigCatOmschrijvingValue = wijzigCatOmschrijving.getText();
        temp =  Integer.parseInt(wijzigCatID.getText());
        wijzigCatIDValue = temp;
        
        WinkelApplication.getQueryManager().updateCategory(wijzigCatIDValue, wijzigCatNaamValue, wijzigCatOmschrijvingValue);
        updateTable(true, false, true, false);
        
        JOptionPane.showMessageDialog(null, "De categorie is gewijzigd!");
        jFrame2.hide();
    }//GEN-LAST:event_wijzigCatButtonActionPerformed

    /**
     * @author Vernon de Goede < vernon.de.goede@hva.nl >
     * @param evt 
     * 
     * Opent een nieuw jPanel waar hij alle opgeslagen values uit de database haalt en deze in de jusite textfields zet.
     */
    private void jbWijzigCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbWijzigCategorieActionPerformed
        // Fetch categorie_id
        int row = jtCategorie.getSelectedRow();
        int col = 0;
        int id = (Integer) jtCategorie.getModel().getValueAt(row, col);
        
        jFrame2.show();
        wijzigCatNaam.setText(WinkelApplication.getQueryManager().getCategorie(id).getName());
        wijzigCatOmschrijving.setText(WinkelApplication.getQueryManager().getCategorie(id).getDescription());
        wijzigCatID.setText(Integer.toString(id));
    }//GEN-LAST:event_jbWijzigCategorieActionPerformed

    private void jbProductVerwijderenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProductVerwijderenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbProductVerwijderenActionPerformed

    private void jbProductWijzigenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbProductWijzigenMouseClicked
        // Fetch product_id
        int row = jtProducten.getSelectedRow();
        int col = 0;
        int id = (Integer) jtProducten.getModel().getValueAt(row, col);
        Product product = WinkelApplication.getQueryManager().getProduct(id);
        
        main.WinkelApplication.getInstance().showPanel(new NieuwProduct(product));
    }//GEN-LAST:event_jbProductWijzigenMouseClicked

    private void testKlasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testKlasseActionPerformed
        String naamTest;
        naamTest = WinkelApplication.getQueryManager().getOrder(3).getNaam();
        variabeleTest.setText(naamTest);
    }//GEN-LAST:event_testKlasseActionPerformed

    private void bekijkBestellingenVensterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bekijkBestellingenVensterActionPerformed
        jFrame3.show();
    }//GEN-LAST:event_bekijkBestellingenVensterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bekijkBestellingenVenster;
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbBack;
    private javax.swing.JButton jbProductRetour;
    private javax.swing.JButton jbProductToevoegen;
    private javax.swing.JButton jbProductVerwijderen;
    private javax.swing.JButton jbProductWijzigen;
    private javax.swing.JButton jbProductWijzigen2;
    private javax.swing.JButton jbToevoegenCategorie;
    private javax.swing.JButton jbWijzigCategorie;
    private javax.swing.JComboBox jcCategoryVeld;
    private javax.swing.JComboBox jcProductVeld;
    private javax.swing.JTable jtCategorie;
    private javax.swing.JTable jtProducten;
    private javax.swing.JTextField jtZoekveld;
    private javax.swing.JTextField jtZoekveldCat;
    private javax.swing.JButton testKlasse;
    private javax.swing.JLabel variabeleTest;
    private javax.swing.JButton wijzigCatButton;
    private javax.swing.JTextField wijzigCatID;
    private javax.swing.JTextField wijzigCatNaam;
    private javax.swing.JTextArea wijzigCatOmschrijving;
    // End of variables declaration//GEN-END:variables
}
