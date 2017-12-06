package hr.apps.maltar.remotemousecontroller.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import hr.apps.maltar.remotemousecontroller.EnterIPActivity;
import hr.apps.maltar.remotemousecontroller.MouseActivity;
import hr.apps.maltar.remotemousecontroller.params.IntentKey;

import static hr.apps.maltar.remotemousecontroller.EnterIPActivity.socket;

/**
 * Created by Maltar on 10.10.2017..
 */

public class ConnectionIntentService extends IntentService {
    private static final String LOG_TAG = "ConnectionService";
    private Handler mHandler;

    public ConnectionIntentService() {
        super("Connection Intent Service");
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String ip = intent.getStringExtra(IntentKey.IP_KEY);
        int port = Integer.parseInt(intent.getStringExtra(IntentKey.PORT_KEY));
        try {
            socket = new Socket(ip, port);
            EnterIPActivity.dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            Intent newIntent = new Intent(getApplicationContext(), MouseActivity.class);
            newIntent.putExtra(IntentKey.IP_KEY, ip);
            newIntent.putExtra(IntentKey.PORT_KEY, port);
            startActivity(newIntent);
        } catch (IOException ioe) {
            Log.d(LOG_TAG, ioe.getMessage());
            mHandler.post(new DisplayToast(this, "Connection refused.\nTry different IP address or port.", Toast.LENGTH_LONG));
        }
    }

    private class DisplayToast implements Runnable {
        private final Context mContext;
        private String mText;
        private int length;

        public DisplayToast(Context mContext, String text, int length){
            this.mContext = mContext;
            this.mText = text;
            this.length = length;
        }

        public void run(){
            Toast.makeText(mContext, mText, length).show();
        }
    }
}
