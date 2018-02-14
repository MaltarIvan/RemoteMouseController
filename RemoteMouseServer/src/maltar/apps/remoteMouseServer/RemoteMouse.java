package maltar.apps.remoteMouseServer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

import maltar.apps.remoteMouseServer.servers.BluetoothServer;
import maltar.apps.remoteMouseServer.servers.WiFiServer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Created by Maltar on 10.10.2017..
 */
public class RemoteMouse {
    private static final int PORT = 8888;

    private static JLabel labelDirection;
    private static JButton buttonWiFi;
    private static JButton buttonBluetooth;
    private static JButton buttonBack;

    public static JLabel labelActions;
    public static JLabel labelIP;
    public static JLabel labelPort;

    private static WiFiServer wiFiServer;
    private static BluetoothServer bluetoothServer;

    public static void main(String[] args) {
        wiFiServer = new WiFiServer(PORT);
        bluetoothServer = new BluetoothServer();

        JFrame frame = new JFrame("Remote Mouse RemoteMouse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));

        makeHomePane(frame);
    }

    private static  void makeHomePane(JFrame frame) {
        Container pane = frame.getContentPane();
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        labelDirection = new JLabel();
        labelDirection.setText("Choose the server type");
        buttonWiFi = new JButton("Open WiFi Server");
        buttonWiFi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeWiFiPane(frame);
                wiFiServer.start();
            }
        });
        buttonBluetooth = new JButton("Open Bloothoth Server");
        buttonBluetooth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeBluetoothPane(frame);
                bluetoothServer.start();
            }
        });
        pane.add(labelDirection);
        pane.add(buttonBluetooth);
        pane.add(buttonWiFi);

        frame.pack();
        frame.setVisible(true);
    }

    private static void makeWiFiPane(JFrame frame) {
        Container pane = frame.getContentPane();
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        labelActions = new JLabel();
        labelIP = new JLabel();
        labelIP.setText("IP: " + getIPAddress());
        labelPort = new JLabel();
        labelPort.setText("PORT: " + String.valueOf(PORT));

        Border labelIPBorder = labelIP.getBorder();
        Border labelIPMargin = new EmptyBorder(10,10,10,10);

        Border labelPortBorder = labelIP.getBorder();
        Border labelPortMargin = new EmptyBorder(10,10,70,10);

        labelIP.setBorder(new CompoundBorder(labelIPBorder, labelIPMargin));
        labelPort.setBorder(new CompoundBorder(labelPortBorder, labelPortMargin));

        Font labelIPFont = labelIP.getFont();
        Font labelPortFont = labelPort.getFont();

        labelIP.setFont(new Font(labelIPFont.getName(), Font.BOLD, labelIPFont.getSize() * 2));
        labelPort.setFont(new Font(labelPortFont.getName(), Font.BOLD, labelIPFont.getSize() + 5));

        buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    wiFiServer.close();
                    makeHomePane(frame);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        pane.add(labelIP);
        pane.add(labelPort);
        pane.add(labelActions);
        pane.add(buttonBack);

        frame.pack();
        frame.setVisible(true);
    }

    private static void makeBluetoothPane(JFrame frame) {
        Container pane = frame.getContentPane();
        pane.removeAll();
        pane.revalidate();
        pane.repaint();

        buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bluetoothServer.close();
                    makeHomePane(frame);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        pane.add(buttonBack);

        frame.pack();
        frame.setVisible(true);
    }

    private static String getIPAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    // *EDIT*
                    if (addr instanceof Inet6Address) continue;

                    String ip = addr.getHostAddress();
                    System.out.println(iface.getDisplayName() + "\n" + ip);
                    return ip;
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return "Can't get local IP address.";
    }
}
