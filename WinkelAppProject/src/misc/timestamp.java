package misc;

/**
 * @author Vernon de Goede < vernon.de.goede@hva.nl >
 * @version 1.0
 * 
 * Zorgt ervoor dat de juiste timestamp kan worden opgehaald en in de database kan worden gezet. Gebruik hiervoor getTimestamp().
 */
public class timestamp {
    private long timestamp;
    
    /**
     * Zorgt ervoor dat de UNIX timestamp in de variable timestamp wordt gezet.
     */
    public timestamp() {
        this.timestamp = System.currentTimeMillis() / 1000;
        
    }
    
    /**
     * Hiermee kan de current timestamp worden opgehaald.
     *
     * @return long integer value van de timestamp.
     */
    public long getTimestamp() {
        return this.timestamp;
    }


}
