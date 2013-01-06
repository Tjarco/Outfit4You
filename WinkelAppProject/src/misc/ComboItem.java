/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author Michael
 */
public class ComboItem {
    
    public String value;
    public String label;
    
    public ComboItem(String value, String label)
    {
        this.value = value;
        this.label = label;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    @Override
    public String toString() 
    {
        return label;
    }
}
