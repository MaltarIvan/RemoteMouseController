package maltar.apps.remoteMouseServer.servers;

import maltar.apps.remoteMouseServer.utilities.ActionHandler;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Maltar on 14.2.2018..
 */
public class BluetoothServer implements Runnable {
    private DataInputStream inputStream;
    private StreamConnectionNotifier notifier;
    private StreamConnection connection;
    private Thread thread;

    public BluetoothServer(){
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void close() throws IOException {
        if (inputStream != null) inputStream.close();
        if (notifier != null) notifier.close();
        if (connection != null) connection.close();
        thread = null;
    }

    @Override
    public void run() {
        while (thread != null) {
            waitForConnection();
        }
    }

    /** Waiting for connection from devices */
    private void waitForConnection() {
        // retrieve the local Bluetooth device object
        LocalDevice local = null;
        connection = null;

        // setup the server to listen for connection
        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);

            UUID uuid = new UUID(80087355); // "04c6093b-0000-1000-8000-00805f9b34fb"
            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier)Connector.open(url);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // waiting for connection
        while(true) {
            try {
                System.out.println("waiting for connection...");
                connection = notifier.acceptAndOpen();

                Thread processThread = new Thread(new ProcessConnectionThread(connection));
                processThread.start();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private class ProcessConnectionThread implements Runnable {

        private StreamConnection mConnection;

        public ProcessConnectionThread(StreamConnection connection) {
            mConnection = connection;
        }

        @Override
        public void run() {
            try {
                // prepare to receive data
                inputStream = mConnection.openDataInputStream();

                System.out.println("waiting for input");

                String line = inputStream.readUTF();

                while (true) {
                    ActionHandler.handleAction(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
