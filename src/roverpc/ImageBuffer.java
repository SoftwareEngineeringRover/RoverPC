/*
 * and open the template in the editor.
 */
package roverpc;

import com.github.sarxos.webcam.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.*;
import java.util.List;
import javax.swing.ImageIcon;
/**
 *
 * @author junxin
 */
public class ImageBuffer {
    static List<Webcam> camList = Webcam.getWebcams();
    static Webcam webcam = camList.get(0);
    static Webcam webcam2 = camList.get(1);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        webcam.open(); //rover
        webcam2.open(); //rover
        while (true)
        {
            
        }
    }
    
    public static BufferedImage getImage()
    {
        BufferedImage img = webcam.getImage(); //rover
        return img;
    }
    
    public static BufferedImage getImage2()
    {
        BufferedImage img2 = webcam2.getImage(); //rover
        return img2;
    }
    
    public static void closeCamera()
    {
        webcam.close();
        webcam2.close();
    }
    
}