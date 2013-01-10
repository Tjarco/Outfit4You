
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

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
        
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new ImageFilter());
        
        
      }
    
    /**
     * De methode die aangeroepen wordt om een afbeelding in source map te zetten.
     * @return String[] Het eerste object is het pad van de afbeelding die ingeladen wordt, het tweede object is
     * het pad van de nieuwe afbeelding
     */
    public String[] getImage(){
        String path[] = new String[2];
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {


            FileInputStream inputStream = null;
            FileOutputStream outputStream = null;
                     
                        
            try {
                File file = fileChooser.getSelectedFile();
                String extension = getExtension(file.getName());
                //Maak een random naam voor het plaatje.
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
                path[0] = file.getAbsolutePath();
                path[1] = des.getPath();
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
    
    /**
     * Een filter voor afbeeldingen
     * 
     * Zorgt ervoor dat de filechooser alleen maar afbeeldingen accepteert.
     */
    private class ImageFilter extends FileFilter{

        @Override
        public boolean accept(File f) {
            boolean valid = false;
        if (f.isDirectory()) {
           return true;
        }
 
        String extension = getExtension(f.getName());
        if (extension != null) {
            if (extension.equals(".tiff") ||
                extension.equals(".tif") ||
                extension.equals(".gif") ||
                extension.equals(".jpeg") ||
                extension.equals(".jpg") ||
                extension.equals(".png")) {
                    valid= true;
            } else {
                valid = false;
            }
        }
        
        return valid;
 
        }

        @Override
        public String getDescription() {
            return "*.tiff, *.tif, *.gif, *.jpeg, *.jpg, *.png";
                   
        }
    
    }
}
