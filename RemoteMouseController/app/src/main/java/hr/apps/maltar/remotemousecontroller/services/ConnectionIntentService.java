package hr.apps.maltar.remotemousecontroller.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import hr.apps.maltar.remotemousecontroller.MouseActivity;

import static hr.apps.maltar.remotemousecontroller.MouseActivity.socket;

/**
 * Created by Maltar on 10.10.2017..
 */

public class ConnectionIntentService extends IntentService {
    private static final String LOG_TAG = "ConnectionService";

    public ConnectionIntentService() {
        super("Connection Intent Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String ip = intent.getStringExtra("IP");
        int port = Integer.parseInt(intent.getStringExtra("port"));
        try {
            socket = new Socket(ip, port);
            MouseActivity.dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException ioe) {
            Log.d(LOG_TAG, ioe.getMessage());
        }
    }
}
