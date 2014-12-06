/*
 * and open the template in the editor.
 */
package camera;

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
    List<Webcam> camList;
    Webcam webcam;
    Webcam webcam2;
    int[] sendCamList=new int[2];
    
    public ImageBuffer(){
        camList = Webcam.getWebcams();
        webcam = camList.get(0);
        webcam2 = camList.get(1);
        webcam.open(); //rover
        webcam2.open(); //rover
    }
    
    public BufferedImage getImage()
    {
        BufferedImage img = webcam.getImage(); //rover
        return img;
    }
    
    public BufferedImage getImage2()
    {
        BufferedImage img2 = webcam2.getImage(); //rover
        return img2;
    }
    
    public void closeCamera()
    {
        webcam.close();
        webcam2.close();
    }
    
     public void setSendCamList(String[] list){
        sendCamList[0]=Integer.parseInt(list[1]);
        sendCamList[1]=Integer.parseInt(list[2]);
    }
     
     public boolean isOpen(){
         return webcam.isOpen();
     }
    
}