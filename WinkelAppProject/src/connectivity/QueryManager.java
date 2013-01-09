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
    /**
     * Vraagt een cattegorienaam op op basis van de categorie Id
     * @param categoryId
     * @return <String> categorie naam
     *
     */
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
    /**
     * Geeft alle cattegorieën uit de database terug
     * @return Categoriën. 
     */
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
    
    /**
     * Voegt een product toe.
     * @param product
     * @return true als het gelukt is
     */
    public boolean setProduct(Product product)
    {
        boolean success = false;
        
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
                +       "is_actief = " + product.getIs_actief() + "";
        
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
    /**
     * 
     * Geeft de voorraad terug van het product met het gegeven product id
     * @param productId
     * @return 
     */
    
    public int getVoorraad(int productId) {
        int voorraad = 0;
        try{
            String sql = "SELECT `voorraad` FROM `product` WHERE `product_id` = '" + productId +"'";
            ResultSet result = dbmanager.doQuery(sql);      
            if (result.next()) {
                voorraad = result.getInt("voorraad");
            }
        }catch(Exception e){
           System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage()); 
        }
        return voorraad;
    }
    
    /**
     * 
     * Geeft het product terug dat overeen komt met het gegeven product id
     * @param productId
     * @return 
     */
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
    
    /**
     * Verandert de vooraad van het gegeven product.
     * @param productNaam
     * @param voorraad 
     */
    public void UpdateVoorraad(String productNaam, int voorraad){
        try{
            String sql = "UPDATE product SET voorraad = '" + voorraad + "'"
                    + "WHERE naam = '"+productNaam+"'";
            dbmanager.insertQuery(sql);            
        }catch(Exception e){
           System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage()); 
        }
    }

    /**
     * Geeft alle producten die horen bij een bepaalde categorie.
     * @param categoryId
     * @return een lijst met categorieën
     */
    public List<Product> getProducts(int categoryId) {
        List<Product> products = new ArrayList<Product>();
        try {
            String sql = "SELECT * FROM product WHERE categorie_id='" + categoryId + "' AND is_actief = 1 ORDER BY naam ASC";
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
    
    /**
     *  
     * @return Alle producten
     */
    public List<Product> getAllProducts(){
               List<Product> products = new ArrayList<Product>();
        try {
            String sql = "SELECT * FROM product WHERE is_actief = 1 ORDER BY naam ASC";
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
     * Voegt een gebruiker toe aan de database
     * @param gebruiker
     * @return True als het geslaagd is.
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
    
    /**
     * Vraag de gebruiker op die hoort bij het meegegeven klant id
     * @param klantId
     * @return Gebruiker
     */
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
    
    /**
     * Geeft een lijst van alle gebruikers
     * @return lijst gebruikers
     */
    public List<Gebruiker> getGebruikersList(){
        List<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
        
        try{
            String sql = "SELECT * FROM gebruiker WHERE `actief` = 1";
            ResultSet result = dbmanager.doQuery(sql);
            while(result.next()){
                gebruikers.add(new Gebruiker(
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
        return gebruikers;
    }
    /**
     * Plaatst een order. Krijgt als parameters de bestelling(en) en de klantgegevens.
     * @param basket
     * @param naam
     * @param adres
     * @param postcode
     * @param woonplaats
     * @param opmerking
     * @param betaalmethode 
     */
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
    
    /**
     * Checkt of de inloggevens correct zijn.
     * @param email
     * @param wachtwoord
     * @return true als de gegevens kloppen.
     */
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
    
    /**
     * 
     * @param email
     * @return De gebruiker id die hoort bij het email adres
     */
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
