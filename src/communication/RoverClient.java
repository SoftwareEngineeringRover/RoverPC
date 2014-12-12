/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import camera.ImageBuffer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import unpackCommand.UnpackCommand;

/**
 * A simple Swing-based client for the capitalization server. It has a main
 * frame window with a text field for entering strings and a textarea to see the
 * results of capitalizing them.
 */
public class RoverClient extends Thread{

    BufferedReader input;
    Socket s = null;
    boolean done = false;
    String serverAddress;
    UnpackCommand uc;
    ImageBuffer ib;

    public static void main(String[] args){
        String serverAddress = JOptionPane.showInputDialog(
                "Enter IP Address of a machine that is\n"
                + "running the date service on port 9090:");
        try {
            RoverClient c = new RoverClient(serverAddress, null);
        } catch (IOException ex) {
            Logger.getLogger(RoverClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RoverClient(String serverAddress, ImageBuffer ib) throws UnknownHostException, IOException{
        this.serverAddress=serverAddress;
        this.ib=ib;
        uc=new UnpackCommand(ib);
    }
    
    @Override
    public void run(){
        while (!done) {
            try {
                if (serverAddress.equals("")) {
                    throw new UnknownHostException();
                }
                s = new Socket(serverAddress, 9090);
                input =new BufferedReader(new InputStreamReader(s.getInputStream()));
                done = true;
            } catch (ConnectException e) {
                System.out.println("Wait for servor...");
            } catch (UnknownHostException ex) {
                System.out.println("Un known Host Exception Occur...");
            } catch (IOException ex) {
                Logger.getLogger(RoverClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        while(true){
            try {
                String info=input.readLine();
                //System.out.println(info);
                uc.sendCommand(info);
            } catch (IOException ex) {
                Logger.getLogger(RoverClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void close() throws IOException {
        input.close();
        s.close();
    }
}
