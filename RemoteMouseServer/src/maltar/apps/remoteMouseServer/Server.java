package maltar.apps.remoteMouseServer;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import maltar.apps.remoteMouseServer.params.ClientKeyEvents;
import org.json.*;

import javax.swing.*;

/**
 * Created by Maltar on 10.10.2017..
 */
public class Server implements Runnable {
    private Socket socket;
    private ServerSocket serverSocket;
    private Thread thread;
    private DataInputStream inputStream;
    private static JLabel label;

    public Server(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait ...");
            serverSocket = new ServerSocket(port);
            System.out.println("Server started: " + serverSocket);
            start();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void open() throws IOException {
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (inputStream != null) inputStream.close();
        thread = null;
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                label.setText("Waiting for a client ...");
                socket = serverSocket.accept();
                System.out.println("Client accepted: " + socket);
                label.setText("Client accepted: " + socket);
                open();
                boolean done = false;
                while (!done) {
                    try {
                        String line = inputStream.readUTF();
                        System.out.println(line);
                        label.setText(line);
                        handleAction(line);
                    } catch (IOException ioe) {
                        done = true;
                        System.out.println(ioe);
                        label.setText(ioe.getMessage());
                    }
                }
            } catch (IOException ioe) {
                System.out.println("Acceptance Error: " + ioe);
                label.setText("Acceptance Error: " + ioe);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Remote Mouse Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));
        label = new JLabel();
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
        Server server = new Server(8888);
    }

    private void handleActionMove(String execute, String description) {
        int xBeginIndex = description.indexOf("x") + 2;
        int xEndIndex = description.indexOf(":");

        int yBeginIndex = xEndIndex + 3;
        int yEndIndex = description.length();

        String sX = description.substring(xBeginIndex, xEndIndex);
        String sY = description.substring(yBeginIndex, yEndIndex);
        int moveX = Integer.parseInt(sX);
        int moveY = Integer.parseInt(sY);

        int maxDif = Math.abs(moveX) > Math.abs(moveY) ? Math.abs(moveX) : Math.abs(moveY);

        Point currentPoint = MouseInfo.getPointerInfo().getLocation();
        int currentX = currentPoint.x;
        int currentY = currentPoint.y;

        int newX = currentX + moveX;
        int newY = currentY + moveY;

        int stepX = currentX > newX ? -1 : 1;
        int stepY = currentY > newY ? -1 : 1;

        try {
            Robot robot = new Robot();
            for (int i = 0; i < maxDif; i++) {
                if (currentX != newX) {
                    currentX += stepX;
                }
                if (currentY != newY) {
                    currentY += stepY;
                }
                robot.mouseMove(currentX, currentY);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void handleActionClick(String execute, String description) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (description) {
            case "up":
                robot.mouseWheel(-1);
                break;
            case "down":
                robot.mouseWheel(1);
                break;
        }
    }

    private void handleActionPress(String execute, String description) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (description) {
            case "left":
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                break;
            case "right":
                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                break;
        }
    }

    private void handleActionEnter(String execute, String description) {
        int keyEvent = Integer.parseInt(description);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (keyEvent) {
            case ClientKeyEvents.KEYCODE_A:
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                break;
            case ClientKeyEvents.KEYCODE_B:
                robot.keyPress(KeyEvent.VK_B);
                robot.keyRelease(KeyEvent.VK_B);
                break;
            case ClientKeyEvents.KEYCODE_C:
                robot.keyPress(KeyEvent.VK_C);
                robot.keyRelease(KeyEvent.VK_C);
                break;
            case ClientKeyEvents.KEYCODE_D:
                robot.keyPress(KeyEvent.VK_D);
                robot.keyRelease(KeyEvent.VK_D);
                break;
            case ClientKeyEvents.KEYCODE_E:
                robot.keyPress(KeyEvent.VK_E);
                robot.keyRelease(KeyEvent.VK_E);
                break;
            case ClientKeyEvents.KEYCODE_F:
                robot.keyPress(KeyEvent.VK_F);
                robot.keyRelease(KeyEvent.VK_F);
                break;
            case ClientKeyEvents.KEYCODE_G:
                robot.keyPress(KeyEvent.VK_G);
                robot.keyRelease(KeyEvent.VK_G);
                break;
            case ClientKeyEvents.KEYCODE_H:
                robot.keyPress(KeyEvent.VK_H);
                robot.keyRelease(KeyEvent.VK_H);
                break;
            case ClientKeyEvents.KEYCODE_I:
                robot.keyPress(KeyEvent.VK_I);
                robot.keyRelease(KeyEvent.VK_I);
                break;
            case ClientKeyEvents.KEYCODE_J:
                robot.keyPress(KeyEvent.VK_J);
                robot.keyRelease(KeyEvent.VK_J);
                break;
            case ClientKeyEvents.KEYCODE_K:
                robot.keyPress(KeyEvent.VK_K);
                robot.keyRelease(KeyEvent.VK_K);
                break;
            case ClientKeyEvents.KEYCODE_L:
                robot.keyPress(KeyEvent.VK_L);
                robot.keyRelease(KeyEvent.VK_L);
                break;
            case ClientKeyEvents.KEYCODE_M:
                robot.keyPress(KeyEvent.VK_M);
                robot.keyRelease(KeyEvent.VK_M);
                break;
            case ClientKeyEvents.KEYCODE_N:
                robot.keyPress(KeyEvent.VK_N);
                robot.keyRelease(KeyEvent.VK_N);
                break;
            case ClientKeyEvents.KEYCODE_O:
                robot.keyPress(KeyEvent.VK_O);
                robot.keyRelease(KeyEvent.VK_O);
                break;
            case ClientKeyEvents.KEYCODE_P:
                robot.keyPress(KeyEvent.VK_P);
                robot.keyRelease(KeyEvent.VK_P);
                break;
            case ClientKeyEvents.KEYCODE_Q:
                robot.keyPress(KeyEvent.VK_Q);
                robot.keyRelease(KeyEvent.VK_Q);
                break;
            case ClientKeyEvents.KEYCODE_R:
                robot.keyPress(KeyEvent.VK_R);
                robot.keyRelease(KeyEvent.VK_R);
                break;
            case ClientKeyEvents.KEYCODE_S:
                robot.keyPress(KeyEvent.VK_S);
                robot.keyRelease(KeyEvent.VK_S);
                break;
            case ClientKeyEvents.KEYCODE_T:
                robot.keyPress(KeyEvent.VK_T);
                robot.keyRelease(KeyEvent.VK_T);
                break;
            case ClientKeyEvents.KEYCODE_U:
                robot.keyPress(KeyEvent.VK_U);
                robot.keyRelease(KeyEvent.VK_U);
                break;
            case ClientKeyEvents.KEYCODE_V:
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                break;
            case ClientKeyEvents.KEYCODE_W:
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_W);
                break;
            case ClientKeyEvents.KEYCODE_X:
                robot.keyPress(KeyEvent.VK_X);
                robot.keyRelease(KeyEvent.VK_X);
                break;
            case ClientKeyEvents.KEYCODE_Y:
                robot.keyPress(KeyEvent.VK_Y);
                robot.keyRelease(KeyEvent.VK_Y);
                break;
            case ClientKeyEvents.KEYCODE_Z:
                robot.keyPress(KeyEvent.VK_Z);
                robot.keyRelease(KeyEvent.VK_Z);
                break;
            case ClientKeyEvents.KEYCODE_0:
                robot.keyPress(KeyEvent.VK_0);
                robot.keyRelease(KeyEvent.VK_0);
                break;
            case ClientKeyEvents.KEYCODE_1:
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                break;
            case ClientKeyEvents.KEYCODE_2:
                robot.keyPress(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_2);
                break;
            case ClientKeyEvents.KEYCODE_3:
                robot.keyPress(KeyEvent.VK_3);
                robot.keyRelease(KeyEvent.VK_3);
                break;
            case ClientKeyEvents.KEYCODE_4:
                robot.keyPress(KeyEvent.VK_4);
                robot.keyRelease(KeyEvent.VK_4);
                break;
            case ClientKeyEvents.KEYCODE_5:
                robot.keyPress(KeyEvent.VK_5);
                robot.keyRelease(KeyEvent.VK_5);
                break;
            case ClientKeyEvents.KEYCODE_6:
                robot.keyPress(KeyEvent.VK_6);
                robot.keyRelease(KeyEvent.VK_6);
                break;
            case ClientKeyEvents.KEYCODE_7:
                robot.keyPress(KeyEvent.VK_7);
                robot.keyRelease(KeyEvent.VK_7);
                break;
            case ClientKeyEvents.KEYCODE_8:
                robot.keyPress(KeyEvent.VK_8);
                robot.keyRelease(KeyEvent.VK_8);
                break;
            case ClientKeyEvents.KEYCODE_9:
                robot.keyPress(KeyEvent.VK_9);
                robot.keyRelease(KeyEvent.VK_9);
                break;
            case ClientKeyEvents.KEYCODE_SPACE:
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
                break;
            case ClientKeyEvents.KEYCODE_DEL:
                robot.keyPress(KeyEvent.VK_BACK_SPACE);
                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                break;
            case ClientKeyEvents.KEYCODE_ENTER:
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                break;
            case ClientKeyEvents.KEYCODE_SHIFT_LEFT:
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
                break;
        }
    }

    private void handleActionRelease(String execute, String description) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (description) {
            case "left":
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                break;
            case "right":
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                break;
        }
    }

    private void handleAction(String action) {
        JSONObject jsonObject = new JSONObject(action);
        String execute = jsonObject.getString("execute");
        String description = jsonObject.getString("description");
        switch (execute) {
            case "move":
                handleActionMove(execute, description);
                break;
            case "click":
                handleActionClick(execute, description);
                break;
            case "press":
                handleActionPress(execute, description);
                break;
            case "release":
                handleActionRelease(execute, description);
                break;
            case "enter":
                handleActionEnter(execute, description);
                break;
        }
    }
}
