package maltar.apps.remoteMouseServer.servers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
    /**
     * private DataInputStream inputStream;
     * private StreamConnectionNotifier notifier;
     * private StreamConnection connection;
     * private Thread thread;
     * <p>
     * public BluetoothServer(){
     * }
     * <p>
     * public void start() {
     * if (thread == null) {
     * thread = new Thread(this);
     * thread.start();
     * }
     * }
     * <p>
     * public void close() throws IOException {
     * if (inputStream != null) inputStream.close();
     * if (notifier != null) notifier.close();
     * if (connection != null) connection.close();
     * thread = null;
     * }
     *
     * @Override public void run() {
     * while (thread != null) {
     * waitForConnection();
     * }
     * }
     * <p>
     * // Waiting for connection from devices
     * private void waitForConnection() {
     * // retrieve the local Bluetooth device object
     * LocalDevice local = null;
     * connection = null;
     * <p>
     * // setup the server to listen for connection
     * try {
     * local = LocalDevice.getLocalDevice();
     * local.setDiscoverable(DiscoveryAgent.GIAC);
     * <p>
     * UUID uuid = new UUID(80087355); // "04c6093b-0000-1000-8000-00805f9b34fb"
     * String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
     * notifier = (StreamConnectionNotifier)Connector.open(url);
     * } catch (Exception e) {
     * e.printStackTrace();
     * return;
     * }
     * // waiting for connection
     * while(true) {
     * try {
     * System.out.println("waiting for connection...");
     * connection = notifier.acceptAndOpen();
     * <p>
     * Thread processThread = new Thread(new ProcessConnectionThread(connection));
     * processThread.start();
     * } catch (Exception e) {
     * e.printStackTrace();
     * return;
     * }
     * }
     * }
     * <p>
     * private class ProcessConnectionThread implements Runnable {
     * <p>
     * private StreamConnection mConnection;
     * <p>
     * public ProcessConnectionThread(StreamConnection connection) {
     * mConnection = connection;
     * }
     * @Override public void run() {
     * try {
     * // prepare to receive data
     * inputStream = mConnection.openDataInputStream();
     * <p>
     * System.out.println("waiting for input");
     * <p>
     * String line = inputStream.readUTF();
     * <p>
     * while (true) {
     * ActionHandler.handleAction(line);
     * }
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     * }
     **/

    private Thread thread;

    public BluetoothServer() {
    }

    @Override
    public void run() {
        waitForConnection();
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void close() throws IOException {
        if (thread != null) thread = null;
    }

    private void waitForConnection() {
        // retrieve the local Bluetooth device object
        LocalDevice local = null;

        StreamConnectionNotifier notifier;
        StreamConnection connection = null;

        // setup the server to listen for connection
        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);

            UUID uuid = new UUID("d0c722b07e1511e1b0c40800200c9a66", false);
            System.out.println(uuid.toString());

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
                System.out.println("After AcceptAndOpen...");

                Thread processThread = new Thread(new ProcessConnectionThread(connection));
                processThread.start();

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
