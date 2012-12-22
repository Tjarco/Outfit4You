package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Gebruiker;
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
                        result.getDouble("prijs"),
                        result.getInt("vooraad"),
                        //Zet de int in de database om naar een boolean
                        (result.getInt("is_actief") == 0) ? false : true ) ;
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
                        result.getDouble("prijs"),
                        result.getInt("vooraad"),
                        (result.getInt("is_actief") == 0) ? false : true));
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
                        result.getDouble("prijs"),
                        result.getInt("voorraad"),
                        true)); //(result.getInt("is_actief") == 0) ? false : true ));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return products;
    }
    
    public Gebruiker getGebruiker(int klantId)
    {
        Gebruiker gebruiker = new Gebruiker();
        
        try
        {
            String sql = "SELECT "
            + "             `id`, "
            + "             `datum_aangemaakt`, "
            + "             `datum_gewijzigd`, "
            + "             `datum_laatst_ingelogd`, "
            + "             `email`, "
            + "             `voornaam`, "
            + "             `tussenvoegsel`, "
            + "             `achternaam`, "
            + "             `straatnaam`, "
            + "             `huisnummer`, "
            + "             `postcode`, "
            + "             `woonplaats`, "
            + "             `medewerker`, "
            + "             `manager`, "
            + "             `actief`, "
            + "             `geboortedatum`"
            + "         FROM gebruiker "
            + "             WHERE id = " + klantId + "";
            
            ResultSet result = dbmanager.doQuery(sql);
            
            if(result.next())
            {
                gebruiker = new Gebruiker(
                    result.getInt("id"),
                    result.getInt("huisnummer"),
                    result.getString("datum_aangemaakt"),
                    result.getString("datum_gewijzigd"),
                    result.getString("datum_laatst_ingelogd"),
                    "", //wachtwoord veld
                    result.getString("email"),
                    result.getString("voornaam"),
                    result.getString("tussenvoegsel"),
                    result.getString("achternaam"),
                    result.getString("straatnaam"),
                    result.getString("postcode"),
                    result.getString("woonplaats"),
                    result.getString("geboortedatum"),
                    result.getBoolean("medewerker"),
                    result.getBoolean("manager"),
                    result.getBoolean("actief")
                );
            }
        }
        catch(SQLException e)
        {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        
        return gebruiker;
    }
    
    public List<Gebruiker> getKlantenList(){
        List<Gebruiker> klanten = new ArrayList<Gebruiker>();
        
        try{
            String sql = "SELECT * FROM gebruiker WHERE `actief` = 1";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()){
                klanten.add(new Gebruiker(
                    result.getInt("id"),
                    result.getInt("huisnummer"),
                    result.getString("datum_aangemaakt"),
                    result.getString("datum_gewijzigd"),
                    result.getString("datum_laatst_ingelogd"),
                    result.getString("wachtwoord"),
                    result.getString("email"),
                    result.getString("voornaam"),
                    result.getString("tussenvoegsel"),
                    result.getString("achternaam"),
                    result.getString("straatnaam"),
                    result.getString("postcode"),
                    result.getString("woonplaats"),
                    result.getString("geboortedatum"),
                    result.getBoolean("medewerker"),
                    result.getBoolean("manager"),
                    result.getBoolean("actief")
                ));
            }
        }catch(SQLException e){
            System.err.print(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return klanten;
    }
    
    public void addKlant(Gebruiker klant){
//        String sql = "INSERT INTO  `klant` (klant_id, naam, adres, postcode, woonplaats) "
//                + " VALUES('"+ klant.getKlantId() + "', '"+ klant.getVoornaam() + "', '"
//                + klant.getAdres() + "', '" + klant.getPostcode() + "', '" + klant.getWoonplaats() + "')";
//             
//        dbmanager.insertQuery(sql);
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
