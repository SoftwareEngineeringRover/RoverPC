/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import camera.ImageBuffer;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import unpackCommand.UnpackCommand;

/**
 *
 * @author junxin
 */
public class RoverServer implements Runnable {

    Socket socket = null;
    ServerSocket listener = null;
    //DataOutputStream out;
    ImageBuffer ib;
    RoverClient client;

    public static void main(String arg[]) {
        try {
            ImageBuffer ib = new ImageBuffer();
            RoverServer server = new RoverServer(ib);
            new Thread(server).start();
            RoverClient client = new RoverClient("150.250.220.214", ib);
            client.start();
        } catch (IOException ex) {
            Logger.getLogger(RoverServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RoverServer(ImageBuffer ib) {
        this.ib = ib;
        try {
            listener = new ServerSocket(9090);
        } catch (IOException ex) {
            Logger.getLogger(RoverServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        try {
            socket = listener.accept();
            System.out.println("Client accept");

            //out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                //out.printf("", ib.getImage(),ib.getImage2());
                //out.writeObject(ib.getImage());
                BufferedImage bi=ib.getImage();
                System.out.println(bi.toString());
                ImageIO.write(bi, "JPG", socket.getOutputStream());
            }
        } catch (IOException ex) {
            System.out.println("Unable to accept client!");
        }
    }
}
