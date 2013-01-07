package view;

import main.WinkelApplication;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Product;
import model.Session;

/**
 * @version 1.0
 * @author Administrator
 * 
 * @version 2.0
 * @author Tjarco
 * 
 * De klasse om het winkelmandje op het scherm te tonen.
 */
public class BasketDisplay extends JPanel implements ActionListener, Observer {

    public BasketDisplay() {
        super();
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        
        setBackground(Color.gray);
        this.setBorder(new EmptyBorder(15,15,15,15));
        this.setPreferredSize(new Dimension(220,0));
        setOpaque(true);
        
        WinkelApplication.getBasket().addObserver(this);
        initComponents();
    }

    private void initComponents() {
        removeAll();
      
        model.Basket basket = WinkelApplication.getBasket();

        JLabel lblTitle = new JLabel();
        lblTitle.setText("Winkelmand");
        lblTitle.setBounds(5, 5, 150, 20);
        lblTitle.setFont(WinkelApplication.FONT_18_BOLD);
        add(lblTitle);

        JPanel products = new JPanel();
        BoxLayout layoutProducts = new BoxLayout(products, BoxLayout.Y_AXIS);
        products.setLayout(layoutProducts);
        products.setBorder(new EmptyBorder(15,15,15,15));
        products.setOpaque(false);
        
        for (Product product : basket.getProducts()) {
            JLabel lblProduct = new JLabel(basket.getProductAmount(product) +
                    " - " + product.toString());
            lblProduct.setFont(WinkelApplication.FONT_12_PLAIN);
            products.add(lblProduct);

            JLabel lblPrice = new JLabel(WinkelApplication.CURRENCY.format(product.getPrijs()));
            lblPrice.setFont(WinkelApplication.FONT_12_PLAIN);
            products.add(lblPrice);
        }
        
        add(products);

        JLabel lblTotal = new JLabel("Totaal: ");
        lblTotal.setFont(WinkelApplication.FONT_12_BOLD);
        add(lblTotal);

        JLabel lblTotalPrice = new JLabel(WinkelApplication.CURRENCY.format(basket.getTotalCosts()));
        lblTotalPrice.setFont(WinkelApplication.FONT_12_BOLD);
        add(lblTotalPrice);

        JButton btnGoToPay = new JButton("Betalen");
        btnGoToPay.setIcon(new ImageIcon(getClass().getResource("/pictures/icons/Afrekenen.png")));
        btnGoToPay.setContentAreaFilled(false);
        btnGoToPay.setBorderPainted(false);
        btnGoToPay.setFocusPainted(false);
        btnGoToPay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGoToPay.setFont(WinkelApplication.FONT_14_BOLD);
        btnGoToPay.addActionListener(this);

        add(btnGoToPay);
    }

    @Override
    public void update(Observable observable, Object arg) {
        initComponents();
        revalidate();
        repaint();
    }
    

    @Override
    public void actionPerformed(ActionEvent event) {
        // als gebruiker is ingelogd: new payment, anders inloggenRegistreren
        if (Session.getIngelogd()){
            WinkelApplication.getInstance().showPanel(new Payment());
        }
        else {
            WinkelApplication.getInstance().showPanel((new InloggenRegistreren()));
        }
    }
}
