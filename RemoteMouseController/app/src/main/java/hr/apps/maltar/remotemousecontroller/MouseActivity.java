package hr.apps.maltar.remotemousecontroller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import java.io.DataOutputStream;
import java.net.Socket;

import hr.apps.maltar.remotemousecontroller.action.Action;
import hr.apps.maltar.remotemousecontroller.services.ConnectionIntentService;
import hr.apps.maltar.remotemousecontroller.services.SendDataIntentService;

public class MouseActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MOUSE_ACTIVITY";
    public static Socket socket;
    public static DataOutputStream dataOutputStream;

    private String ip;
    private int port;

    private View mouseView;
    private Button rightClickButton;
    private Button leftClickButton;
    private Button scrollUpButton;
    private Button scrollDownButton;
    private Button keyboardButton;

    private Point point1;
    private Point point2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        Intent intent = getIntent();
        ip = intent.getStringExtra("IP");
        port = Integer.parseInt(intent.getStringExtra("port"));

        connectToServer();

        mouseView = findViewById(R.id.mouse_view);

        rightClickButton = (Button) findViewById(R.id.right_click_button);
        leftClickButton = (Button) findViewById(R.id.left_click_button);

        scrollUpButton = (Button) findViewById(R.id.scroll_up_button);
        scrollDownButton = (Button) findViewById(R.id.scroll_down_button);

        scrollUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Action action = Action.makeClickAction("up");
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra("action", action);
                startService(intent);
                return true;
            }
        });

        scrollDownButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Action action = Action.makeClickAction("down");
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra("action", action);
                startService(intent);
                return true;
            }
        });

        rightClickButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction("right");
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra("action", action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction("right");
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra("action", action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });

        keyboardButton = (Button) findViewById(R.id.keyboard_button);
        keyboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInputFromWindow(
                        (findViewById(R.id.activity_mouse)).getApplicationWindowToken(),
                        InputMethodManager.SHOW_FORCED, 0);
            }
        });

        leftClickButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction("left");
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra("action", action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction("left");
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra("action", action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });

        mouseView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (point1 == null) {
                    point1 = new Point((int) event.getRawX(), (int) event.getRawY());
                    point2 = new Point((int) event.getRawX(), (int) event.getRawY());
                }

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_MOVE:
                        point2 = new Point((int) event.getRawX(), (int) event.getRawY());
                        int x = point2.x - point1.x;
                        int y = point2.y - point1.y;
                        point1 = point2;
                        Action action = Action.makeMoveAction(x, y);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra("action", action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        point1 = null;
                        point2 = null;
                }
                return true;
            }
        });
    }

    private void connectToServer() {
        Intent intent = new Intent(getApplicationContext(), ConnectionIntentService.class);
        intent.putExtra("IP", ip);
        intent.putExtra("port", String.valueOf(port));
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        InputMethodManager inputMethodManager =
                (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                (findViewById(R.id.activity_mouse)).getApplicationWindowToken(), 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    (findViewById(R.id.activity_mouse)).getApplicationWindowToken(), 0);

        }
        Action action = Action.makeEnterAction(keyCode);
        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
        intent.putExtra("action", action);
        startService(intent);
        return super.onKeyUp(keyCode, event);
    }
}
