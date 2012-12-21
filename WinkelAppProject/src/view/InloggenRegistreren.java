package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import main.WinkelApplication;
import model.Session;

/**
 * @version 1.0
 * @author Tjarco
 *
 * De klasse waarin de gebruiker in kan loggen of kan kiezen voor registreren.
 * Als er producten in de winkelmand zitten kan de gebruiker ook afrekenen
 * zonder in te loggen.
 */
public class InloggenRegistreren extends MainLayout {

    private JPanel content;
    private JPanel inloggen;
    private JPanel registreren;
    private GridBagConstraints gbc;
    private InloggenDisplay inloggendisplay = new InloggenDisplay();

    public InloggenRegistreren() {
        super.AddTitle("Inloggen of Registreren");
        initComponents();
    }

    private void initComponents() {
        content = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        addInloggen();
        addRegistreren();
        addPayWithoutAccount();

        add(content, BorderLayout.CENTER);

        super.addBackButton(new CategoryList());
    }

    /**
     * Zet het panel met voor inloggen op het scherm *
     */
    private void addInloggen() {
        inloggen = new JPanel(new GridBagLayout());
        GridBagConstraints gbcIn = new GridBagConstraints();
        inloggen.setBackground(Color.LIGHT_GRAY);

        inloggen.setMinimumSize(new Dimension(200, 200));
        inloggen.setMaximumSize(new Dimension(400, 400));
        inloggen.setPreferredSize(new Dimension(300, 300));

        gbcIn.insets = new Insets(0, 0, 15, 15);
        gbcIn.anchor = GridBagConstraints.FIRST_LINE_START;

        JLabel titel = new JLabel("Inloggen");
        titel.setFont(main.WinkelApplication.FONT_18_BOLD);
        titel.setForeground(Color.darkGray);
        gbcIn.gridx = 0;
        gbcIn.gridy = 0;
        gbcIn.anchor = GridBagConstraints.LINE_START;
        inloggen.add(titel, gbcIn);

        JLabel lblmail = new JLabel("Email: ");
        lblmail.setFont(main.WinkelApplication.FONT_12_BOLD);
        gbcIn.gridx = 0;
        gbcIn.gridy = 1;
        inloggen.add(lblmail, gbcIn);

        JTextField txtmail = new JTextField();
        txtmail.setMinimumSize(new Dimension(100, 20));
        txtmail.setMaximumSize(new Dimension(300, 20));
        txtmail.setPreferredSize(new Dimension(150, 20));
        gbcIn.gridx = 1;
        gbcIn.gridy = 1;
        inloggen.add(txtmail, gbcIn);

        JLabel lblwachtwoord = new JLabel("Wachtwoord:");
        lblwachtwoord.setFont(main.WinkelApplication.FONT_12_BOLD);
        gbcIn.gridx = 0;
        gbcIn.gridy = 2;
        inloggen.add(lblwachtwoord, gbcIn);

        JPasswordField jpwachtwoord = new JPasswordField();
        jpwachtwoord.setMinimumSize(new Dimension(100, 20));
        jpwachtwoord.setMaximumSize(new Dimension(300, 20));
        jpwachtwoord.setPreferredSize(new Dimension(150, 20));
        gbcIn.gridx = 1;
        gbcIn.gridy = 2;
        inloggen.add(jpwachtwoord, gbcIn);


        JButton btninloggen = new JButton("Inloggen");
        btninloggen.setFont(main.WinkelApplication.FONT_14_BOLD);
        gbcIn.gridx = 0;
        gbcIn.gridy = 3;
        inloggen.add(btninloggen, gbcIn);

        btninloggen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Session.setIngelogd(true);
                WinkelApplication.getInstance().showPanel(new CategoryList());
            }
        });

        content.add(inloggen, gbc);

    }

    /**
     * De methode om de panel voor registreren toe te voegen.
     *
     */
    private void addRegistreren() {
        registreren = new JPanel(new GridBagLayout());
        GridBagConstraints gbcReg = new GridBagConstraints();

        registreren.setBackground(Color.lightGray);
        registreren.setMinimumSize(new Dimension(200, 200));
        registreren.setMaximumSize(new Dimension(400, 400));
        registreren.setPreferredSize(new Dimension(300, 300));

        gbcReg.insets = new Insets(0, 0, 15, 15);
        gbcReg.anchor = GridBagConstraints.FIRST_LINE_START;

        JLabel titel = new JLabel("Registreren");
        titel.setFont(WinkelApplication.FONT_18_BOLD);
        titel.setForeground(Color.darkGray);
        gbcReg.gridx = 0;
        gbcReg.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        registreren.add(titel, gbcReg);

        //html gebruiken om de label over twee regels te plaatsen.
        JLabel beschrijving = new JLabel("<html>Word lid van Outfit4You om vervolgbestellingen <br> makkelijker te maken</html>");
        beschrijving.setFont(new Font("Calibri", Font.ITALIC, 14));
        beschrijving.setMinimumSize(new Dimension(200, 50));
        beschrijving.setMaximumSize(new Dimension(350, 50));
        beschrijving.setPreferredSize(new Dimension(350, 50));
        gbcReg.gridx = 0;
        gbcReg.gridy = 1;
        registreren.add(beschrijving, gbcReg);

        JButton btnregistreren = new JButton("Registreren");
        btnregistreren.setFont(WinkelApplication.FONT_14_BOLD);
        btnregistreren.addActionListener(new ButtonListener());
        gbcReg.gridx = 0;
        gbcReg.gridy = 2;
        registreren.add(btnregistreren, gbcReg);

        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(registreren, gbc);
    }

    private void addPayWithoutAccount() {
        JButton pay = new JButton("Betalen zonder Account");
        pay.setMinimumSize(new Dimension(100, 50));
        pay.setMaximumSize(new Dimension(200, 50));
        pay.setPreferredSize(new Dimension(150, 50));
        pay.addActionListener(new ButtonListener());

        if (main.WinkelApplication.getBasket().size() == 0) {
            pay.setEnabled(false);
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        content.add(pay, gbc);

    }

    private class ButtonListener implements ActionListener {

        /**
         * Afhandelen van Events Als de source de registreren button is wordt
         * het nieuwe klant scherm getoond. Als de source de pay button is wordt
         * het betaalscherm geopend.
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equalsIgnoreCase("Registreren")) {
                main.WinkelApplication.getInstance().showPanel(new NieuweKlant(NieuweKlant.REGISTREREN));
            } else {
                main.WinkelApplication.getInstance().showPanel(new Payment());
            }
        }
    }
}
