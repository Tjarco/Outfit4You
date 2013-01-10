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

import misc.timestamp;
import model.Paginatie;

import model.Statistiek;

public class QueryManager {

    private final Dbmanager dbmanager;
    private final String tbl_gebruiker = "gebruiker";
    private final String tbl_product = "product";

    public QueryManager(Dbmanager dbmanager) {
        this.dbmanager = dbmanager;
    }

    /**
     * Vraagt een cattegorienaam op op basis van de categorie Id
     *
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
     *
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
     *
     * @param product
     * @return true als het gelukt is
     */
    public boolean setProduct(Product product) {
        boolean success = false;

        String fields = "categorie_id = " + product.getCategorie_id() + ", "
                + "naam = '" + product.getNaam() + "', "
                + "prijs = '" + product.getPrijs() + "', "
                + "omschrijving = '" + product.getOmschrijving() + "', "
                + "datum_aangemaakt = '" + product.getDatum_aangemaakt() + "', "
                + "datum_gewijzigd = '" + product.getDatum_gewijzigd() + "', "
                + "sku = " + product.getSku() + ", "
                + "omschrijving_kort = '" + product.getOmschrijving_kort() + "', "
                + "voorraad = " + product.getVoorraad() + ", "
                + "afbeelding = '" + product.getAfbeelding() + "', "
                + "thumbnail = '" + product.getThumbnail() + "', "
                + "is_actief = " + product.getIs_actief() + "";

        String sql = "INSERT INTO " + this.tbl_product + " "
                + "SET product_id = " + product.getProduct_id() + ", " + fields + " ON DUPLICATE KEY UPDATE " + fields;

        try {
            ResultSet result = dbmanager.insertQuery(sql);
            success = result.next();
        } catch (SQLException e) {
            System.out.println("connectivity.QueryManager.setOrder() Exception:" + e.getMessage());
        }

        return success;
    }

    /**
     *
     * Geeft de voorraad terug van het product met het gegeven product id
     *
     * @param productId
     * @return
     */
    public int getVoorraad(int productId) {
        int voorraad = 0;
        try {
            String sql = "SELECT `voorraad` FROM `product` WHERE `product_id` = '" + productId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                voorraad = result.getInt("voorraad");
            }
        } catch (Exception e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return voorraad;
    }

    /**
     *
     * Geeft het product terug dat overeen komt met het gegeven product id
     *
     * @param productId
     * @return
     */
    public Product getProduct(int productId) {
        Product product = new Product();
        try {
            String sql = "SELECT * FROM " + this.tbl_product + " WHERE product_id='" + productId + "'";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                product = new Product(
                        result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getDouble("prijs"),
                        result.getString("omschrijving"),
                        result.getString("datum_aangemaakt"),
                        result.getString("datum_gewijzigd"),
                        result.getInt("sku"),
                        result.getString("omschrijving_kort"),
                        result.getInt("voorraad"),
                        result.getString("afbeelding"),
                        result.getString("thumbnail"),
                        (result.getInt("is_actief") == 0) ? false : true);

                /*
                 * product = new Product(result.getInt("product_id"),
                 * result.getInt("categorie_id"), result.getString("naam"),
                 * result.getString("omschrijving"), result.getDouble("prijs"),
                 * result.getInt("voorraad"), //Zet de int in de database om
                 * naar een boolean (result.getInt("is_actief") == 0) ? false :
                 * true ) ;
                 */
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return product;
    }

    /**
     * Een methode om een product op te halen dat overenenkomt met de
     * gespecificeerde waarde in het gespecificeerde veld.
     *
     * @param field
     * @param value
     * @return Het product.
     */
    public Product getProduct(String field, String value) {
        Product product = new Product();
        try {
            String sql = "SELECT * FROM " + this.tbl_product + " WHERE " + field + " LIKE '%" + value + "%'";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                product = new Product(
                        result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getDouble("prijs"),
                        result.getString("omschrijving"),
                        result.getString("datum_aangemaakt"),
                        result.getString("datum_gewijzigd"),
                        result.getInt("sku"),
                        result.getString("omschrijving_kort"),
                        result.getInt("voorraad"),
                        result.getString("afbeelding"),
                        result.getString("thumbnail"),
                        (result.getInt("is_actief") == 0) ? false : true);


            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return product;
    }

    /**
     * Verandert de vooraad van het gegeven product.
     *
     * @param productNaam
     * @param voorraad
     */
    public void UpdateVoorraad(String productNaam, int voorraad) {
        try {
            String sql = "UPDATE product SET voorraad = '" + voorraad + "'"
                    + "WHERE naam = '" + productNaam + "'";
            dbmanager.insertQuery(sql);
        } catch (Exception e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
    }
    /**
     * @author Vernon de Goede < vernon.de.goede@hva.nl >
     * @param productID
     * @param voorraad 
     */
    public void UpdateVoorraadByID(int productID, int voorraad){
        try{
            String sql = "UPDATE product SET voorraad = '" + voorraad + "'"
                    + "WHERE product_id = '"+productID+"'";
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
    public List<Product> getProducts(int categoryId, Paginatie paginatie) {
        List<Product> products = new ArrayList<Product>();
        paginatie.calculateOffset();
        
        try 
        {            
            String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM product WHERE categorie_id='" + categoryId + "' AND is_actief = 1 ORDER BY naam ASC LIMIT " + paginatie.getOffset() + "," + paginatie.getAantalPerPagina() + "";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) 
            {
                products.add(new Product(
                        result.getInt("product_id"),
                        result.getInt("categorie_id"),
                        result.getString("naam"),
                        result.getDouble("prijs"),
                        result.getString("omschrijving"),
                        result.getString("datum_aangemaakt"),
                        result.getString("datum_gewijzigd"),
                        result.getInt("sku"),
                        result.getString("omschrijving_kort"),
                        result.getInt("voorraad"),
                        result.getString("afbeelding"),
                        result.getString("thumbnail"),
                        (result.getInt("is_actief") == 0) ? false : true
                ));
            }
            
            result = dbmanager.doQuery("SELECT FOUND_ROWS()");
            if(result.next())
            {
                paginatie.setAantalProducten(result.getInt(1));
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        
        paginatie.calculatePages();
        
        return products;
    }
    
    

    /**
     *
     * @return Alle producten
     */
    public List<Product> getAllProducts() {
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
                        result.getString("datum_aangemaakt"),
                        result.getString("datum_gewijzigd"),
                        result.getInt("sku"),
                        result.getString("omschrijving_kort"),
                        result.getInt("voorraad"),
                        result.getString("afbeelding"),
                        result.getString("thumbnail"),
                        (result.getInt("is_actief") == 0) ? false : true));

                /*
                 * products.add(new Product(result.getInt("product_id"),
                 * result.getInt("categorie_id"), result.getString("naam"),
                 * result.getString("omschrijving"), result.getDouble("prijs"),
                 * result.getInt("voorraad"), true));
                 * //(result.getInt("is_actief") == 0) ? false : true ));
                 */
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return products;
    }

    /**
     * Voegt een gebruiker toe aan de database
     *
     * @param gebruiker
     * @return True als het geslaagd is.
     */
    public boolean setGebruiker(Gebruiker gebruiker) {
        boolean success = false;

        String fields = "datum_aangemaakt = '" + gebruiker.getDatum_aangemaakt() + "', "
                + "datum_gewijzigd = '" + gebruiker.getDatum_gewijzigd() + "', "
                + "datum_laatst_ingelogd = '" + gebruiker.getDatum_laatst_ingelogd() + "', "
                //+       "Telefoonnummer = '" + gebruiker.Telefoonnummer() + "', "  
                + "wachtwoord = '" + gebruiker.getWachtwoord() + "', "
                + "email = '" + gebruiker.getEmail() + "', "
                + "voornaam = '" + gebruiker.getVoornaam() + "', "
                + "tussenvoegsel = '" + gebruiker.getTussenvoegsel() + "', "
                + "achternaam = '" + gebruiker.getAchternaam() + "', "
                + "straatnaam = '" + gebruiker.getStraatnaam() + "', "
                + "huisnummer = " + gebruiker.getHuisnummer() + ", "
                + "postcode = '" + gebruiker.getPostcode() + "', "
                + "woonplaats = '" + gebruiker.getWoonplaats() + "', "
                + "medewerker = " + gebruiker.isMedewerker() + ", "
                + "manager = " + gebruiker.isManager() + ", "
                + "actief = " + gebruiker.isActief() + ", "
                + "geboortedatum = '" + gebruiker.getGeboortedatum() + "'";

        String sql = "INSERT INTO " + this.tbl_gebruiker + " "
                + "SET id = " + gebruiker.getId() + ", " + fields + " ON DUPLICATE KEY UPDATE " + fields;

        try {
            ResultSet result = dbmanager.insertQuery(sql);
            success = result.next();
        } catch (SQLException e) {
            System.out.println("connectivity.QueryManager.setOrder() Exception:" + e.getMessage());
        }

        return success;
    }

    /**
     * Vraag de gebruiker op die hoort bij het meegegeven klant id
     *
     * @param klantId
     * @return Gebruiker
     */
    public Gebruiker getGebruiker(int klantId) {
        Gebruiker gebruiker = new Gebruiker();

        try {
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

            if (result.next()) {
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
                        result.getBoolean("actief"));
            }
        } catch (SQLException e) {
            System.out.println(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }

        return gebruiker;
    }

    /**
     * Geeft een lijst van alle gebruikers
     *
     * @return lijst gebruikers
     */
    public List<Gebruiker> getGebruikersList() {
        List<Gebruiker> gebruikers = new ArrayList<Gebruiker>();

        try {
            String sql = "SELECT * FROM gebruiker WHERE `actief` = 1";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
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
                        result.getBoolean("actief")));
            }
        } catch (SQLException e) {
            System.err.print(Dbmanager.SQL_EXCEPTION + e.getMessage());
        }
        return gebruikers;
    }

    /**
     * Plaatst een order. Krijgt als parameters de bestelling(en) en de
     * klantgegevens.
     *
     * @param basket
     * @param naam
     * @param adres
     * @param postcode
     * @param woonplaats
     * @param opmerking
     * @param betaalmethode
     */

   

    public void setOrder(model.Basket basket, String naam, String adres, String postcode, String woonplaats, String opmerking, String betaalmethode, String betaalCode) 
    {

        timestamp timestamp = new timestamp();
        long currentTimestamp = timestamp.getTimestamp();
        String SQL_order = "INSERT INTO `order` (`order_id`, `naam`, `adres`, `postcode`, `woonplaats`, `notes`, `betaalmethode`, `datum`, `status`, `datum_gewijzigd`, `code`)"
                + " VALUES(NULL, '" + naam + "', '" + adres + "', '" + postcode + "', '"

                + woonplaats + "', '" + opmerking + "', '" + betaalmethode + "', '" + currentTimestamp + "', '', '0')"

                + woonplaats + "', '" + opmerking + "', '" + betaalmethode + "', '" + currentTimestamp + "', '', '0', '" + betaalCode + "')";
        

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
            int product_id = product.getProduct_id();
            int aantal = basket.getProductAmount(product);
            String SQL_orderProduct = "INSERT INTO orderregel (product_id,order_id,aantal) VALUES (" + product_id + "," + order_id + "," + aantal + ")";
            dbmanager.insertQuery(SQL_orderProduct);
        }
    }

    /**
     * Checkt of de inloggevens correct zijn.
     *
     * @param email
     * @param wachtwoord
     * @return true als de gegevens kloppen.
     */
    public boolean isValidLogin(String email, String wachtwoord) {
        String sql = "SELECT * FROM gebruiker WHERE `email` = '" + email + "'";
        ResultSet result = dbmanager.doQuery(sql);
        String pass = "";
        try {
            while (result.next()) {
                pass = result.getString("wachtwoord");
            }
        } catch (SQLException ex) {
            System.out.println("connectivity.QueryManager.isValidInlog() Exception:" + ex.getMessage());

        }
        if (pass.equals(wachtwoord)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @param email
     * @return De gebruiker id die hoort bij het email adres
     */
    public int getGebruikerId(String email) {
        String sql = "SELECT * FROM gebruiker WHERE `email` = '" + email + "'";
        ResultSet result = dbmanager.doQuery(sql);
        int gebruikerId = 0;
        try {
            while (result.next()) {
                gebruikerId = result.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println("connectivity.QueryManager.getGebruikerId() Exception:" + ex.getMessage());
        }
        return gebruikerId;
    }

    public List<Statistiek> getStatistieken() {
        List<Statistiek> statistieken = new ArrayList<Statistiek>();

        String sql = "SELECT * FROM statistieken";

        try {
            ResultSet r = dbmanager.doQuery(sql);

            while (r.next()) {
                statistieken.add(new Statistiek(
                        r.getInt("id"),
                        r.getInt("product_id"),
                        r.getInt("jonger_dan_15"),
                        r.getInt("tussen_15_20"),
                        r.getInt("tussen_20_25"),
                        r.getInt("tussen_25_30"),
                        r.getInt("tussen_30_50"),
                        r.getInt("tussen_50_65"),
                        r.getInt("ouder_dan_65"),
                        r.getInt("totaal_verkocht")));
            }
        } catch (SQLException ex) {
            System.out.println(Dbmanager.SQL_EXCEPTION + ex.getMessage());
        }

        return statistieken;
    }

    public void updateStatistieken(Statistiek statistiek) {

        try {
            String sql = "UPDATE statistieken SET "
                    + "product_id = '" + statistiek.getProduct_id() + "' ,"
                    + "jonger_dan_15 = (jonger_dan_15 + " + statistiek.getJonger_dan_15() + "),"
                    + "tussen_15_20 = (tussen_15_20 + " + statistiek.getTussen_15_20() + "), "
                    + "tussen_20_25 = (tussen_20_25 + " + statistiek.getTussen_20_25() + "), "
                    + "tussen_25_30 = (tussen_25_30 + " + statistiek.getTussen_25_30() + "), "
                    + "tussen_30_50 = (tussen_30_50 + " + statistiek.getTussen_30_50() + "), "
                    + "tussen_50_65 = (tussen_50_65 + " + statistiek.getTussen_50_65() + "), "
                    + "ouder_dan_65 = (ouder_dan_65 + " + statistiek.getOuder_dan_65() + "), "
                    + "totaal_verkocht = (totaal_verkocht + " + statistiek.getTotaal_verkocht() + ")"
                    + "WHERE product_id = '" + statistiek.getProduct_id() + "'";
            dbmanager.insertQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insertStatistiek(Statistiek statistiek) {
        boolean gelukt = false;

        try {
            String sql = "INSERT INTO statistieken VALUES ("
                    + "'" + statistiek.getId() + "',"
                    + "'" + statistiek.getProduct_id() + "',"
                    + "'" + statistiek.getJonger_dan_15() + "',"
                    + "'" + statistiek.getTussen_15_20() + "',"
                    + "'" + statistiek.getTussen_20_25() + "',"
                    + "'" + statistiek.getTussen_25_30() + "',"
                    + "'" + statistiek.getTussen_30_50() + "',"
                    + "'" + statistiek.getTussen_50_65() + "',"
                    + "'" + statistiek.getOuder_dan_65() + "',"
                    + "'" + statistiek.getTotaal_verkocht() + "')";

            ResultSet res = dbmanager.insertQuery(sql);
            gelukt = res.next();
        } catch (SQLException ex) {
            System.out.print(Dbmanager.JDBC_EXCEPTION + ex.getMessage());
        }

        return gelukt;
    }

    public int getAantalVerkocht(int product_id) {
        String sql = "SELECT totaal_verkocht FROM statistieken WHERE `product_id` = '" + product_id + "'";
        ResultSet result = dbmanager.doQuery(sql);
        int aantalVerkocht = 0;
        try {
            while (result.next()) {
                aantalVerkocht = result.getInt("totaal_verkocht");
            }
        } catch (SQLException ex) {
            System.out.println(Dbmanager.SQL_EXCEPTION + ex.getMessage());
        }
        return aantalVerkocht;
    }

    public Statistiek getStatistiek(int product_id) {
        Statistiek s = new Statistiek();

        String sql = "SELECT * FROM statistieken WHERE `product_id` = '" + product_id + "'";
        ResultSet result = dbmanager.doQuery(sql);

        try {
            while (result.next()) {
                s.setId(result.getInt("id"));
                s.setProduct_id(result.getInt("product_id"));
                s.setJonger_dan_15(result.getInt("jonger_dan_15"));
                s.setTussen_15_20(result.getInt("tussen_15_20"));
                s.setTussen_20_25(result.getInt("tussen_20_25"));
                s.setTussen_25_30(result.getInt("tussen_25_30"));
                s.setTussen_30_50(result.getInt("tussen_30_50"));
                s.setTussen_50_65(result.getInt("tussen_50_65"));
                s.setOuder_dan_65(result.getInt("ouder_dan_65"));
                s.setTotaal_verkocht(result.getInt("totaal_verkocht"));
            }
        } catch (SQLException ex) {
            System.out.println(Dbmanager.SQL_EXCEPTION + ex.getMessage());
        }

        return s;
    }
}
