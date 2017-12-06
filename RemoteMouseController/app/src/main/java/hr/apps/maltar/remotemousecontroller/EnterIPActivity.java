package hr.apps.maltar.remotemousecontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.net.Socket;

import hr.apps.maltar.remotemousecontroller.params.IntentKey;
import hr.apps.maltar.remotemousecontroller.services.ConnectionIntentService;

public class EnterIPActivity extends AppCompatActivity {
    private static final String LOG_TAG = "ENTER_IP_ACTIVITY";

    private static final String DEFAULT_IP = "192.168.5.10";
    private static final String DEFAULT_PORT = "8888";

    public static Socket socket;
    public static DataOutputStream dataOutputStream;

    private EditText IPEditText;
    private EditText portEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_ip);

        IPEditText = (EditText) findViewById(R.id.ip_edit_text);
        portEditText = (EditText) findViewById(R.id.port_edit_text);

        IPEditText.setText(DEFAULT_IP);
        portEditText.setText(DEFAULT_PORT);

        Button connectButton = (Button) findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToServer();
            }
        });
    }

    private void connectToServer() {
        String ip = IPEditText.getText().toString();
        String port = portEditText.getText().toString();
        if (!ip.equals("") && !port.equals("")) {
            Intent intent = new Intent(getApplicationContext(), ConnectionIntentService.class);
            intent.putExtra(IntentKey.IP_KEY, ip);
            intent.putExtra(IntentKey.PORT_KEY, String.valueOf(port));
            startService(intent);
        }
    }
}
