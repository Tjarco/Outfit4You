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
    
    //public String convertToDate() {
       // java.util.Date time = new java.util.Date((long)timeStamp*1000);
        //return time;
    //}


}
