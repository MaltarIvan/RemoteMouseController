package client;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Maltar on 10.10.2017..
 */
public class Main {

    public static void main(String[] args) {
        Socket socket;
        DataOutputStream streamOut;

        String ip = "192.168.5.11";
        int port = 8888;
        System.out.println("Establishing connection. Please wait ...");
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected: " + socket);

            streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            Action action = Action.makeMoveAction(10, 10);
            streamOut.writeUTF(action.toString());
            streamOut.flush();

            /*
            for (int i = 0; i < 4; i++) {
                streamOut.writeUTF("HELLO");
                streamOut.flush();
            }
            */
        } catch (UnknownHostException uhe) {
            System.out.println("Unknown host: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }
}
