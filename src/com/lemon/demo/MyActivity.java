package com.lemon.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity {
    Button myButton;
    protected static final String TAG = "MyButton";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        myButton = (Button) findViewById(R.id.btn);

        myButton.setOnTouchListener(new View.OnTouchListener() {
            //if onTouch returns true, the button onTouchEvent will not be executed.
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onTouch ACTION_UP");
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.e(TAG, "onTouch ACTION_POINTER_DOWN");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.e(TAG, "onTouch ACTION_POINTER_UP");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "------------------------onClick");
            }
        });
        myButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e(TAG, "-------------------------onLongClick");
                return false;//false means onclicklistener will be executed, true means onclicklistener will not be executed.
            }
        });
    }
}
