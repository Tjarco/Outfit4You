package model;

public class Product {

    private int product_id;
    private int categorie_id;
    private String naam;
    private double prijs;
    private String omschrijving;
    private String datum_aangemaakt;
    private String datum_gewijzigd;
    private int sku;
    private String omschrijving_kort;
    private int voorraad;
    private String afbeelding;
    private String thumbnail;
    private boolean is_actief;
    
    public Product()
    {
        
    }

    public Product(int product_id, int categorie_id, String naam, double prijs, String omschrijving, String datum_aangemaakt, String datum_gewijzigd, int sku, String omschrijving_kort, int voorraad, String afbeelding, String thumbnail, boolean is_actief) {
        this.product_id = product_id;
        this.categorie_id = categorie_id;
        this.naam = naam;
        this.prijs = prijs;
        this.omschrijving = omschrijving;
        this.datum_aangemaakt = datum_aangemaakt;
        this.datum_gewijzigd = datum_gewijzigd;
        this.sku = sku;
        this.omschrijving_kort = omschrijving_kort;
        this.voorraad = voorraad;
        this.afbeelding = afbeelding;
        this.thumbnail = thumbnail;
        this.is_actief = is_actief;
    }


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getDatum_aangemaakt() {
        return datum_aangemaakt;
    }

    public void setDatum_aangemaakt(String datum_aangemaakt) {
        this.datum_aangemaakt = datum_aangemaakt;
    }

    public String getDatum_gewijzigd() {
        return datum_gewijzigd;
    }

    public void setDatum_gewijzigd(String datum_gewijzigd) {
        this.datum_gewijzigd = datum_gewijzigd;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getOmschrijving_kort() {
        return omschrijving_kort;
    }

    public void setOmschrijving_kort(String omschrijving_kort) {
        this.omschrijving_kort = omschrijving_kort;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }

    public String getAfbeelding() {
        return afbeelding;
    }

    public void setAfbeelding(String afbeelding) {
        this.afbeelding = afbeelding;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean getIs_actief() {
        return is_actief;
    }

    public void setIs_actief(boolean is_actief) {
        this.is_actief = is_actief;
    }
    
    @Override
    public String toString() {
        return naam;
    }

    @Override
    public boolean equals(Object obj) {
        boolean value;
        if (obj instanceof Product) {
            value = this.product_id == ((Product) obj).product_id;
        } else {
            value = super.equals(obj);
        }
        return value;
    }

    @Override
    public int hashCode() {
        return 13 * 3 + this.product_id;
    }
}
