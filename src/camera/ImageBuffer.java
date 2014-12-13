/*
 * and open the template in the editor.
 */
package camera;

import com.github.sarxos.webcam.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author junxin
 */
public class ImageBuffer {

    List<Webcam> camList;
    Webcam webcam;
    Webcam webcam2;
    int[] sendCamList = {0, 1};
    //future sendCamList={0,1};

    public ImageBuffer() {
        camList = Webcam.getWebcams();
        webcam = camList.get(sendCamList[0]);
        webcam2 = camList.get(sendCamList[1]);
        webcam.open(); //rover
        webcam2.open(); //rover
    }

    public BufferedImage getImage() {
        BufferedImage img = webcam.getImage(); //rover
        return img;
    }

    public BufferedImage getImage2() {
        BufferedImage img2 = webcam2.getImage(); //rover
        return img2;
    }

    public void closeCamera() {
        webcam.close();
        webcam2.close();
    }

    public void setSendCamList(String[] list) {
        int num1=Integer.parseInt(list[0]);
        int num2=Integer.parseInt(list[1]);
        //future
        if(num1!=sendCamList[0]&&num1<camList.size()){
            webcam.close();
            webcam=null;
            webcam=camList.get(num1);
            webcam.open();
        }
        if(num2!=sendCamList[1]&&num2<camList.size()){
            webcam2.close();
            webcam2=null;
            webcam2=camList.get(num2);
            webcam2.open();
        }
    }

    public boolean isOpen() {
        return webcam.isOpen() && webcam2.isOpen();
    }

    public void closeWebcams() {
        for (Webcam camList1 : camList) {
            camList1.close();
        }
    }

}
