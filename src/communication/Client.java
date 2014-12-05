/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
    String message;

    public static void main(String[] args) throws IOException, SocketException {
        String serverAddress = JOptionPane.showInputDialog(
                "Enter IP Address of a machine that is\n"
                + "running the date service on port 9090:");
        String message = JOptionPane.showInputDialog(
                "Enter Message:\n");
        Client c = new Client(serverAddress, message);
        c.run();
    }

    public Client(String serverAddress, String message) throws UnknownHostException, IOException {
        this.message = message;
        while (!done) {
            try {
                if (serverAddress.equals("")) {
                    throw new UnknownHostException();
                }
                s = new Socket(serverAddress, 9090);
                in = new ObjectInputStream(s.getInputStream());
                out = new ObjectOutputStream(s.getOutputStream());
                done = true;
            } catch (ConnectException e) {
                System.out.println("Wait for servor...");
            }
        }
    }

    public void run() {
        while (true) {
            try {
                sendMessage();
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

    public synchronized void sendMessage() throws IOException {
        out.writeObject(message);
    }

    public synchronized void getInput() throws IOException, ClassNotFoundException {
        System.out.print(in.readObject().toString());
    }

    public void close() throws IOException {
        in.close();
        s.close();
    }
}
