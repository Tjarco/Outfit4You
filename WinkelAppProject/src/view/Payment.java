package view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.WinkelApplication;
import model.Product;
import model.Session;
import java.net.URI;
import java.awt.Desktop;
import java.io.IOException;
import java.util.Random;
import model.Gebruiker;
import model.Statistiek;

/**
 * @author Administrator
 *
 * @version 1.0 Initial Payment class created.
 */
/**
 * @author Bono
 *
 * @version 1.1 Ingelogde gebruikers informatie wordt automagisch ingevuld in de
 * velden, deze kan altijd nog veranderd worden door de gebruiker.
 */
/**
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * @version 1.2
 *
 * Laat gebruiker waardes in zijn basket veranderen, de input boxes kunnen geen
 * hogere waardes aannemen dan de voorraad van een bepaald product. Gebruikers
 * worden doorgestuurd naar een betaalpagina van de Rabobank Query van
 * OrderSend() is nu kloppend gemaakt met de database update
 *
 * Let op: bugfix nodig om repaint te fixen wanneer de waardes in de basket
 * worden veranderd. Sommige teksten komen nu over elkaar heen te staan
 */
public class Payment extends MainLayout implements MouseListener, ActionListener {

    private final int verticalPosition = 80;
    private final int productOffset = 20;
    private final int formOffset = 30;
    private JTextField tfNaam;
    private JTextField tfAddress;
    private JTextField tfPostcode;
    private JTextField tfWoonplaats;
    private JComboBox cmbPayMethod;
    private JTextField tfNote;
    private JLabel lblFormTitle;
    private final String[] payMethods = {"iDeal", "Contante betaling"};
    private JPanel content;
    private JPanel container;
    public JPanel price;

    public Payment() {



        super.AddTitle("Betalen");
        super.addBackButton(new CategoryList());

        container = new JPanel();
        container.setLayout(null);

        container.setOpaque(true);

        content = new JPanel();
        content.setLayout(null);
        content.setBounds(250, 20, 900, 600);

        price = new JPanel();
        price.setLayout(null);
        price.setOpaque(false);
        price.setBounds(0, 20, 900, 600);


        initComponents();

        container.add(content);
        content.add(price);
        add(container);

    }

    /**
     * Validators voor de textfields.
     *
     * Als het format niet correct is zal het bijbehorende veld rood kleuren.
     * Deze methoden Geven ook een boolean terug, dus kunnen ook gebruikt worden
     * om te testen of de formats correct zijn.
     *
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
        if (validatie.length() > 49 || validatie.length() == 0 || !validatie.matches(format)) {
            tfNaam.setBackground(Color.red);
            isValid = false;
        } else {
            tfNaam.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateAdres(String validatie) {
        Boolean isValid = true;
        String format = "([a-zA-Z \\s]+)([\\d]+)";
        if (validatie.length() > 49 || validatie.length() == 0 || !validatie.matches(format)) {
            tfAddress.setBackground(Color.red);
            isValid = false;
        } else {
            tfAddress.setBackground(Color.green);
        }
        return isValid;
    }

    private Boolean validateWoonplaats(String validatie) {
        Boolean isValid = true;
        String format = "[a-zA-Z ']+";
        if (validatie.length() > 49 || validatie.length() == 0 || !validatie.matches(format)) {
            tfWoonplaats.setBackground(Color.red);
            isValid = false;
        } else {
            tfWoonplaats.setBackground(Color.green);
        }
        return isValid;
    }

    private void initComponents() {
        addTitle();
        addProductList();
        addForm();
    }

    private void initProductList() {
        addProductList();
    }

    private void addTitle() {
        JLabel lblTitle1 = new JLabel();
        lblTitle1.setText("Winkelapplicatie");
        lblTitle1.setBounds(20, 20, 150, 20);
        lblTitle1.setFont(WinkelApplication.FONT_18_BOLD);
        content.add(lblTitle1);

        JLabel lblTitle2 = new JLabel();
        lblTitle2.setText("-");
        lblTitle2.setBounds(170, 20, 20, 20);
        lblTitle2.setFont(WinkelApplication.FONT_14_BOLD);
        content.add(lblTitle2);

        JLabel lblTitle3 = new JLabel();
        lblTitle3.setText("Betaling");
        lblTitle3.setBounds(190, 20, 500, 20);
        lblTitle3.setFont(WinkelApplication.FONT_14_BOLD);
        content.add(lblTitle3);

    }

    private void addProductList() {
        List<Product> products = WinkelApplication.getBasket().getProducts();

        JLabel lblProductHeader = new JLabel();
        lblProductHeader.setText("Producten");
        lblProductHeader.setBounds(20, 60, 150, 20);
        lblProductHeader.setFont(WinkelApplication.FONT_12_BOLD);
        price.add(lblProductHeader);

        JLabel lblAmountHeader = new JLabel();
        lblAmountHeader.setText("Aantal");
        lblAmountHeader.setBounds(400, 60, 150, 20);
        lblAmountHeader.setFont(WinkelApplication.FONT_12_BOLD);
        price.add(lblAmountHeader);

        JLabel lblPriceHeader = new JLabel();
        lblPriceHeader.setText("Prijs");
        lblPriceHeader.setBounds(480, 60, 150, 20);
        lblPriceHeader.setFont(WinkelApplication.FONT_12_BOLD);
        price.add(lblPriceHeader);

        for (int i = 0; i < products.size(); i++) {
            final Product product = products.get(i);

            JLabel lblProduct = new JLabel(product.getNaam());
            lblProduct.setBounds(20, verticalPosition + i * productOffset, 420, 20);
            lblProduct.setFont(WinkelApplication.FONT_12_PLAIN);
            price.add(lblProduct);

            final SpinnerNumberModel model = new SpinnerNumberModel(WinkelApplication.getBasket().getProductAmount(product), 0, WinkelApplication.getQueryManager().getVoorraad(product.getProduct_id()), 1);
            JSpinner amountItems = new JSpinner(model);
            amountItems.setBounds(400, verticalPosition + i * productOffset, 40, 20);
            amountItems.setFont(WinkelApplication.FONT_12_PLAIN);
            amountItems.addChangeListener(new ChangeListener() {

                public void stateChanged(ChangeEvent e) {
                    WinkelApplication.getBasket().removeProduct(product, (Integer) model.getValue());

                    price.removeAll();
                    price.revalidate();
                    price.repaint();
                    initProductList();
                }
            });

            content.add(amountItems);
            JLabel lblPrice = new JLabel(WinkelApplication.CURRENCY.format(product.getPrijs()));
            lblPrice.setBounds(480, verticalPosition + i * productOffset, 70, 20);
            lblPrice.setFont(WinkelApplication.FONT_12_PLAIN);
            price.add(lblPrice);
        }

        // create total labels
        JLabel lblTotal = new JLabel("Totaal: ");
        lblTotal.setBounds(20, verticalPosition + products.size() * productOffset, 50, 20);
        lblTotal.setFont(WinkelApplication.FONT_12_BOLD);
        price.add(lblTotal);

        // create total labels
        JLabel lblTotalPrice = new JLabel(WinkelApplication.CURRENCY.format(WinkelApplication.getBasket().getTotalCosts()));
        lblTotalPrice.setBounds(480, verticalPosition + products.size() * productOffset, 70, 20);
        lblTotalPrice.setFont(WinkelApplication.FONT_12_BOLD);
        price.add(lblTotalPrice);
    }

    private void addForm() {
        List<Product> products = WinkelApplication.getBasket().getProducts();

        lblFormTitle = new JLabel("Verzendgegevens");
        lblFormTitle.setBounds(20, verticalPosition + products.size() * productOffset + (formOffset * 2), 150, 20);
        lblFormTitle.setFont(WinkelApplication.FONT_14_BOLD);
        content.add(lblFormTitle);

        JLabel lblNaam = new JLabel("Naam:");
        lblNaam.setBounds(20, verticalPosition + products.size() * productOffset + (formOffset * 3), 100, 20);
        lblNaam.setFont(WinkelApplication.FONT_12_BOLD);
        content.add(lblNaam);

        tfNaam = new JTextField();
        tfNaam.setBounds(120, verticalPosition + products.size() * productOffset + (formOffset * 3), 130, 20);
        tfNaam.setFont(WinkelApplication.FONT_12_BOLD);
        tfNaam.setText("");
        if (Session.getGebruiker() != null) {
            System.out.println("gebruiker ingelogd: set naam etc.");
            tfNaam.setText(Session.getGebruiker().getVoornaam() + " " + Session.getGebruiker().getTussenvoegsel() + " " + Session.getGebruiker().getAchternaam());
        }
        tfNaam.getDocument().addDocumentListener(new ValidateListener());
        content.add(tfNaam);

        JLabel lblPostcode = new JLabel("Postcode:");
        lblPostcode.setBounds(320, verticalPosition + products.size() * productOffset + (formOffset * 3), 100, 20);
        lblPostcode.setFont(WinkelApplication.FONT_12_BOLD);
        content.add(lblPostcode);

        tfPostcode = new JTextField();
        tfPostcode.setBounds(420, verticalPosition + products.size() * productOffset + (formOffset * 3), 130, 20);
        tfPostcode.setFont(WinkelApplication.FONT_12_BOLD);
        tfPostcode.setText("");
        if (Session.getGebruiker() != null) {
            tfPostcode.setText(Session.getGebruiker().getPostcode());
        }
        tfPostcode.getDocument().addDocumentListener(new ValidateListener());
        content.add(tfPostcode);

        JLabel lblAddress = new JLabel("Adres:");
        lblAddress.setBounds(20, verticalPosition + products.size() * productOffset + (formOffset * 4), 100, 20);
        lblAddress.setFont(WinkelApplication.FONT_12_BOLD);
        content.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(120, verticalPosition + products.size() * productOffset + (formOffset * 4), 130, 20);
        tfAddress.setFont(WinkelApplication.FONT_12_BOLD);
        tfAddress.setText("");
        if (Session.getGebruiker() != null) {
            tfAddress.setText(Session.getGebruiker().getStraatnaam() + " " + Session.getGebruiker().getHuisnummer());
        }
        tfAddress.getDocument().addDocumentListener(new ValidateListener());
        content.add(tfAddress);

        JLabel lblWoonplaats = new JLabel("Woonplaats:");
        lblWoonplaats.setBounds(320, verticalPosition + products.size() * productOffset + (formOffset * 4), 100, 20);
        lblWoonplaats.setFont(WinkelApplication.FONT_12_BOLD);
        content.add(lblWoonplaats);

        tfWoonplaats = new JTextField();
        tfWoonplaats.setBounds(420, verticalPosition + products.size() * productOffset + (formOffset * 4), 130, 20);
        tfWoonplaats.setFont(WinkelApplication.FONT_12_BOLD);
        tfWoonplaats.setText("");
        if (Session.getGebruiker() != null) {
            tfWoonplaats.setText(Session.getGebruiker().getWoonplaats());
        }

        tfWoonplaats.getDocument().addDocumentListener(new ValidateListener());
        content.add(tfWoonplaats);

        JLabel lblPayMethod = new JLabel("Betaalmethode:");
        lblPayMethod.setBounds(20, verticalPosition + products.size() * productOffset + (formOffset * 5), 100, 20);
        lblPayMethod.setFont(WinkelApplication.FONT_12_BOLD);
        content.add(lblPayMethod);

        cmbPayMethod = new JComboBox(payMethods);
        cmbPayMethod.setBounds(120, verticalPosition + products.size() * productOffset + (formOffset * 5), 250, 20);
        cmbPayMethod.setFont(WinkelApplication.FONT_12_BOLD);
        content.add(cmbPayMethod);

        JLabel lblNote = new JLabel("Opmerking:");
        lblNote.setBounds(20, verticalPosition + products.size() * productOffset + (formOffset * 6), 100, 20);
        lblNote.setFont(WinkelApplication.FONT_12_BOLD);
        content.add(lblNote);

        tfNote = new JTextField();
        tfNote.setBounds(120, verticalPosition + products.size() * productOffset + (formOffset * 6), 250, 80);
        tfNote.setFont(WinkelApplication.FONT_12_BOLD);
        content.add(tfNote);

        JButton btnSend = new JButton("Verzend bestelling");
        btnSend.setBounds(120, verticalPosition + products.size() * productOffset + (formOffset * 9), 150, 20);
        btnSend.setFont(WinkelApplication.FONT_12_BOLD);
        btnSend.addActionListener(this);
        content.add(btnSend);

    }

    public static void openURL(String urlText) {
        if (Desktop.isDesktopSupported()) {
            URI uri = URI.create(urlText);
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                System.out.println("Foutmelding: Browser kan betaalpagina niet openen.");
            }
        }
    }

    /**
     * A listener for textfields. Use the formats to test if the user is filling
     * in correct information. If the underlaying document, which is automaticly
     * generated, changes, this listener will be called.
     *
     * The source will be checked, and the right validator is called.
     */
    private class ValidateListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            if (e.getDocument().equals(tfPostcode.getDocument())) {
                validatePostcode(tfPostcode.getText());

            } else if (e.getDocument().equals(tfNaam.getDocument())) {
                validateNaam(tfNaam.getText());

            } else if (e.getDocument().equals(tfAddress.getDocument())) {
                validateAdres(tfAddress.getText());

            } else if (e.getDocument().equals(tfWoonplaats.getDocument())) {
                validateWoonplaats(tfWoonplaats.getText());

            }
        }

        public void removeUpdate(DocumentEvent e) {
            if (e.getDocument().equals(tfPostcode.getDocument())) {
                validatePostcode(tfPostcode.getText());

            } else if (e.getDocument().equals(tfNaam.getDocument())) {
                validateNaam(tfNaam.getText());

            } else if (e.getDocument().equals(tfAddress.getDocument())) {
                validateAdres(tfAddress.getText());

            } else if (e.getDocument().equals(tfWoonplaats.getDocument())) {
                validateWoonplaats(tfWoonplaats.getText());

            }
        }

        public void changedUpdate(DocumentEvent e) {
            if (e.getDocument().equals(tfPostcode.getDocument())) {
                validatePostcode(tfPostcode.getText());

            } else if (e.getDocument().equals(tfNaam.getDocument())) {
                validateNaam(tfNaam.getText());

            } else if (e.getDocument().equals(tfAddress.getDocument())) {
                validateAdres(tfAddress.getText());

            } else if (e.getDocument().equals(tfWoonplaats.getDocument())) {
                validateWoonplaats(tfWoonplaats.getText());

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        /**
         * 1. Velden worden gecontroleerd d.m.v. regex 2. Queries zullen de
         * order in de database plaatsen. 3. De statistieken zullen gëupdate
         * worden 3. Gebruiker zal (afhankelijk van zijn betaalmethode) naar een
         * pagina worden gestuurd, zie hieronder: 3.1 iDeal - Gebruiker wordt
         * naar een iDeal betaalpagina gestuurd 3.2 Contante betaling -
         * Gebruiker krijgt een code waarmee hij contant kan betalen bij een
         * medewerker en vervolgens zijn product in ontvangst kan nemen.
         */
        if (validateNaam(tfNaam.getText()) && validateAdres(tfAddress.getText()) && validatePostcode(tfPostcode.getText())
                && validateWoonplaats(tfWoonplaats.getText())) {
            // Unieke code wordt meegegeven
            Random rndNumbers = new Random(30);
            int rndNumber = rndNumbers.nextInt(Integer.MAX_VALUE) + 1;
            String betaalCode = Integer.toString(rndNumber);

            String naam = tfNaam.getText();
            String adres = tfAddress.getText();
            String opmerking = tfNote.getText();
            String postcode = tfPostcode.getText();
            String woonplaats = tfWoonplaats.getText();
            String betaalmethode = (String) cmbPayMethod.getSelectedItem();
            WinkelApplication.getQueryManager().setOrder(WinkelApplication.getBasket(),
                    naam, adres, postcode, woonplaats, opmerking, betaalmethode, betaalCode);

                       
            for (int i = 0; i < WinkelApplication.getBasket().size(); i++) {
                if (Session.getGebruiker() != null) {
                    Statistiek s = new Statistiek(Session.getGebruiker(), WinkelApplication.getBasket().getProducts().get(i));
                    main.WinkelApplication.getQueryManager().updateStatistieken(s);
                } else{
                    Statistiek s = new Statistiek();
                    s.setProduct_id(WinkelApplication.getBasket().getProducts().get(i).getProduct_id());
                    s.setTotaal_verkocht(1);
                    main.WinkelApplication.getQueryManager().updateStatistieken(s);
                }
            }

            WinkelApplication.getBasket().empty();
            if ("iDeal".equals(betaalmethode)) {
                // Betaling verloopt via iDeal
                openURL("https://bankieren.rabobank.nl/rib/");
                WinkelApplication.getInstance().showPanel(new OrderSend());
            } else {
                // Betaling verloopt via contante betaling, unieke code wordt meegegeven aan het volgende scherm
                WinkelApplication.getInstance().showPanel(new OrderSendContantBetaald(betaalCode));
            }

        } else {
            this.lblFormTitle.setText("Verzendgegevens -- Niet alle gegevens zijn correct ingevuld");
            this.lblFormTitle.setForeground(Color.red);
            this.lblFormTitle.setSize(400, 20);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // the user clicked on the title, go back to the first screen
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Intentionally left blank.
    }
}
