package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
/**
 * 
 * @author Administrator
 * @version 1
 * 
 * Een winkelmand die de producten bevat die de gebruiker toegevoegd heeft.
 */
public class Basket extends Observable {

    private final Map<Product, Integer> products;

    public Basket() {
        super();
        products = new LinkedHashMap<Product, Integer>();
    }

    /**
     * Voegt een product toe aan de winkelmand
     * @param product 
     */
    public void addProduct(Product product) {
        // check if product is allready added to the basket
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
        setChanged();
        notifyObservers();
    }
        
    /**
     * Verwijdert een specifiek product uit de winkelmand
     * @param product 
     */
    public void removeProduct (Product product, int value) {
        products.put(product, value);
        setChanged();
        notifyObservers();
    }

    /**
     * Leeg de winkelmand
     */
    public void empty() {
        products.clear();
        setChanged();
        notifyObservers();
    }
    
    /**
     * 
     * @return Alle producten in een List
     */
    public List<Product> getProducts() {
        List<Product> list = new LinkedList<Product>(products.keySet());
        return Collections.unmodifiableList(list);
    }
    
    /**
     * 
     * @param product
     * @return het product uit de map die overeen komt met het product uit de parameter. return null als het product
     * niet in de map zit
     */
    public int getProductAmount(Product product) {
        return products.get(product);
    }
    
    /**
     * 
     * @return De hoeveelheid producten in de winkelmand
     */
    public int size() {
        return products.size();
    }

    /**
     * 
     * @return De totale prijs van alle producten.
     */
    public double getTotalCosts() {
        double total = 0.0;
        for (Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrijs() * entry.getValue();
        }
        return total;
    }
}
