package hr.apps.maltar.remotemousecontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import hr.apps.maltar.remotemousecontroller.action.Action;
import hr.apps.maltar.remotemousecontroller.params.ActionKey;
import hr.apps.maltar.remotemousecontroller.params.IntentKey;
import hr.apps.maltar.remotemousecontroller.services.SendDataIntentService;

public class KalimbaActivity extends AppCompatActivity {

    private Button jumpButton;
    private Button moveLeftButton;
    private Button moveRightButton;
    private Button escButton;
    private Button enterButton;
    private Button swipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalimba);

        jumpButton = (Button) findViewById(R.id.jump_button);
        moveLeftButton = (Button) findViewById(R.id.move_left_button);
        moveRightButton = (Button) findViewById(R.id.move_right_button);
        escButton = (Button) findViewById(R.id.esc_button);
        enterButton = (Button) findViewById(R.id.enter_button);
        swipeButton = (Button) findViewById(R.id.swipe_button);

        jumpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction(ActionKey.ACTION_JUMP);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction(ActionKey.ACTION_JUMP);
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra(IntentKey.ACTION_KEY, action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });

        moveLeftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction(ActionKey.ACTION_MOVE_LEFT);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction(ActionKey.ACTION_MOVE_LEFT);
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra(IntentKey.ACTION_KEY, action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });

        moveRightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction(ActionKey.ACTION_MOVE_RIGHT);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction(ActionKey.ACTION_MOVE_RIGHT);
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra(IntentKey.ACTION_KEY, action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });

        escButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction(ActionKey.ACTION_ESC);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction(ActionKey.ACTION_ESC);
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra(IntentKey.ACTION_KEY, action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });

        enterButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction(ActionKey.ACTION_ENTER);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction(ActionKey.ACTION_ENTER);
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra(IntentKey.ACTION_KEY, action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });

        swipeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction(ActionKey.ACTION_SWIPE);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction(ActionKey.ACTION_SWIPE);
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra(IntentKey.ACTION_KEY, action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });
    }
}
