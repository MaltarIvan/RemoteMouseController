package hr.apps.maltar.remotemousecontroller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import hr.apps.maltar.remotemousecontroller.action.Action;
import hr.apps.maltar.remotemousecontroller.params.ActionKey;
import hr.apps.maltar.remotemousecontroller.params.IntentKey;
import hr.apps.maltar.remotemousecontroller.services.SendDataIntentService;

public class MouseActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MOUSE_ACTIVITY";

    private boolean click;
    private CountDownTimer clickTimer;
    private CountDownTimer volumeFragmentTimer;

    private View mouseView;
    private Button rightClickButton;
    private Button leftClickButton;
    private Button scrollUpButton;
    private Button scrollDownButton;
    private Button keyboardButton;
    private Button mainEnterButton;
    private Button clickerButton;
    private Button clickerPremiumButton;

    private Point point1;
    private Point point2;

    private int touchY = 0;
    private int currentY = 0;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private VolumeFragment volumeFragment;
    private FrameLayout volumeFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        volumeFragment = new VolumeFragment();

        setTimers();

        setViews();
    }

    private void setTimers() {
        clickTimer = new CountDownTimer(100, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                click = false;
            }
        };
        volumeFragmentTimer = new CountDownTimer(5000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                removeVolumeFragment();
            }
        };
    }

    private void setViews() {
        mouseView = findViewById(R.id.mouse_view);
        volumeFragmentContainer = (FrameLayout) findViewById(R.id.volume_fragment_container);

        rightClickButton = (Button) findViewById(R.id.right_click_button);
        leftClickButton = (Button) findViewById(R.id.left_click_button);

        scrollUpButton = (Button) findViewById(R.id.scroll_up_button);
        scrollDownButton = (Button) findViewById(R.id.scroll_down_button);

        mainEnterButton = (Button) findViewById(R.id.main_enter_button);

        clickerButton = (Button) findViewById(R.id.main_clicker_button);

        clickerPremiumButton = (Button) findViewById(R.id.main_clicker_pr_button);

        mainEnterButton.setOnTouchListener(new View.OnTouchListener() {
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

        scrollUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Action action = Action.makeClickAction(ActionKey.ACTION_SCROLL_UP);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                return true;
            }
        });

        scrollDownButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Action action = Action.makeClickAction(ActionKey.ACTION_SCROLL_DOWN);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                return true;
            }
        });

        rightClickButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        Action action = Action.makePressAction(ActionKey.ACTION_RIGHT_MOUSE);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction(ActionKey.ACTION_RIGHT_MOUSE);
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra(IntentKey.ACTION_KEY, action2);
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
                        Action action = Action.makePressAction(ActionKey.ACTION_LEFT_MOUSE);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Action action2 = Action.makeReleaseAction(ActionKey.ACTION_LEFT_MOUSE);
                        Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent2.putExtra(IntentKey.ACTION_KEY, action2);
                        startService(intent2);
                        break;
                }
                return true;
            }
        });

        clickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeClickAction(ActionKey.ACTION_CLICKER);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
            }
        });

        clickerPremiumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Action action = Action.makeClickAction(ActionKey.ACTION_CLICKER_PR);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                */
            }
        });

        mouseView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return handleMouseViewEvent(v, event);
            }
        });
    }

    private boolean handleMouseViewEvent(View v, MotionEvent event) {
        int pointerCount = event.getPointerCount();
        int eventActionMasked = event.getActionMasked();
        if (pointerCount == 1) {
            if (point1 == null) {
                point1 = new Point((int) event.getRawX(), (int) event.getRawY());
                point2 = new Point((int) event.getRawX(), (int) event.getRawY());
            }

            switch (eventActionMasked) {
                case MotionEvent.ACTION_BUTTON_PRESS:
                    break;
                case  MotionEvent.ACTION_DOWN:
                    click = true;
                    clickTimer.start();
                    break;
                case MotionEvent.ACTION_MOVE:
                    point2 = new Point((int) event.getRawX(), (int) event.getRawY());
                    int x = point2.x - point1.x;
                    int y = point2.y - point1.y;
                    point1 = point2;
                    Action action = Action.makeMoveAction(x, y);
                    Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                    intent.putExtra(IntentKey.ACTION_KEY, action);
                    startService(intent);
                    break;
                case MotionEvent.ACTION_UP:
                    point1 = null;
                    point2 = null;
                    if (click) {
                        action = Action.makePressAction(ActionKey.ACTION_LEFT_MOUSE);
                        intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        action = Action.makeReleaseAction(ActionKey.ACTION_LEFT_MOUSE);
                        intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                        clickTimer.cancel();
                        click = false;
                    }
                    break;
            }
        } else if (pointerCount == 2) {
            if (touchY == 0) {
                touchY = currentY = (int)event.getY();
            }
            switch (eventActionMasked) {
                case MotionEvent.ACTION_DOWN:
                    touchY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    touchY = 0;
                    currentY = 0;
                    break;
                case MotionEvent.ACTION_MOVE:
                    currentY = (int) event.getY();
                    int diffY = touchY- currentY;
                    touchY = currentY;
                    if (diffY > 0) {
                        Action action = Action.makeClickAction(ActionKey.ACTION_SCROLL_DOWN);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                    } else if (diffY < 0) {
                        Action action = Action.makeClickAction(ActionKey.ACTION_SCROLL_UP);
                        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                        intent.putExtra(IntentKey.ACTION_KEY, action);
                        startService(intent);
                    }
                    break;
            }
        }
        return true;
    }

    private boolean showVolumeFragment() {
        Action action = Action.makeVolumeControlAction("open_volume_control");
        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
        intent.putExtra(IntentKey.ACTION_KEY, action);
        startService(intent);

        fragmentTransaction = fragmentManager.beginTransaction();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 2.0f);
        volumeFragmentContainer.setLayoutParams(lp);

        fragmentTransaction.add(R.id.volume_fragment_container, volumeFragment);
        fragmentTransaction.commit();
        volumeFragmentTimer.start();
        return true;
    }

    public boolean removeVolumeFragment() {
        Action action = Action.makeVolumeControlAction("close_volume_control");
        Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
        intent.putExtra(IntentKey.ACTION_KEY, action);
        startService(intent);

        fragmentTransaction = fragmentManager.beginTransaction();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 0f);
        volumeFragmentContainer.setLayoutParams(lp);

        fragmentTransaction.remove(volumeFragment);
        fragmentTransaction.commit();
        volumeFragmentTimer.cancel();
        return true;
    }

    public void resetVolumeTimer() {
        volumeFragmentTimer.start();
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
        intent.putExtra(IntentKey.ACTION_KEY, action);
        startService(intent);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mouse_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open_kalimba_activity:
                Intent intent = new Intent(getApplicationContext(), KalimbaActivity.class);
                startActivity(intent);
                return true;
            case R.id.open_volume_fragment:
                return showVolumeFragment();
            case R.id.make_exit_action:
                Action action = Action.makeExitAction();
                Intent intent2 = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent2.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
