package view;

import main.WinkelApplication;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Product;

public class ProductList extends JPanel implements MouseListener, ActionListener {

    private final int categoryId;
    private final int verticalPosition = 60;
    private final int offset = 40;
    private JButton jbTerug;

    public ProductList(int categoryId) {
        super();
        this.setLayout(null);
        this.categoryId = categoryId;
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
        addProductItems();
        addBasket();
    }
    
        private void addInlog(){
        InloggenDisplay logIn = new InloggenDisplay();
        this.add(logIn);
    }

    private void addProductItems() {
        List<Product> products = WinkelApplication.getQueryManager().getProducts(categoryId);


        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            
            JLabel lblDot = new JLabel("\u2022");
            lblDot.setFont(WinkelApplication.FONT_12_BOLD);
            lblDot.setBounds(20, verticalPosition + i * offset, 10, 20);
            add(lblDot);

            JLabel lblProduct = new JLabel(product.getName());
            lblProduct.setBounds(35, verticalPosition + i * offset, 340, 20);
            lblProduct.setFont(WinkelApplication.FONT_12_BOLD);
            lblProduct.addMouseListener(this);
            lblProduct.setName(String.valueOf(product.getProductId()));
            lblProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(lblProduct);

            JLabel lblDescription = new JLabel(product.getDescription());
            lblDescription.setBounds(35, verticalPosition + i * offset + 15, 340, 20);
            lblDescription.setFont(WinkelApplication.FONT_12_PLAIN);
            this.add(lblDescription);

            JLabel lblPrice = new JLabel(WinkelApplication.CURRENCY + product.getPrice());
            lblPrice.setBounds(380, verticalPosition  + i * offset + 15, 80, 20);
            lblPrice.setFont(WinkelApplication.FONT_12_BOLD);
            add(lblPrice);

            JButton btnOrder = new JButton("Bestel");
            btnOrder.setBounds(450, verticalPosition  + i * offset + 15, 90, 20);
            btnOrder.setFont(WinkelApplication.FONT_12_BOLD);
            btnOrder.setName(String.valueOf(product.getProductId()));
            btnOrder.addActionListener(this);
            add(btnOrder);
        }

    }

    private void addBasket() {
        BasketDisplay basket = new BasketDisplay();
        add(basket);
    }

    private void addTitle() {
        JLabel lblTitle1 = new JLabel();
        lblTitle1.setText("Winkelapplicatie");
        lblTitle1.setBounds(20, 20, 150, 20);
        lblTitle1.setFont(WinkelApplication.FONT_18_BOLD);
        lblTitle1.setName("-1");
        lblTitle1.addMouseListener(this);
        lblTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lblTitle1);

        JLabel lblTitle2 = new JLabel();
        lblTitle2.setText("-");
        lblTitle2.setBounds(170, 20, 20, 20);
        lblTitle2.setFont(WinkelApplication.FONT_18_BOLD);
        add(lblTitle2);
        JLabel lblTitle3 = new JLabel();
        lblTitle3.setText("Producten in " + WinkelApplication.getQueryManager().getCategoryName(categoryId));
        lblTitle3.setBounds(190, 20, 500, 20);
        lblTitle3.setFont(WinkelApplication.FONT_18_BOLD);
        add(lblTitle3);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        JLabel label = (JLabel) event.getSource();
        if (label.getName().equals("-1")) {
            // the user clicked on the title, go back to the first screen
            WinkelApplication.getInstance().showPanel(new view.CategoryList());
        } else {
            WinkelApplication.getInstance().showPanel(new view.ProductDetails(Integer.parseInt(label.getName())));
        }
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
    public void actionPerformed(ActionEvent event) {
        String product_id = ((JButton) event.getSource()).getName();
        Product product = WinkelApplication.getQueryManager().getProduct(Integer.parseInt(product_id));
        WinkelApplication.getBasket().addProduct(product);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawLine(20, 45, 540, 45);
    }
}
