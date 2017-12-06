package hr.apps.maltar.remotemousecontroller.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;

import hr.apps.maltar.remotemousecontroller.EnterIPActivity;
import hr.apps.maltar.remotemousecontroller.action.Action;
import hr.apps.maltar.remotemousecontroller.params.IntentKey;

/**
 * Created by Maltar on 10.10.2017..
 */

public class SendDataIntentService extends IntentService {
    private Handler mHandler;

    public SendDataIntentService() {
        super("Send Data Intent Service");
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Action action = intent.getParcelableExtra(IntentKey.ACTION_KEY);
        try {
            EnterIPActivity.dataOutputStream.writeUTF(action.toJSON());
            EnterIPActivity.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            mHandler.post(new DisplayToast(this, "Connection lost!", Toast.LENGTH_SHORT));
            Intent newIntent = new Intent(getApplicationContext(), EnterIPActivity.class);
            startActivity(newIntent);
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
