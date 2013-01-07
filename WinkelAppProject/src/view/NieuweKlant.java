/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import main.WinkelApplication;
import model.Gebruiker;

/**
 * @version 1.1
 * @author Kevin
 * Medewerker toevoegen
 * 
 * @version 1.0
 * @author Tjarco 
 * De klasse met de velden om een nieuwe klant toe te voegen. Deze klasse kan
 * vanaf meerdere klasse gebruikt worden, daarom moet als parameter worden
 * meegegeven wat de source is, zodat kan worden teruggekeerd naar het juiste
 * menu na het registreren.
 */
public class NieuweKlant extends JPanel {

    public static final int REGISTREREN = 0;
    public static final int KLANTEN_OVERZICHT = 1;
    public static final int KLANTEN_OVERZICHT_MEDEWERKER = 2;
    private int source;

    /**
     * Creates new form NieuweKlant
     */
    public NieuweKlant(int source) {
        initComponents();
        this.source = source;

        //Make the listener to validate the textfields
        ValidationListener lis = new ValidationListener();
        tfWoonplaats.getDocument().addDocumentListener(lis);
        tfHuisnummer.getDocument().addDocumentListener(lis);
        tfNaam.getDocument().addDocumentListener(lis);
        tfStraat.getDocument().addDocumentListener(lis);
        tfTelefoon.getDocument().addDocumentListener(lis);
        tfEmail.getDocument().addDocumentListener(lis);
        tfAchternaam.getDocument().addDocumentListener(lis);
        tfTussenvoegsel.getDocument().addDocumentListener(lis);
        tfPostcode.getDocument().addDocumentListener(lis);
        tfWachtwoord.getDocument().addDocumentListener(lis);
        tfWachtwoordHerhaal.getDocument().addDocumentListener(lis);

    }

// <editor-fold defaultstate="collapsed" desc=" Validators ">
    /**
     * Valdidators for the textfields in the form. If the the format is not
     * correct, the background of the field will turn red. If the format is
     * correct, the background will turn green.
     *
     * these methods are also used to validate the info at the time of
     * commiting. They will return false if the info is incorrect.
     */
    private Boolean validatePostcode(String validatie) {
        Boolean isValid = true;
        String format = "[\\d]{4}+[A-Z]{2}";

        if (!validatie.matches(format) || validatie.length() == 0) {
            tfPostcode.setBackground(Color.red);
            isValid = false;
        } else {
            tfPostcode.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateTelefoon(String validatie) {
        Boolean isValid = true;
        String format = "([\\d]+)+([- \\s]?)+([\\d]+)";

        if (!validatie.matches(format) || validatie.length() == 0 || validatie.length() > 13) {
            tfTelefoon.setBackground(Color.red);
            isValid = false;
        } else {
            tfTelefoon.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateNaam(String validatie) {
        Boolean isValid = true;
        String format = "[a-zA-Z . \\s]+";
        if (validatie.length() > 49 || validatie.length() == 0 || !validatie.matches(format)) {
            tfNaam.setBackground(Color.red);
            isValid = false;
        } else {
            tfNaam.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateWachtwoord(char[] validatie) {
        Boolean isValid = true;
        if (validatie.length > 49 || validatie.length == 0) {
            tfWachtwoord.setBackground(Color.red);
            isValid = false;
        } else {
            tfWachtwoord.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateWachtwoordHerhaling(char[] validatie) {
        Boolean isValid = true;

        if (validatie.length > 49 || validatie.length == 0 || !Arrays.equals(tfWachtwoord.getPassword(), validatie)) {
            tfWachtwoordHerhaal.setBackground(Color.red);
            isValid = false;
        } else {
            tfWachtwoordHerhaal.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateEmail(String validatie) {
        Boolean isValid = true;
        String format = "(.+)+[@]+(.+)+[.]+([\\w]){1,6}";
        if (validatie.length() > 49 || validatie.length() == 0 || !validatie.matches(format)) {
            tfEmail.setBackground(Color.red);
            isValid = false;
        } else {
            tfEmail.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateTussenvoegsel(String validatie) {
        Boolean isValid = true;
        if (validatie.length() > 20) {
            tfTussenvoegsel.setBackground(Color.red);
            isValid = false;
        } else {
            tfTussenvoegsel.setBackground(Color.green);
        }

        return isValid;
    }

    private Boolean validateAchternaam(String validatie) {
        Boolean isValid = true;
        String format = "[a-zA-Z \\s]+";
        if (validatie.length() > 49 || validatie.length() == 0 || !validatie.matches(format)) {
            tfAchternaam.setBackground(Color.red);
            isValid = false;
        } else {
            tfAchternaam.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateStraat(String validatie) {
        Boolean isValid = true;
        String format = "[a-zA-Z . \\s]+";
        if (validatie.length() > 49 || validatie.length() == 0 || !validatie.matches(format)) {
            tfStraat.setBackground(Color.red);
            isValid = false;
        } else {
            tfStraat.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateHuisnummer(String validatie) {
        Boolean isValid = true;
        String format = "([\\d]+)([a-zA-Z]?)";
        if (validatie.length() > 4 || validatie.length() == 0 || !validatie.matches(format)) {
            tfHuisnummer.setBackground(Color.red);
            isValid = false;
        } else {
            tfHuisnummer.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateWoonplaats(String validatie) {
        Boolean isValid = true;
        String format = "[a-zA-Z ' \\s]+";
        if (validatie.length() > 49 || validatie.length() == 0 || !validatie.matches(format)) {
            tfWoonplaats.setBackground(Color.red);
            isValid = false;
        } else {
            tfWoonplaats.setBackground(Color.green);
        }
        return isValid;

    }

// </editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextField6 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfNaam = new javax.swing.JTextField();
        tfPostcode = new javax.swing.JTextField();
        tfWoonplaats = new javax.swing.JTextField();
        jlTitel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfTussenvoegsel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfAchternaam = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfStraat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfHuisnummer = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfTelefoon = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfWachtwoord = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        tfWachtwoordHerhaal = new javax.swing.JPasswordField();
        jbVoegToe = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jTextField6.setText("jTextField6");

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Naam");

        jLabel4.setText("Postcode");

        jLabel5.setText("Woonplaats");

        jlTitel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlTitel.setText("Klantgegevens:");

        jLabel1.setText("Tussenvoegsel");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                jLabel1MouseEntered(evt);
            }
        });

        jLabel7.setText("Achternaam");

        jLabel8.setText("Straat");

        jLabel9.setText("Huisnummer");

        jLabel10.setText("E-mail");

        jLabel3.setText("Telefoon");

        jLabel11.setText("Wachtwoord");

        jLabel12.setText("Herhaal Wachtwoord");

        jbVoegToe.setText("Voeg toe");
        jbVoegToe.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbVoegToeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jlTitel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jbVoegToe))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfWoonplaats, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfWachtwoord, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfWachtwoordHerhaal, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNaam, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfTussenvoegsel, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfAchternaam, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(tfStraat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel9)
                                        .addGap(4, 4, 4)
                                        .addComponent(tfHuisnummer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(80, 80, 80))))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(303, 303, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGap(0, 184, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlTitel)
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tfNaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(tfTussenvoegsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(tfAchternaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfStraat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfHuisnummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9)))
                        .addGap(13, 13, 13)
                        .addComponent(tfWoonplaats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(tfPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(tfTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tfWachtwoord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tfWachtwoordHerhaal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel10)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addComponent(jbVoegToe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(403, 403, 403)))
        );

        jPanel1.add(jPanel4, new java.awt.GridBagConstraints());

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(main.WinkelApplication.BACKGROUND);
        jPanel2.setPreferredSize(new java.awt.Dimension(961, 70));

        jLabel14.setFont(main.WinkelApplication.TITEL);
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Registreren");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(895, Short.MAX_VALUE))
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

        jButton1.setFont(main.WinkelApplication.FONT_14_BOLD);
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/backButton.png"))); // NOI18N
        jButton1.setText("Terug");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addContainerGap(862, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jbVoegToeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVoegToeActionPerformed
        if (validateNaam(tfNaam.getText()) && validateAchternaam(tfAchternaam.getText())
                && validateTussenvoegsel(tfTussenvoegsel.getText()) && validateStraat(tfStraat.getText())
                && validateHuisnummer(tfHuisnummer.getText()) && validateWoonplaats(tfWoonplaats.getText())
                && validatePostcode(tfPostcode.getText()) && validateTelefoon(tfTelefoon.getText())
                && validateEmail(tfEmail.getText()) && validateWachtwoord(tfWachtwoord.getPassword())
                && validateWachtwoordHerhaling(tfWachtwoordHerhaal.getPassword())) {

            
        
        Gebruiker g = new Gebruiker();
            g.setVoornaam(tfNaam.getText());
            g.setTussenvoegsel(tfTussenvoegsel.getText());
            g.setAchternaam(tfAchternaam.getText());
            g.setStraatnaam(tfStraat.getText());
            g.setHuisnummer(Integer.parseInt(tfHuisnummer.getText()));
            g.setWoonplaats(tfWoonplaats.getText());
            g.setPostcode(tfPostcode.getText());
            //g.setTelefoonnummer(tfTelefoon.getText());
            g.setEmail(tfEmail.getText());
            String wachtwoord = "";
            for(int i = 0; i <=tfWachtwoord.getPassword().length-1; i++){
                wachtwoord += tfWachtwoord.getPassword()[i];
            }
            g.setWachtwoord(wachtwoord);
            
            
            g.setDatum_aangemaakt(WinkelApplication.getCurrentTimeStamp());
            g.setDatum_gewijzigd(WinkelApplication.getCurrentTimeStamp());
            g.setDatum_laatst_ingelogd(WinkelApplication.getCurrentTimeStamp());
            
            if(source == NieuweKlant.KLANTEN_OVERZICHT_MEDEWERKER){
                System.out.println("test");
                g.setMedewerker(true);
            }
            
            g.setActief(true);
            //g.setisManager(0);
            //g.setId();
            
            
            main.WinkelApplication.getQueryManager().setGebruiker(g);
            
            //Keert terug naar het klantenoverzicht als deze klasse vanaf het klantenoverzicht is aangeroepen
            //Als deze klasse vanaf registreren is aangeroepen wordt er terug gekeerd naar de categorielijst.
            //Tevens wordt er een sessie gestart voor de net geregistreerde klant.
            if (source == NieuweKlant.KLANTEN_OVERZICHT || source == NieuweKlant.KLANTEN_OVERZICHT_MEDEWERKER) {
                main.WinkelApplication.getInstance().showPanel(new KlantenOverzicht());
            } else if (source == NieuweKlant.REGISTREREN) {
                main.WinkelApplication.getInstance().showPanel(new CategoryList());
            }
        }
    }//GEN-LAST:event_jbVoegToeActionPerformed

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

           
        if(source == NieuweKlant.KLANTEN_OVERZICHT || source == NieuweKlant.KLANTEN_OVERZICHT_MEDEWERKER){
            main.WinkelApplication.getInstance().showPanel(new KlantenOverzicht());
         } else if(source == NieuweKlant.REGISTREREN){
             main.WinkelApplication.getInstance().showPanel(new CategoryList());
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * A listener for the textfields The listener validates the input at the
     * time it changes.
     */
    private class ValidationListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            validateFields(e.getDocument());
        }

        public void removeUpdate(DocumentEvent e) {
            validateFields(e.getDocument());

        }

        public void changedUpdate(DocumentEvent e) {
            validateFields(e.getDocument());
        }

        private void validateFields(Document d) {
            if (d.equals(tfNaam.getDocument())) {
                validateNaam(tfNaam.getText());
            } else if (d.equals(tfAchternaam.getDocument())) {
                validateAchternaam(tfAchternaam.getText());
            } else if (d.equals(tfTussenvoegsel.getDocument())) {
                validateTussenvoegsel(tfTussenvoegsel.getText());
            } else if (d.equals(tfHuisnummer.getDocument())) {
                validateHuisnummer(tfHuisnummer.getText());
            } else if (d.equals(tfEmail.getDocument())) {
                validateEmail(tfEmail.getText());
            } else if (d.equals(tfWoonplaats.getDocument())) {
                validateWoonplaats(tfWoonplaats.getText());
            } else if (d.equals(tfTelefoon.getDocument())) {
                validateTelefoon(tfTelefoon.getText());
            } else if (d.equals(tfPostcode.getDocument())) {
                validatePostcode(tfPostcode.getText());
            } else if (d.equals(tfWoonplaats.getDocument())) {
                validateWoonplaats(tfWoonplaats.getText());
            } else if (d.equals(tfStraat.getDocument())) {
                validateStraat(tfStraat.getText());
            } else if (d.equals(tfWachtwoord.getDocument())) {
                validateWachtwoord(tfWachtwoord.getPassword());
            } else if (d.equals(tfWachtwoordHerhaal.getDocument())) {
                validateWachtwoordHerhaling(tfWachtwoordHerhaal.getPassword());
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton jbVoegToe;
    private javax.swing.JLabel jlTitel;
    private javax.swing.JTextField tfAchternaam;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfHuisnummer;
    private javax.swing.JTextField tfNaam;
    private javax.swing.JTextField tfPostcode;
    private javax.swing.JTextField tfStraat;
    private javax.swing.JTextField tfTelefoon;
    private javax.swing.JTextField tfTussenvoegsel;
    private javax.swing.JPasswordField tfWachtwoord;
    private javax.swing.JPasswordField tfWachtwoordHerhaal;
    private javax.swing.JTextField tfWoonplaats;
    // End of variables declaration//GEN-END:variables
}
