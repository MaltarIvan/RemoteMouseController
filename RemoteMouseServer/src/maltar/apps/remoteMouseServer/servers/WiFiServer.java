package maltar.apps.remoteMouseServer.servers;

import maltar.apps.remoteMouseServer.RemoteMouse;
import maltar.apps.remoteMouseServer.utilities.ActionHandler;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Maltar on 14.2.2018..
 */
public class WiFiServer implements Runnable {
    private Socket socket;
    private ServerSocket serverSocket;
    private Thread thread;
    private DataInputStream inputStream;
    private int port;

    public WiFiServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            System.out.println("Binding to port " + port + ", please wait ...");
            serverSocket = new ServerSocket(port);
            System.out.println("RemoteMouse started: " + serverSocket);
            if (thread == null) {
                thread = new Thread(this);
                thread.start();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void open() throws IOException {
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (inputStream != null) inputStream.close();
        if (serverSocket != null) serverSocket.close();
        thread = null;
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                RemoteMouse.labelActions.setText("Waiting for a client ...");
                socket = serverSocket.accept();
                System.out.println("Client accepted: " + socket);
                RemoteMouse.labelActions.setText("Client accepted: " + socket);
                open();
                boolean done = false;
                while (!done) {
                    try {
                        String line = inputStream.readUTF();
                        System.out.println(line);
                        RemoteMouse.labelActions.setText(line);
                        ActionHandler.handleAction(line);
                    } catch (IOException ioe) {
                        done = true;
                        System.out.println(ioe);
                        RemoteMouse.labelActions.setText(ioe.getMessage());
                    }
                }
            } catch (IOException ioe) {
                System.out.println("Acceptance Error: " + ioe);
                RemoteMouse.labelActions.setText("Acceptance Error: " + ioe);
            }
        }
    }

}
