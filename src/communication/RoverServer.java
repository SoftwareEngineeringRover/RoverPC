package communication;

import camera.ImageBuffer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Sends images to User client
 * @author Jun,Ed,Matt,Dan,Dakota,Zhen
 */
public class RoverServer implements Runnable {

    Socket socket = null;
    ServerSocket listener = null;
    //DataOutputStream out;
    ImageBuffer ib;
    RoverClient client;

    /**
     * Creates server, starts Rover client and
     * server connects to UserClient
     * @param arg 
     */
    public static void main(String arg[]) {
        try {
            ImageBuffer ib = new ImageBuffer();
            RoverServer server = new RoverServer(ib);
            new Thread(server).start();
            RoverClient client = new RoverClient("150.250.218.175", ib);
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

    /**
     * Starts the server then starts to
     * continuously send images
     */
    @Override
    public void run() {
        try {
            System.out.println("Server start...");
            socket = listener.accept();
            System.out.println("Client accepted");
            while (true) {
                BufferedImage bi=ib.getImage();
                ImageIO.write(bi, "JPG", socket.getOutputStream());
            }
        } catch (IOException ex) {
            System.out.println("Unable to accept client!");
        }
    }
}
