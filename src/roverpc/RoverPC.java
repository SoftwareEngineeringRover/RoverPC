/*
 * and open the template in the editor.
 */
package roverpc;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jun,Ed,Matt,Dan,Dakota,Zhen
 */
public class RoverPC {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    }
    
    /**
     * Keeps images going
     */
    class takeIamges extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RoverPC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
