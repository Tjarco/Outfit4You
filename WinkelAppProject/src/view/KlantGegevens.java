
package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.WinkelApplication;
import model.Gebruiker;
import model.Session;

/**
 * @version 1.0
 * @author Tjarco
 * 
 * Het hoofdmenu van de applicatie, alleen toegankelijk voor de medewerker.
 * Vanaf hier kan er naar de verschillende sub-menu's genavigeerd worden.
 * De sub-menu's zijn:
 * -Categorielijst
 * -Klantenoverzicht
 * -Productovericht
 * -Voorraad
 * -Rapporten
 */
public class KlantGegevens extends javax.swing.JPanel{
JButton jbTerug;
Gebruiker gebruiker;
String wijzigingonthoud;

    /**
     * Creates new form MainMenu
     */
    public KlantGegevens() {
        gebruiker = Session.getGebruiker();
        initComponents();
        addInlog();
        addBasket();
        addBackButton();
        loadGegevens();
    }
        private void addBasket() {
        BasketDisplay basket = new BasketDisplay();
        this.add(basket, BorderLayout.EAST);
    }
        private void addInlog(){
        InloggenDisplay logIn = new InloggenDisplay();
        this.add(logIn, BorderLayout.NORTH);
    }
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
        jbTerug.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    
                    WinkelApplication.getInstance().showPanel(new CategoryList());
                }
            });
        
        
        down.add(jbTerug);        
        
        add(down, BorderLayout.PAGE_END);
    }
        private void loadGegevens(){
            tfNaam.setText(gebruiker.getVoornaam());
            tfTussenvoegsel.setText(gebruiker.getTussenvoegsel());
            tfAchternaam.setText(gebruiker.getAchternaam());
            tfStraatnaam.setText(gebruiker.getStraatnaam());
            tfHuisnummer.setText(Integer.toString(gebruiker.getHuisnummer()));
            tfWoonplaats.setText(gebruiker.getWoonplaats());
            tfPostcode.setText(gebruiker.getPostcode());
            tfEmail.setText(gebruiker.getEmail());
            tfGeboortedatum.setText(gebruiker.getGeboortedatum());
        }
        
        
        
        private void newFrame(final int methodenummer, String titel, String tekst){
            final JFrame frame=new JFrame(titel);
            JButton bWijzigen=new JButton("Wijzigen");
            JButton bAnnuleren=new JButton("Annuleren");
            JLabel lTekst=new JLabel(tekst);
            final JTextField tfInvoer=new JTextField();
            JPanel panel=new JPanel();
            
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
                    String format;
                    //wel wijzigen
                    if (!tfInvoer.getText().equals("")){
                        switch (methodenummer) {
                            case 1: { 
                                format = "[a-zA-Z . \\s]+";
                            
                                if (tfInvoer.getText().matches(format)){
                                gebruiker.setVoornaam(tfInvoer.getText());
                                resultaatFrame("Profiel wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen geldige naam");
                                }
                            }
                                break;
                            case 2: {
                            
                                if (tfInvoer.getText().length()<20){
                                gebruiker.setTussenvoegsel(tfInvoer.getText());
                                resultaatFrame("Profiel wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen geldige tussenvoegsel");
                                }
                            }
                                break;
                            case 3: {
                                format = "[a-zA-Z \\s]+";
                            
                                if (tfInvoer.getText().matches(format)){
                                gebruiker.setAchternaam(tfInvoer.getText());
                                resultaatFrame("Profiel wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen geldige achternaam");
                                }
                            }
                                break;
                            case 4: {
                                format = "[a-zA-Z . \\s]+";
                            
                                if (tfInvoer.getText().matches(format)){
                                gebruiker.setStraatnaam(tfInvoer.getText());
                                resultaatFrame("Profiel wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen geldige straatnaam");
                                }
                            }
                                break;
                            case 5: {
                                format = "([\\d]+)([a-zA-Z]?)";
                            
                                if (tfInvoer.getText().matches(format)){
                                gebruiker.setHuisnummer(Integer.parseInt(tfInvoer.getText()));
                                resultaatFrame("Profiel wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen geldig huisnummer");
                                }
                            }
                                break;
                            case 6: {
                                format = "[a-zA-Z ' \\s]+";
                            
                                if (tfInvoer.getText().matches(format)){
                                gebruiker.setWoonplaats(tfInvoer.getText());
                                resultaatFrame("Profiel wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen geldige woonplaats");
                                }
                            }
                                break;
                            case 7: {
                                format = "[\\d]{4}+[A-Z]{2}";
                            
                                if (tfInvoer.getText().matches(format)){
                                gebruiker.setPostcode(tfInvoer.getText());
                                resultaatFrame("Profiel wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen geldige postcode");
                                }
                            }
                                break;
                            case 8: {
                                format = "\\d{4}-\\d{2}-\\d{2}";
                                if (tfInvoer.getText().matches(format)){
                                    gebruiker.setGeboortedatum(tfInvoer.getText());
                                    resultaatFrame("Profie wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                    frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen juiste geboortedatum. [Voorbeeld: 2000-01-12");
                                }
                            }
                                
                            case 9: { 
                                format = "(.+)+[@]+(.+)+[.]+([\\w]){1,6}";
                            
                                if (tfInvoer.getText().matches(format)){
                                    gebruiker.setEmail(tfInvoer.getText());
                                    resultaatFrame("Profiel wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                                    frame.dispose();
                                }
                                else { resultaatFrame("Fout", "Dit is geen geldige e-mail");
                                }
                            }
                                break;



                                default: System.out.println("verkeerde nummer");
                        }
                        loadGegevens();
                    }
                    else resultaatFrame("Fout" , "U heeft niks ingevuld");
                }
            });
            
            bAnnuleren.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    frame.dispose();
                    //resultaatFrame("Fout", "Gegevens zijn niet bijgewerkt");
                    //niet wijzigen
                }
            });
            

            panel.add(bWijzigen);
            panel.add(bAnnuleren);
            
            frame.add(lTekst, BorderLayout.PAGE_START);
            frame.add(new JLabel("          "), BorderLayout.LINE_START);
            frame.add(tfInvoer, BorderLayout.CENTER);
            frame.add(new JLabel("          "), BorderLayout.LINE_END);
            frame.add(panel, BorderLayout.PAGE_END);
            
            
        }
        
        
        private void passwordFrame(){
            final JFrame frame = new JFrame("Wachtwoord wijzigen");
            frame.setVisible(true);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
         //   frame.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            Container pane=frame.getContentPane();
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));  
            
            JLabel          lOudWachtwoord = new JLabel("     Oud wachtwoord:           ");
            JLabel lEmpty=new JLabel(" ");
            JLabel          lNieuwWachtwoord=new JLabel("     Nieuw wachtwoord:         ");
            JLabel   lHerhaalNieuwWachtwoord=new JLabel("     Herhaling wachtwoord:     ");
            
            final JPasswordField pfOudWachtwoord=new JPasswordField();
            final JPasswordField pfNieuwWachtwoord=new JPasswordField();
            final JPasswordField pfNieuwWachtwoord2=new JPasswordField();
            JButton bWijzigen = new JButton("Wijzigen");
            
            pfOudWachtwoord.setPreferredSize(new Dimension(90,25));
            pfNieuwWachtwoord.setPreferredSize(new Dimension(90,25));
            pfNieuwWachtwoord2.setPreferredSize(new Dimension(90,25));
            //panel 1,2,3,4
            
            
            bWijzigen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    // als het oude wachtwoord niet klopt, een foutmelding geven
                    // hiervoor kan je de isvalidlogin gebruiken aangezien die true of false terug stuurd en de email van de gebruiker heb je al.
                    if (WinkelApplication.getQueryManager().isValidLogin(gebruiker.getEmail(), md5(pfOudWachtwoord.getText()))==false){
                        resultaatFrame("Wachtwoord wijzigen", "Fout: het ingevulde wachtwoord klopt niet");
                        //Fout: wachtwoord klopt niet
                    }
                    else // anders: als wachtwoord wel klopt
                        if (pfNieuwWachtwoord.getText().equals(pfNieuwWachtwoord2.getText())){
                            frame.dispose();
                            gebruiker.setWachtwoord(md5(pfNieuwWachtwoord2.getText()));
                            resultaatFrame("Wachtwoord wijzigen", "Druk op de knop 'Wijzigingen opslaan' om uw wijzigingen op te slaan.");
                            
                        }
                        else {
                            resultaatFrame("Wachtwoord wijzigen", "Fout: de nieuwe wachtwoord velden zijn niet gelijk aan elkaar");
                        //nieuwe passwords zijn niet gelijk aan elkaar
                        }
                    //resultaatFrame("Fout", "Gegevens zijn niet bijgewerkt");
                    //niet wijzigen
                }
            });
            
            
            
            JPanel r1=new JPanel();
            JPanel r3=new JPanel();
            JPanel r4=new JPanel();
            JPanel r5=new JPanel();
            r1.add(lOudWachtwoord);
            r1.add(pfOudWachtwoord);
            r3.add(lNieuwWachtwoord);
            r3.add(pfNieuwWachtwoord);
            r4.add(lHerhaalNieuwWachtwoord);
            r4.add(pfNieuwWachtwoord2);
            r5.add(bWijzigen);
            pane.add(r1);
            pane.add(lEmpty);// r2
            pane.add(r3);
            pane.add(r4);
            pane.add(r5);
            
            
        }
        
        
        
        private void resultaatFrame(String titel, String tekst){
            JOptionPane.showMessageDialog(this,
                tekst,//"tekst",
                titel,//"titel",
                JOptionPane.PLAIN_MESSAGE);
        }
        
        private String md5(String in){
        String hashword=null;
        try{
            MessageDigest md5=
                    MessageDigest.getInstance("MD5");
            md5.update(in.getBytes());
            BigInteger hash= new BigInteger(1, md5.digest());
            hashword=hash.toString(16);
        } catch (NoSuchAlgorithmException e){}
        return hashword;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lNaam = new javax.swing.JLabel();
        tfNaam = new javax.swing.JTextField();
        lTussenvoegsel = new javax.swing.JLabel();
        tfTussenvoegsel = new javax.swing.JTextField();
        lAchternaam = new javax.swing.JLabel();
        tfAchternaam = new javax.swing.JTextField();
        lStraatnaam = new javax.swing.JLabel();
        tfStraatnaam = new javax.swing.JTextField();
        lHuisnummer = new javax.swing.JLabel();
        tfHuisnummer = new javax.swing.JTextField();
        lWoonplaats = new javax.swing.JLabel();
        tfWoonplaats = new javax.swing.JTextField();
        lPostcode = new javax.swing.JLabel();
        tfPostcode = new javax.swing.JTextField();
        lEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jbWachtwoordWijzigen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lTussenvoegselWijzigen = new javax.swing.JLabel();
        lNaamWijzigen = new javax.swing.JLabel();
        lAchternaamWijzigen = new javax.swing.JLabel();
        lStraatnaamWijzigen = new javax.swing.JLabel();
        lHuisnummerWijzigen = new javax.swing.JLabel();
        lWoonplaatsWijzigen = new javax.swing.JLabel();
        lPostcodeWijzigen = new javax.swing.JLabel();
        lEmailWijzigen = new javax.swing.JLabel();
        jbOpslaan = new javax.swing.JButton();
        lGeboortedatum = new javax.swing.JLabel();
        tfGeboortedatum = new javax.swing.JTextField();
        lGeboortedatumWijzigen = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        jButton2.setText("jButton2");

        setLayout(new java.awt.BorderLayout());

        lNaam.setText("Naam");

        tfNaam.setEditable(false);

        lTussenvoegsel.setText("Tussenvoegsel");

        tfTussenvoegsel.setEditable(false);

        lAchternaam.setText("Achternaam");

        tfAchternaam.setEditable(false);

        lStraatnaam.setText("Straatnaam");

        tfStraatnaam.setEditable(false);

        lHuisnummer.setText("Huisnummer");

        tfHuisnummer.setEditable(false);

        lWoonplaats.setText("Woonplaats");

        tfWoonplaats.setEditable(false);

        lPostcode.setText("Postcode");

        tfPostcode.setEditable(false);

        lEmail.setText("E-mail");

        tfEmail.setEditable(false);

        jbWachtwoordWijzigen.setText("Wachtwoord wijzigen");
        jbWachtwoordWijzigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbWachtwoordWijzigenActionPerformed(evt);
            }
        });

        jLabel1.setText(" ");

        lTussenvoegselWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lTussenvoegselWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lTussenvoegselWijzigenMousePressed(evt);
            }
        });

        lNaamWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lNaamWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lNaamWijzigenMousePressed(evt);
            }
        });

        lAchternaamWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lAchternaamWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lAchternaamWijzigenMousePressed(evt);
            }
        });

        lStraatnaamWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lStraatnaamWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lStraatnaamWijzigenMousePressed(evt);
            }
        });

        lHuisnummerWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lHuisnummerWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lHuisnummerWijzigenMousePressed(evt);
            }
        });

        lWoonplaatsWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lWoonplaatsWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lWoonplaatsWijzigenMousePressed(evt);
            }
        });

        lPostcodeWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lPostcodeWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lPostcodeWijzigenMousePressed(evt);
            }
        });

        lEmailWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lEmailWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lEmailWijzigenMousePressed(evt);
            }
        });

        jbOpslaan.setText("Wijzigingen opslaan");
        jbOpslaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOpslaanActionPerformed(evt);
            }
        });

        lGeboortedatum.setText("Geboortedatum");

        tfGeboortedatum.setEditable(false);

        lGeboortedatumWijzigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons/wijzigen.png"))); // NOI18N
        lGeboortedatumWijzigen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lGeboortedatumWijzigenMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(lPostcode)
                                                .addGap(120, 120, 120))
                                            .addComponent(lWoonplaats))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lHuisnummer)
                                                .addComponent(lEmail))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addComponent(lStraatnaam))
                                .addComponent(lAchternaam))
                            .addComponent(lTussenvoegsel))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(lNaam)
                            .addGap(73, 73, 73)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lGeboortedatum)
                        .addGap(135, 135, 135)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tfGeboortedatum, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lGeboortedatumWijzigen))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNaam, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                    .addComponent(tfTussenvoegsel))
                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbOpslaan))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lTussenvoegselWijzigen)
                                .addComponent(jbWachtwoordWijzigen)
                                .addComponent(lNaamWijzigen)
                                .addComponent(lAchternaamWijzigen)
                                .addComponent(lStraatnaamWijzigen)
                                .addComponent(lHuisnummerWijzigen)
                                .addComponent(lWoonplaatsWijzigen)
                                .addComponent(lPostcodeWijzigen)
                                .addComponent(lEmailWijzigen)))
                        .addComponent(tfAchternaam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfStraatnaam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfHuisnummer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfWoonplaats, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfPostcode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(596, 596, 596))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lNaam))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTussenvoegsel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lTussenvoegsel)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lNaamWijzigen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lTussenvoegselWijzigen)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfAchternaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lAchternaam))
                    .addComponent(lAchternaamWijzigen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfStraatnaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lStraatnaam))
                    .addComponent(lStraatnaamWijzigen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfHuisnummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lHuisnummer))
                    .addComponent(lHuisnummerWijzigen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfWoonplaats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lWoonplaats))
                    .addComponent(lWoonplaatsWijzigen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lPostcode))
                    .addComponent(lPostcodeWijzigen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lEmailWijzigen)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lEmail)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lGeboortedatumWijzigen)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfGeboortedatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lGeboortedatum)))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbWachtwoordWijzigen)
                    .addComponent(jbOpslaan))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(main.WinkelApplication.BACKGROUND);
        jPanel3.setPreferredSize(new java.awt.Dimension(892, 70));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1483, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        add(jPanel3, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void lTussenvoegselWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lTussenvoegselWijzigenMousePressed
        newFrame(2, "Tussenvoegsel wijzigen", "Uw tussenvoegsel wijzigen in: ");
    }//GEN-LAST:event_lTussenvoegselWijzigenMousePressed

    private void lNaamWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lNaamWijzigenMousePressed
        newFrame(1, "Naam wijzigen", "Uw naam wijzigen in: ");
    }//GEN-LAST:event_lNaamWijzigenMousePressed

    private void lAchternaamWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lAchternaamWijzigenMousePressed
        newFrame(3, "Achternaam wijzigen", "Uw achternaam wijzigen in: ");
    }//GEN-LAST:event_lAchternaamWijzigenMousePressed

    private void lStraatnaamWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lStraatnaamWijzigenMousePressed
        newFrame(4, "Straatnaam wijzigen", "Uw straatnaam wijzigen in: ");
    }//GEN-LAST:event_lStraatnaamWijzigenMousePressed

    private void lHuisnummerWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lHuisnummerWijzigenMousePressed
        newFrame(5, "Huisnummer wijzigen", "Uw huisnummer wijzigen in: ");
    }//GEN-LAST:event_lHuisnummerWijzigenMousePressed

    private void lWoonplaatsWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lWoonplaatsWijzigenMousePressed
        newFrame(6, "Woonplaats wijzigen", "Uw woonplaats wijzigen in: ");
    }//GEN-LAST:event_lWoonplaatsWijzigenMousePressed

    private void lPostcodeWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lPostcodeWijzigenMousePressed
        newFrame(7, "Postcode wijzigen", "Uw postcode wijzigen in: ");
    }//GEN-LAST:event_lPostcodeWijzigenMousePressed

    private void lEmailWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lEmailWijzigenMousePressed
        newFrame(9, "E-mail wijzigen", "Uw E-mail wijzigen in: ");
    }//GEN-LAST:event_lEmailWijzigenMousePressed

    private void jbWachtwoordWijzigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbWachtwoordWijzigenActionPerformed
        passwordFrame();
    }//GEN-LAST:event_jbWachtwoordWijzigenActionPerformed

    private void jbOpslaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOpslaanActionPerformed
        WinkelApplication.getQueryManager().setGebruiker(gebruiker);
        InloggenDisplay.updateLoginDisplay(gebruiker.getVoornaam());
        resultaatFrame("Profiel wijzigen", "Uw wijzigingen zijn opgeslagen");
    }//GEN-LAST:event_jbOpslaanActionPerformed

    private void lGeboortedatumWijzigenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lGeboortedatumWijzigenMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lGeboortedatumWijzigenMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbOpslaan;
    private javax.swing.JButton jbWachtwoordWijzigen;
    private javax.swing.JLabel lAchternaam;
    private javax.swing.JLabel lAchternaamWijzigen;
    private javax.swing.JLabel lEmail;
    private javax.swing.JLabel lEmailWijzigen;
    private javax.swing.JLabel lGeboortedatum;
    private javax.swing.JLabel lGeboortedatumWijzigen;
    private javax.swing.JLabel lHuisnummer;
    private javax.swing.JLabel lHuisnummerWijzigen;
    private javax.swing.JLabel lNaam;
    private javax.swing.JLabel lNaamWijzigen;
    private javax.swing.JLabel lPostcode;
    private javax.swing.JLabel lPostcodeWijzigen;
    private javax.swing.JLabel lStraatnaam;
    private javax.swing.JLabel lStraatnaamWijzigen;
    private javax.swing.JLabel lTussenvoegsel;
    private javax.swing.JLabel lTussenvoegselWijzigen;
    private javax.swing.JLabel lWoonplaats;
    private javax.swing.JLabel lWoonplaatsWijzigen;
    private javax.swing.JTextField tfAchternaam;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfGeboortedatum;
    private javax.swing.JTextField tfHuisnummer;
    private javax.swing.JTextField tfNaam;
    private javax.swing.JTextField tfPostcode;
    private javax.swing.JTextField tfStraatnaam;
    private javax.swing.JTextField tfTussenvoegsel;
    private javax.swing.JTextField tfWoonplaats;
    // End of variables declaration//GEN-END:variables
}
