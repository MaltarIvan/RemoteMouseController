package hr.apps.maltar.remotemousecontroller.services;

import android.app.IntentService;
import android.content.Intent;

import java.io.IOException;

import hr.apps.maltar.remotemousecontroller.MouseActivity;
import hr.apps.maltar.remotemousecontroller.action.Action;

/**
 * Created by Maltar on 10.10.2017..
 */

public class SendDataIntentService extends IntentService {

    public SendDataIntentService() {
        super("Send Data Intent Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Action action = intent.getParcelableExtra("action");
        try {
            MouseActivity.dataOutputStream.writeUTF(action.toJSON());
            MouseActivity.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
