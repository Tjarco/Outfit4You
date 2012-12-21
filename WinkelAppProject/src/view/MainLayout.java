
package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.WinkelApplication;

/**
 * @author Tjarco
 * 
 * main Layout. De layout die gebruikt wordt voor de panels.
 * 
 */
public class MainLayout extends JPanel {
    protected JPanel top;
    protected JPanel bottom;
    private JButton jbTerug;
    
    public MainLayout(){
        initComponents();
    }
    
    private void initComponents(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
                
        top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //de dimensie van de panel (de breedte is nul, want deze wordt door de borderlayout bepaald)
        top.setPreferredSize(new Dimension(0,70));
        top.setBackground(WinkelApplication.BACKGROUND);
        top.setOpaque(true);        
        add(top, BorderLayout.PAGE_START);
        
        bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //de dimensie van de panel (de breedte is nul, want deze wordt door de borderlayout bepaald)
        bottom.setPreferredSize(new Dimension(0,70));
        bottom.setBackground(WinkelApplication.BACKGROUND);
        bottom.setOpaque(true);
        add(bottom, BorderLayout.PAGE_END);
    }
    
    protected void AddTitle(String title){
        JLabel titel = new JLabel(title);
        titel.setFont(main.WinkelApplication.TITEL);
        titel.setForeground(Color.white);
        titel.setPreferredSize(new Dimension(500,70));
        top.add(titel);
        revalidate();    
    }
    /**
     * Voegt een terugknop toe aan de panel. 
     * @param pane 
     */
    protected void addBackButton(JPanel pane){
        jbTerug = new JButton("Terug");
        jbTerug.setFont(new Font("Calibri", Font.PLAIN, 18));
        jbTerug.setIcon(new ImageIcon(getClass().getResource("/pictures/backButton.png")));
        jbTerug.setContentAreaFilled(false);
        jbTerug.setBorderPainted(false);
        jbTerug.setFocusPainted(false);
        jbTerug.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jbTerug.addMouseListener(new BackButtonListener(pane));
        
        bottom.add(jbTerug); 
    }
    
    /**
     * listener voor de backbutton.
     */
    private class BackButtonListener implements MouseListener{
        //De panel die getoond moet worden als er op de back knop geklikt wordt.
        private JPanel pane;
        
        /**
         * de constructor van de Listener
         * 
         * @param pane wordt gebruikt om te bepalen waar naar teruggekeerd moet worden.
         */
        public BackButtonListener(JPanel pane){
            this.pane = pane;
        }

        public void mouseClicked(MouseEvent e) {
            
        }

        
        public void mousePressed(MouseEvent e) {
            try{
                main.WinkelApplication.getInstance().showPanel(pane);
            }catch(Exception ex){
                System.err.print("Cannot display panel");
            }
        }

        public void mouseReleased(MouseEvent e) {
            
        }

        public void mouseEntered(MouseEvent e) {
            
        }

        public void mouseExited(MouseEvent e) {
            
        }
    
    }
    
}
