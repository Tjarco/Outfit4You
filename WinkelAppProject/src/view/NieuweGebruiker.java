/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import main.WinkelApplication;
import model.Gebruiker;

/**
 * @version 1.1
 * @author Kevin Medewerker toevoegen
 *
 * @version 1.0
 * @author Tjarco De klasse met de velden om een nieuwe klant toe te voegen.
 * Deze klasse kan vanaf meerdere klasse gebruikt worden, daarom moet als
 * parameter worden meegegeven wat de source is, zodat kan worden teruggekeerd
 * naar het juiste menu na het registreren.
 */
public class NieuweGebruiker extends JPanel {

    public static final int REGISTREREN = 0;
    public static final int KLANTEN_OVERZICHT = 1;
    public static final int KLANTEN_OVERZICHT_MEDEWERKER = 2;
    private int source;

    /**
     * Creates new form NieuweGebruiker
     */
    public NieuweGebruiker(int source) {
        initComponents();
        this.source = source;

        //Make the listener to validate the textfields
        ValidationListener lis = new ValidationListener();
        tfWoonplaats.getDocument().addDocumentListener(lis);
        tfHuisnummer.getDocument().addDocumentListener(lis);
        tfNaam.getDocument().addDocumentListener(lis);
        tfStraat.getDocument().addDocumentListener(lis);
        tfGeboortedatum.getDocument().addDocumentListener(lis);
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

        if (!validatie.matches(format)) {
            tfPostcode.setBackground(Color.red);
            isValid = false;
        } else {
            tfPostcode.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateNaam(String validatie) {
        Boolean isValid = true;
        String format = "[a-zA-Z . \\s]+";
        if (validatie.length() > 49 || !validatie.matches(format)) {
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

    private Boolean validateGeboorteDatum(String validatie) {
        Boolean isValid = true;
        String format = "\\d{4}-\\d{2}-\\d{2}";

        int jaar =0, maand=0 , dag=0;
        try {
             jaar = Integer.parseInt(tfGeboortedatum.getText().substring(0, 4));
            System.out.println(jaar);
             maand = Integer.parseInt(tfGeboortedatum.getText().substring(5, 7));
             dag = Integer.parseInt(tfGeboortedatum.getText().substring(8, 10));
        } catch (Exception e) {
            //de datum is nog niet compleet
        }
        if (!validatie.matches(format)  || !(jaar < 2013) || !(maand <= 12) || !(dag <= 31)) {
       
            tfGeboortedatum.setBackground(Color.red);
            isValid = false;
        } else {
            tfGeboortedatum.setBackground(Color.green);
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
    private void initComponents() {

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
        jLabel11 = new javax.swing.JLabel();
        tfWachtwoord = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        tfWachtwoordHerhaal = new javax.swing.JPasswordField();
        jbVoegToe = new javax.swing.JButton();
        tfGeboortedatum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jbBack = new javax.swing.JButton();

        jTextField6.setText("jTextField6");

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(487, 500));

        jLabel2.setText("Naam");

        jLabel4.setText("Postcode");

        jLabel5.setText("Woonplaats");

        jlTitel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlTitel.setText("Klantgegevens:");

        jLabel1.setText("Tussenvoegsel");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        jLabel7.setText("Achternaam");

        jLabel8.setText("Straat");

        jLabel9.setText("Huisnummer");

        jLabel10.setText("E-mail");

        jLabel11.setText("Wachtwoord");

        jLabel12.setText("Herhaal Wachtwoord");

        jbVoegToe.setText("Voeg toe");
        jbVoegToe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVoegToeActionPerformed(evt);
            }
        });

        jLabel3.setText("Geboortedatum:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jlTitel)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jbVoegToe)
                                    .addComponent(jLabel3))
                                .addGap(0, 17, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfWoonplaats, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfWachtwoord, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfWachtwoordHerhaal, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(tfStraat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfHuisnummer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfNaam)
                                    .addComponent(tfTussenvoegsel)
                                    .addComponent(tfAchternaam)
                                    .addComponent(tfGeboortedatum))))
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
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tfNaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(tfTussenvoegsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(tfAchternaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfGeboortedatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfStraat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(tfHuisnummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfWoonplaats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfWachtwoord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfWachtwoordHerhaal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jbVoegToe)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 83, Short.MAX_VALUE)
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
                .addContainerGap(862, Short.MAX_VALUE))
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

    private void jbVoegToeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVoegToeActionPerformed
        if (validateNaam(tfNaam.getText()) && validateAchternaam(tfAchternaam.getText())
                && validateTussenvoegsel(tfTussenvoegsel.getText()) && validateStraat(tfStraat.getText())
                && validateHuisnummer(tfHuisnummer.getText()) && validateWoonplaats(tfWoonplaats.getText())
                && validatePostcode(tfPostcode.getText()) && this.validateGeboorteDatum(tfGeboortedatum.getText())
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
            g.setGeboortedatum(tfGeboortedatum.getText());
            g.setEmail(tfEmail.getText());
            String wachtwoord = "";
            for (int i = 0; i <= tfWachtwoord.getPassword().length - 1; i++) {
                wachtwoord += tfWachtwoord.getPassword()[i];
            }
            g.setWachtwoord(md5(wachtwoord));


            g.setDatum_aangemaakt(WinkelApplication.getCurrentTimeStamp());
            g.setDatum_gewijzigd(WinkelApplication.getCurrentTimeStamp());
            g.setDatum_laatst_ingelogd(WinkelApplication.getCurrentTimeStamp());

            if (source == NieuweGebruiker.KLANTEN_OVERZICHT_MEDEWERKER) {
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
            if (source == NieuweGebruiker.KLANTEN_OVERZICHT || source == NieuweGebruiker.KLANTEN_OVERZICHT_MEDEWERKER) {
                main.WinkelApplication.getInstance().showPanel(new GebruikerOverzicht());
            } else if (source == NieuweGebruiker.REGISTREREN) {
                main.WinkelApplication.getInstance().showPanel(new CategoryList());
            }
        } else {
            //Toon de dingen die fout zijn:
            String message = "<html><h3>De volgende dingen zijn niet correct ingevuld:</h3> ";
            if (!this.validateNaam(tfNaam.getText())) {
                message += "<br/>Naam: De naam is te lang of bevat ongebruikelijke tekens.<br/>";
            }
            if (!this.validateAchternaam(tfAchternaam.getText())) {
                message += "<br/>Achternaam: De achternaam is te lang of bevat ongebruikelijke tekens.<br/>";
            }
            if (!this.validateStraat(tfStraat.getText())) {
                message += "<br/>Straat: De straatnaam is te lang of bevat ongebruikelijke tekens.<br/>";
            }
            if (!this.validateHuisnummer(tfHuisnummer.getText())) {
                message += "<br/>Huisnummer: Het huisnummer is te lang of <u>begint</u> met een letter.<br/>";
            }
            if (!this.validateWoonplaats(tfWoonplaats.getText())) {
                message += "<br/>Woonplaats: De naam is te lang of bevat ongebruikelijke tekens<br/>";
            }
            if (!this.validatePostcode(tfPostcode.getText())) {
                message += "<br/>Postcode: de postcode moet in de volgende vorm gegeven worden: '1000AA'<br/> ";
            }
            if (!this.validateGeboorteDatum(tfGeboortedatum.getText())) {
                message += "<Br/>Geboortedatum: De geboortedatum moet in het format jjj-mm-dd gegeven worden<br/>";
            }
            if (!this.validateEmail(tfEmail.getText())) {
                message += "<br/>Email: Het emailadres kan in de volgende vormen gegeven worden:<br/>"
                        + " 'iemand@host.nl', 'ik.naam.achternaam@host.com'<br/>";
            }
            if (!this.validateWachtwoordHerhaling(tfWachtwoord.getPassword())) {
                message += "<br/>Wachtwoord: de wachtwoorden komen niet overeen";
            }


            message += "</html>";
            JOptionPane.showMessageDialog(this, message);

        }
    }

    private String md5(String in) {
        String hashword = null;
        try {
            MessageDigest md5 =
                    MessageDigest.getInstance("MD5");
            md5.update(in.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            hashword = hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
        }
        return hashword;
    }//GEN-LAST:event_jbVoegToeActionPerformed

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jbBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBackActionPerformed


        if (source == NieuweGebruiker.KLANTEN_OVERZICHT || source == NieuweGebruiker.KLANTEN_OVERZICHT_MEDEWERKER) {
            main.WinkelApplication.getInstance().showPanel(new GebruikerOverzicht());
        } else if (source == NieuweGebruiker.REGISTREREN) {
            main.WinkelApplication.getInstance().showPanel(new CategoryList());
        }
    }//GEN-LAST:event_jbBackActionPerformed

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
            } else if (d.equals(tfGeboortedatum.getDocument())) {
                validateGeboorteDatum(tfGeboortedatum.getText());
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
    private javax.swing.JButton jbBack;
    private javax.swing.JButton jbVoegToe;
    private javax.swing.JLabel jlTitel;
    private javax.swing.JTextField tfAchternaam;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfGeboortedatum;
    private javax.swing.JTextField tfHuisnummer;
    private javax.swing.JTextField tfNaam;
    private javax.swing.JTextField tfPostcode;
    private javax.swing.JTextField tfStraat;
    private javax.swing.JTextField tfTussenvoegsel;
    private javax.swing.JPasswordField tfWachtwoord;
    private javax.swing.JPasswordField tfWachtwoordHerhaal;
    private javax.swing.JTextField tfWoonplaats;
    // End of variables declaration//GEN-END:variables
}
