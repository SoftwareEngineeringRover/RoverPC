/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * A simple Swing-based client for the capitalization server. It has a main
 * frame window with a text field for entering strings and a textarea to see the
 * results of capitalizing them.
 */
public class Client {
    BufferedReader in = null;
    Socket s = null;
    boolean done = false;

    public static void main(String[] args) throws IOException {
        String serverAddress = JOptionPane.showInputDialog(
                "Enter IP Address of a machine that is\n"
                + "running the date service on port 9090:");
        Client c=new Client(serverAddress);
        while(true){
            System.out.println(c.getInput());
        }
    }

    public Client(String serverAddress) throws UnknownHostException, IOException {
        while (!done) {
            try {
                if (serverAddress.equals("") || serverAddress == null) {
                    throw new UnknownHostException();
                }
                s = new Socket(serverAddress, 9090);
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                done = true;
            } catch (ConnectException e) {
                System.out.println("Wait for servor...");
            }
        }
    }
    
    public String getInput() throws IOException{
        return in.readLine();
    }
    
    public void close() throws IOException{
        in.close();
        s.close();
    }
}
