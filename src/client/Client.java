/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * A simple Swing-based client for the capitalization server. It has a main
 * frame window with a text field for entering strings and a textarea to see the
 * results of capitalizing them.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        String serverAddress = null;
        serverAddress = JOptionPane.showInputDialog(
                "Enter IP Address of a machine that is\n"
                + "running the date service on port 9090:");
        Socket s = null;
        boolean done = false;
        PrintWriter out = null;
        BufferedReader in = null;
        String n = "hi";
        try {
            while (!done) {
                try {
                    if (serverAddress.equals("") || serverAddress == null) {
                        throw new UnknownHostException();
                    }
                    s = new Socket(serverAddress, 9090);
                    done = true;
                } catch (ConnectException e) {
                    System.out.println("Wait for servor...");
                }
            }
            while (true) {
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out = new PrintWriter(s.getOutputStream(), true);
                out.print(n);
                String answer = in.readLine();
                System.out.println(answer);
            }

        } catch (UnknownHostException e) {
            System.exit(0);
        } catch (SocketException e) {
            s.close();
            System.exit(0);
        }
    }
}
