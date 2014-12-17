package communication;

import camera.ImageBuffer;
import com.github.sarxos.webcam.WebcamLockException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import sun.misc.BASE64Encoder;

/**
 * Sends images to User client
 *
 * @author Jun,Ed,Matt,Dan,Dakota,Zhen
 */
public class RoverServer implements Runnable {

    Socket socket = null;
    ServerSocket listener = null;
    //DataOutputStream out;
    ImageBuffer ib;
    RoverClient client;
    private PrintWriter out;

    /**
     * Creates server, starts Rover client and server connects to UserClient
     *
     * @param arg
     */
    public static void main(String arg[]) {
        try {
            ImageBuffer ib = new ImageBuffer();
            RoverServer server = new RoverServer(ib);
            new Thread(server).start();
            String ipAddress = JOptionPane.showInputDialog(null,
                    "Please enter server IP", "150.250.220.119");
            RoverClient client = new RoverClient(ipAddress, ib);
            client.start();
        } catch (IOException ex) {
            Logger.getLogger(RoverServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WebcamLockException e) {
            System.out.println("Cameras locked by other application!");
        }
    }

    public RoverServer(ImageBuffer ib) throws IOException {
        this.ib = ib;
        listener = new ServerSocket(9090);
    }

    /**
     * Starts the server then starts to continuously send images
     */
    @Override
    public void run() {
        try {
            System.out.println("Server start...");
            socket = listener.accept();
            System.out.println("Client accept");
            out = new PrintWriter(socket.getOutputStream(), true);
            DataOutputStream outting = new DataOutputStream(socket.getOutputStream());
            BASE64Encoder b64enc = new BASE64Encoder();
            while (true) {
                BufferedImage bi = ib.getImage();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bi, "png", baos);
                byte[] theBytes = baos.toByteArray();
                outting.writeShort(0);
                outting.writeInt(theBytes.length);
                outting.write(theBytes);
                BufferedImage bi2 = ib.getImage2();
                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                ImageIO.write(bi2, "png", baos2);
                byte[] theBytes2 = baos2.toByteArray();
                outting.writeShort(1);
                outting.writeInt(theBytes2.length);
                outting.write(theBytes2);
                //String imageString = b64enc.encode(theBytes);
                //out.println(imageString);
                //out.println("");
                //out.flush();
                //outting.flush();
            }
        } catch (IOException ex) {
            System.out.println("Unable to accept client!");
        }
    }
}
