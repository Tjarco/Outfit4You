package model;

/**
 *
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * 
 * Zorgt ervoor dat de OrderRegels opgeslagen kunnen worden en later gemakkelijk hieruit gehaald kunnen worden.
 */
public class OrderRegel {
    private int product_id;
    private int order_id;
    private int aantal;

    public OrderRegel(int product_id, int order_id, int aantal) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.aantal = aantal;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getAantal() {
        return aantal;
    }
    
    
    
    
}
