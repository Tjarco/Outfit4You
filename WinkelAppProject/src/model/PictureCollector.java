
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * @author Tjarco
 * @version 1.0
 * 
 * Een klasse Die gebruikt kan worden om afbeeldingen in het systeem te laden.
 */
public class PictureCollector {
    private JFileChooser fileChooser;

    public PictureCollector(){
        fileChooser = new JFileChooser();
      }
    
    /**
     * De methode die aangeroepen wordt om een afbeelding in source map te zetten.
     * @return Het pad van de afbeelding
     */
    public String getImage(){
        String path = "";
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {


            FileInputStream inputStream = null;
            FileOutputStream outputStream = null;
            
            //Maak een random naam voor het plaatje.
            
            
            try {
                File file = fileChooser.getSelectedFile();
                String extension = getExtension(file.getName());
                String name = Integer.toString((int)(Math.random()*125415)) + extension ;
                
                File des = new File("src/pictures/" + name);
                des.createNewFile();
                inputStream = new FileInputStream(file);
                outputStream = new FileOutputStream(des);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                path = des.getPath();
                System.out.println(path);
                
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
        
        return path;
    }
    
    private String getExtension(String path){
        int i = path.indexOf(".");
        String ext = "";
        if (i > 0 &&  i < path.length() - 1) {
            ext = path.substring(i).toLowerCase();
        }
        return ext;
    }
}
