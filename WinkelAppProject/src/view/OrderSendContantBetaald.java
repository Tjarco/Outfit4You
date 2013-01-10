package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.WinkelApplication;

/*
 * @version 1.0
 * @autohor Vernon de Goede < vernon.de.goede@hva.nl >
 * Wanneer een product contant betaald moet worden kan een klant hier een code ontvangen die hij vervolgens aan medewerkers moet laten zien. Hij kan dan zijn product contant betalen en in ontvangst nemen.
 */

public class OrderSendContantBetaald extends MainLayout implements ActionListener, MouseListener {
    private String betaalCode;

    public OrderSendContantBetaald(String betaalCode) {
        this.betaalCode = betaalCode;
        super.AddTitle("De order is verzonden");
        super.addBackButton(new CategoryList());
        initComponents();
    }

    private void initComponents() {
        JPanel container = new JPanel(new GridBagLayout());  
        container.setBackground(Color.white);
        container.setOpaque(true);
        
        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor= GridBagConstraints.LINE_START;
        
        
        
        // display title
        JLabel lblTitle1 = new JLabel();
        lblTitle1.setText("Order Verzonden");
        lblTitle1.setFont(WinkelApplication.FONT_18_BOLD);
        lblTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTitle1.addMouseListener(this);
        gbc.insets = new Insets(60,15,15,15);
        gbc.gridx = 0;
        gbc.gridy= 0;
        content.add(lblTitle1, gbc);


        JLabel lblMessage = new JLabel();
        lblMessage.setText("<html>Uw order is geplaatst. U kunt uw bestelling contant betalen <br />bij onze medewerkers. Gebruik hiervoor de code: <br/><b>" + this.betaalCode + "</b><br /> Bedankt dat u heeft"
                + "gekozen voor Outfit4You.</html> ");
        lblMessage.setFont(new Font("Calibri", Font.ITALIC, 16));
        gbc.insets = new Insets(0,15,60,15);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx=0;
        gbc.gridy=1;
        content.add(lblMessage, gbc);

        JButton btnGoBack = new JButton("Terug naar de winkelapplicatie");
        btnGoBack.setFont(WinkelApplication.FONT_14_BOLD);
        btnGoBack.addActionListener(this);
        btnGoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gbc.gridx =0;
        gbc.gridy = 2;
        content.add(btnGoBack,gbc);

        
        container.add(content);
        this.add(container, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        WinkelApplication.getInstance().showPanel(new view.CategoryList());
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // Intentionally left blank.
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // Intentionally left blank.
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // Intentionally left blank.
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // Intentionally left blank.
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        WinkelApplication.getInstance().showPanel(new view.CategoryList());
    }
}
