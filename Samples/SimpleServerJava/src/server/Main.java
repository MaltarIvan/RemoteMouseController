package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Maltar on 10.10.2017..
 */
public class Main {
    public static void main(String[] args) {
        ServerSocket server;
        Socket socket;
        int port = 54234;

        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            while (true) {

            }
        } catch (IOException ioe) {
            System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
        }
    }
}
