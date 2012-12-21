package model;

public class Product {

    private int productId;
    private int categorieId;
    private String name;
    private String description;
    private double price;

    public Product() {
        this(-1, -1, "", "", 0.0);
    }

    public Product(int product_id, int categorie_id, String name, String description, double price) {
        this.productId = product_id;
        this.categorieId = categorie_id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the categorieId
     */
    public int getCategorieId() {
        return categorieId;
    }

    /**
     * @param categorieId the categorieId to set
     */
    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        boolean value;
        if (obj instanceof Product) {
            value = this.productId == ((Product)obj).productId;
        } else {
            value = super.equals(obj);
        }
        return value;
    }

    @Override
    public int hashCode() {
        return 13 * 3 + this.productId;
    }
}
