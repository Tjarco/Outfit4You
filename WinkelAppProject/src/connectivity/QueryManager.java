package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Gebruiker;
import model.Product;

public class QueryManager {

    private final Dbmanager dbmanager;
    private final String tbl_gebruiker = "gebruiker";
    private final String tbl_product = "product";
    
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

    public boolean setProduct(Product product)
    {
        boolean success = false;
        int actief = 0;
        if(product.getIs_actief()) actief = 1;
        
        String fields = "categorie_id = " + product.getCategorie_id() + ", "
                +       "naam = '" + product.getNaam() + "', "
                +       "prijs = '" + product.getPrijs() + "', "
                +       "omschrijving = '" + product.getOmschrijving() + "', "
                +       "datum_aangemaakt = '" + product.getDatum_aangemaakt() + "', "
                +       "datum_gewijzigd = '" + product.getDatum_gewijzigd() + "', "
                +       "sku = " + product.getSku() + ", "
                +       "omschrijving_kort = '" + product.getOmschrijving_kort() + "', "
                +       "voorraad = " + product.getVoorraad() + ", "
                +       "afbeelding = '" + product.getAfbeelding() + "', "
                +       "thumbnail = '" + product.getThumbnail() + "', "
                +       "is_actief = " + actief + "";
        
        String sql =    "INSERT INTO " + this.tbl_product + " "
                +           "SET product_id = " + product.getProduct_id() + ", " + fields + " ON DUPLICATE KEY UPDATE " + fields;
        
        try 
        {
            ResultSet result = dbmanager.insertQuery(sql);
            success = result.next();
        } 
        catch (SQLException e) 
        {
            System.out.println("connectivity.QueryManager.setOrder() Exception:" + e.getMessage());
        }
        
        return success;
    }
    
    public Product getProduct(int productId) {
        Product product = new Product();
        try {
            String sql = "SELECT * FROM "+ this.tbl_product +" WHERE product_id='" + productId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                product = new Product(
                        result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getDouble("prijs"),
                        result.getString("omschrijving"),
                        result.getInt("datum_aangemaakt"),
                        result.getInt("datum_gewijzigd"),
                        result.getInt("sku"),
                        result.getString("omschrijving_kort"),
                        result.getInt("voorraad"),
                        result.getString("afbeelding"),
                        result.getString("thumbnail"),
                        (result.getInt("is_actief") == 0) ? false : true
                );
                
                /*product = new Product(result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getDouble("prijs"),
                        result.getInt("voorraad"),
                        //Zet de int in de database om naar een boolean
                        (result.getInt("is_actief") == 0) ? false : true ) ;*/
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return product;
    }
    
    public void UpdateProducts(String productNaam, int vooraad){
        try{
            String sql = "UPDATE product SET voorraad = '" + vooraad + "'"
                    + "WHERE naam = '"+productNaam+"'";
            dbmanager.insertQuery(sql);            
        }catch(Exception e){
           System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage()); 
        }
    }

    public List<Product> getProducts(int categoryId) {
        List<Product> products = new ArrayList<Product>();
        try {
            String sql = "SELECT * FROM product WHERE categorie_id='" + categoryId + "' ORDER BY naam ASC";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                
                products.add(new Product(
                        result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getDouble("prijs"),
                        result.getString("omschrijving"),
                        result.getInt("datum_aangemaakt"),
                        result.getInt("datum_gewijzigd"),
                        result.getInt("sku"),
                        result.getString("omschrijving_kort"),
                        result.getInt("voorraad"),
                        result.getString("afbeelding"),
                        result.getString("thumbnail"),
                        (result.getInt("is_actief") == 0) ? false : true
                ));
                
                /*products.add(new Product(result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getDouble("prijs"),
                        result.getInt("voorraad"),
                        (result.getInt("is_actief") == 0) ? false : true));*/
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
                
                products.add(new Product(
                        result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getDouble("prijs"),
                        result.getString("omschrijving"),
                        result.getInt("datum_aangemaakt"),
                        result.getInt("datum_gewijzigd"),
                        result.getInt("sku"),
                        result.getString("omschrijving_kort"),
                        result.getInt("voorraad"),
                        result.getString("afbeelding"),
                        result.getString("thumbnail"),
                        (result.getInt("is_actief") == 0) ? false : true
                ));
                
               /*products.add(new Product(result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getString("omschrijving"),
                        result.getDouble("prijs"),
                        result.getInt("voorraad"),
                        true)); //(result.getInt("is_actief") == 0) ? false : true ));*/
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return products;
    }
    
    /**
     * 
     * @param gebruiker
     * @return boolean update/insert success
     */
    public boolean setGebruiker(Gebruiker gebruiker)
    {
        boolean success = false;
        
        String fields = "datum_aangemaakt = '" + gebruiker.getDatum_aangemaakt() + "', "
                +       "datum_gewijzigd = '" + gebruiker.getDatum_gewijzigd() + "', "
                +       "datum_laatst_ingelogd = '" + gebruiker.getDatum_laatst_ingelogd() + "', "
              //+       "Telefoonnummer = '" + gebruiker.Telefoonnummer() + "', "  
                +       "wachtwoord = '" + gebruiker.getWachtwoord() + "', "
                +       "email = '" + gebruiker.getEmail() + "', "
                +       "voornaam = '" + gebruiker.getVoornaam() + "', "
                +       "tussenvoegsel = '" + gebruiker.getTussenvoegsel() + "', "
                +       "achternaam = '" + gebruiker.getAchternaam() + "', "
                +       "straatnaam = '" + gebruiker.getStraatnaam() + "', "
                +       "huisnummer = " + gebruiker.getHuisnummer() + ", "
                +       "postcode = '" + gebruiker.getPostcode() + "', "
                +       "woonplaats = '" + gebruiker.getWoonplaats() + "', "
                +       "medewerker = " + gebruiker.isMedewerker() + ", "
                +       "manager = " + gebruiker.isManager() + ", "
                +       "actief = " + gebruiker.isActief() + ", "
                +       "geboortedatum = '" + gebruiker.getGeboortedatum() + "'";
        
        String sql =    "INSERT INTO " + this.tbl_gebruiker + " "
                +           "SET id = " + gebruiker.getId() + ", " + fields + " ON DUPLICATE KEY UPDATE " + fields;
        
        try 
        {
            ResultSet result = dbmanager.insertQuery(sql);
            success = result.next();
        } 
        catch (SQLException e) 
        {
            System.out.println("connectivity.QueryManager.setOrder() Exception:" + e.getMessage());
        }
        
        return success;
    }
    
    public Gebruiker getGebruiker(int klantId)
    {
        Gebruiker gebruiker = new Gebruiker();
        
        try
        {
            String sql = "SELECT "
            + "             `id`, "
            //+ "             `Telefoonnummer`, "
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
                    //result.getString("telefoonnummer"),
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
                    //result.getString("telefoonnummer"),
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
 
    public void setOrder(model.Basket basket, String naam, String adres, String postcode, String woonplaats, String opmerking, String betaalmethode) 
    {
        String SQL_order = "INSERT INTO `order` (naam, adres, postcode, woonplaats, notes, betaalmethode, datum)"
                + " VALUES('" + naam + "', '" + adres + "', '" + postcode + "', '"
                + woonplaats + "', '" + opmerking + "', '" + betaalmethode + "', CURDATE() )";
        
        int order_id = 0;
        
        try 
        {
            ResultSet result = dbmanager.insertQuery(SQL_order);
            result.next();
            order_id = result.getInt(1);
        } 
        catch (SQLException e) 
        {
            System.out.println("connectivity.QueryManager.setOrder() Exception:" + e.getMessage());
        }
        
        List<Product> products = basket.getProducts();
        for (Product product : products) {
            int product_id = product.getProduct_id();
            int aantal = basket.getProductAmount(product);
            String SQL_orderProduct = "INSERT INTO orderregel (product_id,order_id,aantal) VALUES (" + product_id + "," + order_id + "," + aantal + ")";
            dbmanager.insertQuery(SQL_orderProduct);
        }
    }
    /// kijkt of de login correct is
    public boolean isValidLogin(String email, String wachtwoord){
        String sql= "SELECT * FROM gebruiker WHERE `email` = '"+email+"'";
        ResultSet result = dbmanager.doQuery(sql);
        String pass="";
        try {
            while(result.next())
                {
                    pass=result.getString("wachtwoord");
                }
        } catch (SQLException ex) {
            System.out.println("connectivity.QueryManager.isValidInlog() Exception:"+ ex.getMessage());
            
        }
        if (pass.equals(wachtwoord)){ return true;}
        else {return false;}
        
    }
    // returnt de gebruikerId, aangezien je met je mail inlog
    public int getGebruikerId(String email){
        String sql= "SELECT * FROM gebruiker WHERE `email` = '"+email+"'";
        ResultSet result = dbmanager.doQuery(sql);
        int gebruikerId=0;
        try{
            while (result.next())
            {
                gebruikerId=result.getInt("id");
            }
        } catch (SQLException ex){
            System.out.println("connectivity.QueryManager.getGebruikerId() Exception:"+ ex.getMessage());
        }
        return gebruikerId;
    }
    
}
