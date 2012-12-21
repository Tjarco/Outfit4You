package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Klant;
import model.Product;

public class QueryManager {

    private final Dbmanager dbmanager;

    public QueryManager(Dbmanager dbmanager) {
        this.dbmanager = dbmanager;
    }

    public String getCategoryName(int categoryId) {
        String categoryName = "";
        try {
            String sql = "SELECT naam FROM categorie WHERE categorie_id='" + categoryId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                categoryName = result.getString("naam");
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return categoryName;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            String sql = "SELECT * FROM categorie ORDER BY naam ASC";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                Category category = new Category(result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return categories;
    }

    public Product getProduct(int productId) {
        Product product = new Product();
        try {
            String sql = "SELECT * FROM product WHERE product_id='" + productId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                product = new Product(result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getDouble("prijs"));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return product;
    }

    public List<Product> getProducts(int categoryId) {
        List<Product> products = new ArrayList<Product>();
        try {
            String sql = "SELECT * FROM product WHERE categorie_id='" + categoryId + "' ORDER BY naam ASC";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                products.add(new Product(result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getDouble("prijs")));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return products;
    }
    
    public List<Product> getAllProducts(){
               List<Product> products = new ArrayList<Product>();
        try {
            String sql = "SELECT * FROM product ORDER BY naam ASC";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                products.add(new Product(result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getDouble("prijs")));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return products;
    }
    
    public Klant getKlant(int klantId){
        Klant klant = new Klant();
        
        try{
            String sql = "SELECT * FROM klant WHERE klant_id = '" + klantId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            
            if(result.next()){
                klant = new Klant(result.getInt("klant_id"),
                      result.getString("naam"),
                      result.getString("adres"),
                      result.getString("postcode"),
                      result.getString("woonplaats"));
               
            }
        }catch(SQLException e){
            System.err.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return klant;
    }
    
    public List<Klant> getKlantenList(){
        List<Klant> klanten = new ArrayList<Klant>();
        
        try{
            String sql = "SELECT * FROM klant";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()){
                klanten.add(new Klant(result.getInt("klant_id"),
                        result.getString("naam"),
                        result.getString("adres"),
                        result.getString("postcode"),
                        result.getString("woonplaats")));
            }
        }catch(SQLException e){
            System.err.print(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return klanten;
    }
    
    public void addKlant(Klant klant){
        String sql = "INSERT INTO  `klant` (klant_id, naam, adres, postcode, woonplaats) "
                + " VALUES('"+ klant.getKlantId() + "', '"+ klant.getNaam() + "', '"
                + klant.getAdres() + "', '" + klant.getPostcode() + "', '" + klant.getWoonplaats() + "')";
             
        dbmanager.insertQuery(sql);
    }
 
    public void setOrder(model.Basket basket, String naam, String adres,
            String postcode, String woonplaats, String opmerking, String betaalmethode) {
        String SQL_order = "INSERT INTO `order` (naam, adres, postcode, woonplaats, notes, betaalmethode, datum)"
                + " VALUES('" + naam + "', '" + adres + "', '" + postcode + "', '"
                + woonplaats + "', '" + opmerking + "', '" + betaalmethode + "', CURDATE() )";
        int order_id = 0;
        try {
            ResultSet result = dbmanager.insertQuery(SQL_order);
            result.next();
            order_id = result.getInt(1);
        } catch (SQLException e) {
            System.out.println("connectivity.QueryManager.setOrder() Exception:" + e.getMessage());
        }
        List<Product> products = basket.getProducts();
        for (Product product : products) {
            int product_id = product.getProductId();
            int aantal = basket.getProductAmount(product);
            String SQL_orderProduct = "INSERT INTO orderregel (product_id,order_id,aantal) VALUES (" + product_id + "," + order_id + "," + aantal + ")";
            dbmanager.insertQuery(SQL_orderProduct);
        }
    }
}
