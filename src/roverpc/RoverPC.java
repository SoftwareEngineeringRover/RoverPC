/*
 * and open the template in the editor.
 */
package roverpc;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author junxin
 */
public class RoverPC {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    }
    
    class takeIamges extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    //BufferedImage img = webcam.getImage(); //rover
                    //BufferedImage img2 = webcam2.getImage(); //rover
                    
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RoverPC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
