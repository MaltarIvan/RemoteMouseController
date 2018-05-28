package hr.apps.maltar.remotemousecontroller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import hr.apps.maltar.remotemousecontroller.action.Action;
import hr.apps.maltar.remotemousecontroller.params.ActionKey;
import hr.apps.maltar.remotemousecontroller.params.IntentKey;
import hr.apps.maltar.remotemousecontroller.params.SharedPreferencesParams;
import hr.apps.maltar.remotemousecontroller.services.SendDataIntentService;

public class KeyboardActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    private TextView textView;

    private Button clearButton;
    private Button backButton;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private ImageButton backspaceButton;

    private Button buttonQ;
    private Button buttonW;
    private Button buttonE;
    private Button buttonR;
    private Button buttonT;
    private Button buttonZ;
    private Button buttonU;
    private Button buttonI;
    private Button buttonO;
    private Button buttonP;
    private Button buttonA;
    private Button buttonS;
    private Button buttonD;
    private Button buttonF;
    private Button buttonG;
    private Button buttonH;
    private Button buttonJ;
    private Button buttonK;
    private Button buttonL;
    private Button buttonY;
    private Button buttonX;
    private Button buttonC;
    private Button buttonV;
    private Button buttonB;
    private Button buttonN;
    private Button buttonM;

    private ImageButton buttonUp;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    private ImageButton buttonDown;

    private Button spaceButton;
    private ImageButton enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferencesEditor = sharedPreferences.edit();

        setViews();

        String text = sharedPreferences.getString(SharedPreferencesParams.TEXT_PREFERENCE, "");
        textView.setText(text);
    }

    private void setViews() {
        textView = (TextView) findViewById(R.id.keyboard_act_text_view);
        clearButton = (Button) findViewById(R.id.keyboard_act_clr_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesEditor.putString(SharedPreferencesParams.TEXT_PREFERENCE, "");
                sharedPreferencesEditor.commit();
                textView.setText("");
            }
        });
        backButton = (Button) findViewById(R.id.keyboard_act_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonPressed();
                finish();
            }
        });

        button0 = (Button) findViewById(R.id.keyboard_act_0_button);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_0);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("0");
            }
        });
        button1 = (Button) findViewById(R.id.keyboard_act_1_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_1);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("1");
            }
        });
        button2 = (Button) findViewById(R.id.keyboard_act_2_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_2);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("2");
            }
        });
        button3 = (Button) findViewById(R.id.keyboard_act_3_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_3);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("3");
            }
        });
        button4 = (Button) findViewById(R.id.keyboard_act_4_button);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_4);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("4");
            }
        });
        button5 = (Button) findViewById(R.id.keyboard_act_5_button);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_5);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("5");
            }
        });
        button6 = (Button) findViewById(R.id.keyboard_act_6_button);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_6);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("6");
            }
        });
        button7 = (Button) findViewById(R.id.keyboard_act_7_button);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_7);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("7");
            }
        });
        button8 = (Button) findViewById(R.id.keyboard_act_8_button);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_8);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("8");
            }
        });
        button9 = (Button) findViewById(R.id.keyboard_act_9_button);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_9);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("9");
            }
        });

        backspaceButton = (ImageButton) findViewById(R.id.keyboard_act_backspace_button);
        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_DEL);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                String text = textView.getText().toString();
                if (text.length() > 0) {
                    text = text.substring(0, text.length() - 1);
                    textView.setText(text);
                }
            }
        });

        buttonQ = (Button) findViewById(R.id.keyboard_act_q_button);
        buttonQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_Q);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("q");
            }
        });
        buttonW = (Button) findViewById(R.id.keyboard_act_w_button);
        buttonW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_W);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("w");
            }
        });
        buttonE = (Button) findViewById(R.id.keyboard_act_e_button);
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_E);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("e");
            }
        });
        buttonR = (Button) findViewById(R.id.keyboard_act_r_button);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_R);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("r");
            }
        });
        buttonT = (Button) findViewById(R.id.keyboard_act_t_button);
        buttonT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_T);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("t");
            }
        });
        buttonZ = (Button) findViewById(R.id.keyboard_act_z_button);
        buttonZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_Z);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("z");
            }
        });
        buttonU = (Button) findViewById(R.id.keyboard_act_u_button);
        buttonU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_U);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("u");
            }
        });
        buttonI = (Button) findViewById(R.id.keyboard_act_i_button);
        buttonI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_I);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("i");
            }
        });
        buttonO = (Button) findViewById(R.id.keyboard_act_o_button);
        buttonO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_O);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("o");
            }
        });
        buttonP = (Button) findViewById(R.id.keyboard_act_p_button);
        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_P);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("p");
            }
        });
        buttonA = (Button) findViewById(R.id.keyboard_act_a_button);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_A);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("a");
            }
        });
        buttonS = (Button) findViewById(R.id.keyboard_act_s_button);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_S);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("s");
            }
        });
        buttonD = (Button) findViewById(R.id.keyboard_act_d_button);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_D);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("d");
            }
        });
        buttonF = (Button) findViewById(R.id.keyboard_act_f_button);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_F);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("f");
            }
        });
        buttonG = (Button) findViewById(R.id.keyboard_act_g_button);
        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_G);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("g");
            }
        });
        buttonH = (Button) findViewById(R.id.keyboard_act_h_button);
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_H);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("h");
            }
        });
        buttonJ = (Button) findViewById(R.id.keyboard_act_j_button);
        buttonJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_J);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("j");
            }
        });
        buttonK = (Button) findViewById(R.id.keyboard_act_k_button);
        buttonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_K);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("k");
            }
        });
        buttonL = (Button) findViewById(R.id.keyboard_act_l_button);
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_L);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("l");
            }
        });
        buttonY = (Button) findViewById(R.id.keyboard_act_y_button);
        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_Y);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("y");
            }
        });
        buttonX = (Button) findViewById(R.id.keyboard_act_x_button);
        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_X);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("x");
            }
        });
        buttonC = (Button) findViewById(R.id.keyboard_act_c_button);
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_C);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("c");
            }
        });
        buttonV = (Button) findViewById(R.id.keyboard_act_v_button);
        buttonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_V);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("v");
            }
        });
        buttonB = (Button) findViewById(R.id.keyboard_act_b_button);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_B);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("b");
            }
        });
        buttonN = (Button) findViewById(R.id.keyboard_act_n_button);
        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_N);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("n");
            }
        });
        buttonM = (Button) findViewById(R.id.keyboard_act_m_button);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_M);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
                textView.append("m");
            }
        });

        buttonUp = (ImageButton) findViewById(R.id.keyboard_act_up_button);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeClickAction(ActionKey.ACTION_UP);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
            }
        });
        buttonRight = (ImageButton) findViewById(R.id.keyboard_act_right_button);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeClickAction(ActionKey.ACTION_RIGHT);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
            }
        });
        buttonLeft = (ImageButton) findViewById(R.id.keyboard_act_left_button);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeClickAction(ActionKey.ACTION_LEFT);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
            }
        });
        buttonDown = (ImageButton) findViewById(R.id.keyboard_act_down_button);
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeClickAction(ActionKey.ACTION_DOWN);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
            }
        });

        spaceButton = (Button) findViewById(R.id.keyboard_act_space_button);
        spaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_SPACE);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
            }
        });
        enterButton = (ImageButton) findViewById(R.id.keyboard_act_enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = Action.makeEnterAction(KeyEvent.KEYCODE_ENTER);
                Intent intent = new Intent(getApplicationContext(), SendDataIntentService.class);
                intent.putExtra(IntentKey.ACTION_KEY, action);
                startService(intent);
            }
        });
    }

    private void backButtonPressed() {
        sharedPreferencesEditor.putString(SharedPreferencesParams.TEXT_PREFERENCE, textView.getText().toString());
        sharedPreferencesEditor.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backButtonPressed();
        }

        return super.onKeyDown(keyCode, event);
    }
}
