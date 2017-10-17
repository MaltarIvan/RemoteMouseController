package hr.apps.maltar.remotemousecontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterIPActivity extends AppCompatActivity {
    private static final String DEFAULT_IP = "192.168.5.10";
    private static final String DEFAULT_PORT = "8888";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_ip);

        final EditText IPEditText = (EditText) findViewById(R.id.ip_edit_text);
        final EditText portEditText = (EditText) findViewById(R.id.port_edit_text);

        IPEditText.setText(DEFAULT_IP);
        portEditText.setText(DEFAULT_PORT);

        Button connectButton = (Button) findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = IPEditText.getText().toString();
                String port = portEditText.getText().toString();
                if (!ip.equals("") && !port.equals("")) {
                    Intent intent = new Intent(getApplicationContext(), MouseActivity.class);
                    intent.putExtra("IP", ip);
                    intent.putExtra("port", port);
                    startActivity(intent);
                }
            }
        });
    }
}
