package view;

import main.WinkelApplication;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Product;

public class ProductDetails extends JPanel implements MouseListener, ActionListener {

    private final int product_id;
    private final JButton jbTerug;

    public ProductDetails(int product_id) {
        super();
        /** Set name, layout and Background */
        this.setLayout(null);
        this.product_id = product_id;
        initComponents();
        
        jbTerug = new JButton("Terug");
        jbTerug.addActionListener(new ButtonListener());
        jbTerug.setBounds(570, 280, 150, 40);
        add(jbTerug);
    }
    
        private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            main.WinkelApplication.getInstance().showPanel(new CategoryList());
        }
    
    }

    private void initComponents() {
        addInlog();
        addTitle();
        addSubTitle();
        addProduct();
        addBasket();
    }
    
   private void addInlog(){
        InloggenDisplay logIn = new InloggenDisplay();
        this.add(logIn);
    }

    private void addTitle() {
        JLabel lblTitle1 = new JLabel();
        lblTitle1.setText("Winkelapplicatie");
        lblTitle1.setBounds(20, 20, 150, 20);
        lblTitle1.setFont(WinkelApplication.FONT_18_BOLD);
        lblTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTitle1.addMouseListener(this);
        add(lblTitle1);
    }

    private void addSubTitle() {
        JLabel lblTitle2 = new JLabel();
        lblTitle2.setText("-");
        lblTitle2.setBounds(170, 20, 20, 20);
        lblTitle2.setFont(WinkelApplication.FONT_18_BOLD);
        add(lblTitle2);
        JLabel lblTitle3 = new JLabel();
        lblTitle3.setText("Bekijk product");
        lblTitle3.setBounds(190, 20, 500, 20);
        lblTitle3.setFont(WinkelApplication.FONT_18_BOLD);
        add(lblTitle3);
    }

    private void addProduct() {
        Product currentProduct = WinkelApplication.getQueryManager().getProduct(product_id);

        JLabel lblProductName = new JLabel();
        lblProductName.setText(currentProduct.getName());
        lblProductName.setBounds(20, 60, 500, 20);
        lblProductName.setFont(WinkelApplication.FONT_18_BOLD);
        add(lblProductName);

        JLabel lblDescription = new JLabel();
        lblDescription.setText(currentProduct.getDescription());
        lblDescription.setBounds(20, 80, 500, 20);
        lblDescription.setFont(WinkelApplication.FONT_12_PLAIN);
        add(lblDescription);

        JLabel lblProductPrice = new JLabel();
        lblProductPrice.setText("Price: " + WinkelApplication.CURRENCY + currentProduct.getPrice());
        lblProductPrice.setBounds(20, 100, 500, 20);
        lblProductPrice.setFont(WinkelApplication.FONT_12_PLAIN);
        add(lblProductPrice);

        JButton btnOrder = new JButton("Toevoegen aan Winkelmand");
        btnOrder.setBounds(20, 140, 230, 20);
        btnOrder.setFont(WinkelApplication.FONT_14_BOLD);
        btnOrder.addActionListener(this);
        add(btnOrder);
    }

    private void addBasket() {
        BasketDisplay basket = new BasketDisplay();
        add(basket);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Product currentProduct = WinkelApplication.getQueryManager().getProduct(product_id);
        WinkelApplication.getBasket().addProduct(currentProduct);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // the user clicked on the title, go back to the first screen
        WinkelApplication.getInstance().showPanel(new view.CategoryList());
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

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawLine(20, 45, 540, 45);
    }
}
