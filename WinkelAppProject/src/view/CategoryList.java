package view;

import connectivity.QueryManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.WinkelApplication;
import model.Category;
import model.Product;
import model.Session;
import model.Paginatie;

/**
 * @version 1.0
 * @author Administrator
 *
 * @version 2.0
 * @author Tjarco
 *
 * De klasse die een overzicht geeft van de categorieën en producten.
 *
 * @version 2.1
 * @author Bono
 *
 * Alleen terug knop toevoegen als er een medewerker/manager is ingelogd
 *
 * @version 2.2
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 *
 * Wanneer er geen enkel product meer op voorraad is van een bepaald product
 * wordt een melding hiervan gegeven en zal het product niet in de basket
 * verschijnen.
 *
 * @version 2.3
 * @author Bono
 *
 * Paginatie toegevoegd voor producten.
 */
public class CategoryList extends JPanel implements MouseListener {
    
    private JButton jbTerug;
    //Het panel voor de items, d.w.z catetorieën en producten
    private JPanel items;
    //Het panel voor de productdetails
    private JPanel productDetails;
    //Het product dat de 'focus' heeft
    private Product currentProduct;
    //Paginatie
    private Paginatie paginatie = new Paginatie(1);
    
    public CategoryList() {
        super();
        setLayout(new BorderLayout());
        initComponents();
        this.setBackground(Color.white);
        this.setOpaque(true);
    }

    /**
     * The listener For the back Button
     */
    private class BackButtonListener implements MouseListener {
        
        public void mouseClicked(MouseEvent e) {
            WinkelApplication.getInstance().showPanel(new MainMenu());
        }
        
        public void mousePressed(MouseEvent e) {
            // do nothing
        }
        
        public void mouseReleased(MouseEvent e) {
            // do nothing
        }
        
        public void mouseEntered(MouseEvent e) {
            // do nothing
        }
        
        public void mouseExited(MouseEvent e) {
            // do nothing
        }
    }

    /**
     * Bouw het GUI op.
     */
    private void initComponents() {
        System.out.println("initpanels");
        initPanels();
        System.out.println("adding inlog");
        addInlog();
        System.out.println("adding cat items");
        addcategoryItems();
        System.out.println("adding basket");
        addBasket();
        System.out.println("adding back button");
        if ((Session.getGebruiker() != null) && (Session.getGebruiker().isManager() || Session.getGebruiker().isMedewerker())) {
            addBackButton();
        }
    }

    /**
     * Initialiseerd de panels die de producten bevatten.
     */
    private void initPanels() {
        //Maak de items panel aan
        this.items = new JPanel(new BorderLayout());
        add(items, BorderLayout.LINE_START);

        //Maak de productdetails panel aan    
        productDetails = new JPanel(new GridBagLayout());
        productDetails.setBackground(Color.white);
        productDetails.setOpaque(true);
        this.add(productDetails, BorderLayout.CENTER);
    }

    /**
     * Voegt de back button om naar het hoofdmenu te gaan. (Alleen voor de
     * medewerkers)
     *
     */
    private void addBackButton() {
        JPanel down = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //de dimensie van de panel (de breedte is nul, want deze wordt door de borderlayout bepaald)
        down.setPreferredSize(new Dimension(0, 70));
        down.setBackground(WinkelApplication.BACKGROUND);
        down.setOpaque(true);
        
        
        jbTerug = new JButton("Terug");
        jbTerug.setFont(new Font("Calibri", Font.PLAIN, 18));
        jbTerug.setIcon(new ImageIcon(getClass().getResource("/pictures/backButton.png")));
        jbTerug.setContentAreaFilled(false);
        jbTerug.setBorderPainted(false);
        jbTerug.setFocusPainted(false);
        jbTerug.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbTerug.addMouseListener(new BackButtonListener());
        down.add(jbTerug);
        
        add(down, BorderLayout.PAGE_END);
    }

    /**
     * Voegt de categorieën toe aan de lijst
     */
    private void addcategoryItems() {
        QueryManager queryManager = WinkelApplication.getQueryManager();
        List<Category> categories = queryManager.getCategories();
        
        JPanel categorys = new JPanel();
        BoxLayout layoutCat = new BoxLayout(categorys, BoxLayout.Y_AXIS);
        categorys.setLayout(layoutCat);
        
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            
            JPanel pnlCategory = new JPanel();
            BoxLayout layout = new BoxLayout(pnlCategory, BoxLayout.Y_AXIS);
            pnlCategory.setLayout(layout);
            pnlCategory.setBorder(new EmptyBorder(15, 15, 15, 15));
            
            JLabel lblCategorie = new JLabel();
            lblCategorie.setName(String.valueOf(category.getCategoryId()));
            lblCategorie.setText(category.getName());
            lblCategorie.setFont(WinkelApplication.FONT_12_BOLD);
            lblCategorie.addMouseListener(this);
            lblCategorie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            pnlCategory.add(lblCategorie);
            
            JLabel lblDescription = new JLabel();
            lblDescription.setText(category.getDescription());
            lblDescription.setFont(WinkelApplication.FONT_12_PLAIN);
            pnlCategory.add(lblDescription);
            
            categorys.add(pnlCategory);
        }
        
        items.add(categorys, BorderLayout.LINE_START);
        revalidate();
        
    }

    /**
     * Voegt de producten toe die bij een bepaalde categorie horen.
     *
     * @param categoryID
     */
    private void addProducts(int categoryID) {
        List<Product> products = WinkelApplication.getQueryManager().getProducts(categoryID, this.paginatie);
        
        JPanel jpProducts = new JPanel();
        jpProducts.setMinimumSize(new Dimension(250, 0));
        jpProducts.setMaximumSize(new Dimension(250, 0));
        jpProducts.setPreferredSize(new Dimension(250, 0));
        BoxLayout layoutPro = new BoxLayout(jpProducts, BoxLayout.Y_AXIS);
        jpProducts.setLayout(layoutPro);
        
        
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            
            JPanel pnlProduct = new JPanel();
            BoxLayout layout = new BoxLayout(pnlProduct, BoxLayout.Y_AXIS);
            pnlProduct.setLayout(layout);
            pnlProduct.setBorder(new EmptyBorder(15, 15, 15, 15));
            
            
            JLabel lblProduct = new JLabel();
            lblProduct.setName(String.valueOf(product.getProduct_id()));
            lblProduct.setText(product.getNaam());
            lblProduct.setFont(WinkelApplication.FONT_12_BOLD);
            lblProduct.addMouseListener(new ProductListener());
            lblProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnlProduct.add(lblProduct);
            
            JLabel lblPrice = new JLabel(WinkelApplication.CURRENCY.format(product.getPrijs()));
            lblPrice.setFont(WinkelApplication.FONT_12_PLAIN);
            pnlProduct.add(lblPrice);
            
            jpProducts.add(pnlProduct);
        }
        
        JPanel panelPaginatie = new JPanel();        
        
        if (paginatie.getCurrentPage() > 1) {
            JLabel goLeft = new JLabel("<<");
            
            goLeft.setFont(WinkelApplication.FONT_12_BOLD);
            goLeft.setName("goLeft");
            goLeft.addMouseListener(new paginatieListener(this.paginatie, goLeft, this, categoryID));
            goLeft.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            panelPaginatie.add(goLeft);
        }
        
        if (paginatie.getCurrentPage() < paginatie.getAantalPaginas()) {
            JLabel goRight = new JLabel(">>");
            
            goRight.setFont(WinkelApplication.FONT_12_BOLD);
            goRight.setName("goRight");
            goRight.addMouseListener(new paginatieListener(this.paginatie, goRight, this, categoryID));
            goRight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            panelPaginatie.add(goRight);
        }
        
        jpProducts.add(panelPaginatie);
        
        try {
            items.remove(1);
        } catch (Exception e) {
            // do nothing
        }
        items.add(jpProducts, BorderLayout.LINE_END);
        jpProducts.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
        revalidate();
        
    }

    /**
     * Voegt de winkelmand toe aan het scherm
     */
    private void addBasket() {
        BasketDisplay basket = new BasketDisplay();
        this.add(basket, BorderLayout.EAST);
    }

    /**
     * Voegt de inlog panel toe
     */
    private void addInlog() {
        InloggenDisplay logIn = new InloggenDisplay();
        this.add(logIn, BorderLayout.NORTH);
    }

    /**
     * Zet de details van een bepaald product op het scherm worden getoond
     *
     * @param productId
     */
    private void addProductDetails(int productId) {
        Product product = WinkelApplication.getQueryManager().getProduct(productId);
        
        try {
            productDetails.removeAll();
        } catch (Exception e) {
            //do nothing
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        
        JLabel image = new JLabel();
        gbc.insets = new Insets(20,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        String imagePath = product.getAfbeelding();
        if (imagePath.length() > 10) {
            String im = "/" + imagePath.substring(3, 11) + "/" + imagePath.substring(11, imagePath.length());
            image.setIcon(new ImageIcon(getClass().getResource(im)));
        } else {
            
            image.setIcon(new ImageIcon(getClass().getResource("/pictures/icons/noImage.gif")));
        }
        productDetails.add(image, gbc);
        
        
        StringBuilder omschrijving = new StringBuilder(product.getOmschrijving());
        omschrijving.insert(0, "<html> ");
        omschrijving.append("</html>");
        for (int i = 80; i < omschrijving.length(); i += 80) {
            if (omschrijving.length() - i > 7) {
                omschrijving.insert(i, " <br/>");
            } else {
                omschrijving.append("<br/>");
            }
        }
        String str = omschrijving.toString();
        JLabel description = new JLabel(str);
        description.setFont(new Font("Calibri", Font.ITALIC, 20));
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,0,0);
        productDetails.add(description, gbc);
        
        JButton voegToe = new JButton("Voeg toe aan winkelmand");
        currentProduct = product;
        voegToe.addActionListener(new ToevoegListener());
        gbc.gridy = 2;
        productDetails.add(voegToe, gbc);
        
        revalidate();
        repaint();
        
    }
    
    private class ToevoegListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            if (WinkelApplication.getQueryManager().getVoorraad(currentProduct.getProduct_id()) == 0) {
                JOptionPane.showMessageDialog(null, "Dit product is helaas niet meer op voorraad.");
            } else {
                WinkelApplication.getBasket().addProduct(currentProduct);
            }
        }
    }
    
    private class ProductListener implements MouseListener {
        
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            int productId = Integer.parseInt(label.getName());
            addProductDetails(productId);
        }
        
        public void mousePressed(MouseEvent e) {
        }
        
        public void mouseReleased(MouseEvent e) {
        }
        
        public void mouseEntered(MouseEvent e) {
        }
        
        public void mouseExited(MouseEvent e) {
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        JLabel label = (JLabel) event.getSource();
        int categoryId = Integer.parseInt(label.getName());
        this.addProducts(categoryId);
        this.productDetails.removeAll();
        revalidate();
        repaint();
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
    
    public class paginatieListener implements MouseListener {

        Paginatie paginatie;
        JLabel label;
        CategoryList current;
        int categorieId;
        
        paginatieListener(Paginatie paginatie, JLabel label, CategoryList current, int categorieId) {
            this.paginatie = paginatie;
            this.label = label;
            this.categorieId = categorieId;
            this.current = current;
        }
        
        public void mouseClicked(MouseEvent e) {            
            if (this.label.getName().equals("goRight")) {
                this.paginatie.nextPage();
                this.current.addProducts(this.categorieId);
            } else {
                this.paginatie.previousPage();
                this.current.addProducts(this.categorieId);
            }
        }
        
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
}
