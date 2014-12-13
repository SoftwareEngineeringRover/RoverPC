/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import camera.ImageBuffer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.misc.BASE64Encoder;

/**
 *
 * @author junxin
 */
public class RoverServer implements Runnable {

    Socket socket = null;
    ServerSocket listener = null;
    ImageBuffer ib;
    RoverClient client;
    private PrintWriter out;
    BASE64Encoder b64enc;

    public static void main(String arg[]) {
        try {
            ImageBuffer ib = new ImageBuffer();
            RoverServer server = new RoverServer(ib);
            new Thread(server).start();
            RoverClient client = new RoverClient("150.250.221.2", ib);
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
            System.out.println("Server start...");
            socket = listener.accept();
            System.out.println("Client accept");
            out = new PrintWriter(socket.getOutputStream(), true);
            b64enc = new BASE64Encoder();
            int k = 0;
            while (true) {
                BufferedImage bi = ib.getImage();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bi, "png", baos);
                byte[] theBytes = baos.toByteArray();
                String imageString = b64enc.encode(theBytes);
                out.println(imageString);
                out.println("");
                out.flush();
            }
        } catch (IOException ex) {
            System.out.println("Unable to accept client!");
        }
    }
}
