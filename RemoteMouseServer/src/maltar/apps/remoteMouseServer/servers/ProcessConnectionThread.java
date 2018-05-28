package maltar.apps.remoteMouseServer.servers;

import javax.microedition.io.StreamConnection;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;

/**
 * Created by Maltar on 1.5.2018..
 */
public class ProcessConnectionThread implements Runnable {
    private StreamConnection mConnection;

    // Constant that indicate command from devices
    private static final int EXIT_CMD = -1;
    private static final int KEY_RIGHT = 1;
    private static final int KEY_LEFT = 2;

    public ProcessConnectionThread(StreamConnection connection) {
        mConnection = connection;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = mConnection.openInputStream();
            System.out.println("Waiting for input...");

            while (true) {
                int command = inputStream.read();

                if (command == EXIT_CMD) {
                    System.out.println("Finish process");
                    break;
                }
                processCommand(command); //Function that can be changed
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processCommand(int command) {
        try {
            Robot robot = new Robot();
            switch (command) {
                case KEY_RIGHT:
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    System.out.println("Right");
                    break;
                case KEY_LEFT:
                    robot.keyPress(KeyEvent.VK_LEFT);
                    System.out.println("Left");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
