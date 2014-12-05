/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * A simple Swing-based client for the capitalization server. It has a main
 * frame window with a text field for entering strings and a textarea to see the
 * results of capitalizing them.
 */
public class Client extends Thread {

    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    Socket s = null;
    boolean done = false;

    public static void main(String[] args) throws IOException, SocketException {
        String serverAddress = JOptionPane.showInputDialog(
                "Enter IP Address of a machine that is\n"
                + "running the date service on port 9090:");
        Client c = new Client(serverAddress);
        c.run();
    }

    public Client(String serverAddress) throws UnknownHostException, IOException {
        while (!done) {
            try {
                if (serverAddress.equals("")) {
                    throw new UnknownHostException();
                }
                s = new Socket(serverAddress, 9090);
                in = new ObjectInputStream(s.getInputStream());
                done = true;
            } catch (ConnectException e) {
                System.out.println("Wait for servor...");
            }
        }
    }

    public void run() {
        while (true) {
            try {
                getInput();
                Thread.sleep(100);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getInput() throws IOException, ClassNotFoundException {
        System.out.print((String)in.readObject());
    }

    public void close() throws IOException {
        in.close();
        s.close();
    }
}
