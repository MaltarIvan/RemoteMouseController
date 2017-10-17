package hr.apps.maltar.dragdistancecalculator;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private View mouseView;
    private TextView point1TextView;
    private TextView point2TextView;
    private TextView xTextView;
    private TextView yTextView;

    private Point point1;
    private Point point2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mouseView = findViewById(R.id.mouse_view);
        point1TextView = (TextView) findViewById(R.id.point1_text_view);
        point2TextView = (TextView) findViewById(R.id.point2_text_view);
        xTextView = (TextView) findViewById(R.id.x_text_view);
        yTextView = (TextView) findViewById(R.id.y_text_view);

        mouseView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (point1 == null) {
                    point1 = new Point((int) event.getRawX(), (int) event.getRawY());
                    point2 = new Point((int) event.getRawX(), (int) event.getRawY());
                    point1TextView.setText("Point1: " + point1.toString());
                    point2TextView.setText("Point2: " + point2.toString());
                }

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_MOVE:
                        point2 = new Point((int) event.getRawX(), (int) event.getRawY());
                        point2TextView.setText("Point2: " + point2.toString());
                        int x = point2.x - point1.x;
                        int y = point2.y - point1.y;
                        xTextView.setText("X: " + x);
                        yTextView.setText("Y: " + y);
                        point1 = point2;
                        point1TextView.setText("Point1: " + point1.toString());
                        break;
                }
                return true;
            }
        });
    }
}
