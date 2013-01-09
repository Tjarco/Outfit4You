package misc;

/**
 *
 * @author Vernon de Goede
 */
public class timestamp {
    private long timestamp;

    public timestamp() {
        this.timestamp = System.currentTimeMillis() / 1000;
        
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }


}
