package com.example.haley.tryagain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.NumberPicker;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    NumberPicker pick = null;
    NumberPicker pick2 = null;
    NumberPicker pick3 = null;
    private static final String TAG = "My Activity";
    int val1 = 0;
    int val2 = 0;
    int val3 = 0;
    SurfaceView color = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pick = (NumberPicker) findViewById(R.id.pickNumber1);
        pick.setMaxValue(255);
        pick.setMinValue(0);
        pick.setWrapSelectorWheel(true);

        pick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.i(TAG, "Number of pickNumber1 changed value.");
                val1 = newVal;
                Log.i(TAG, "The currant value of val is " + val1);
                changeColor(val1, val2, val3);
            }
        });

        pick2 = (NumberPicker) findViewById(R.id.pickNumber2);
        pick2.setMaxValue(255);
        pick2.setMinValue(0);
        pick2.setWrapSelectorWheel(true);

        pick2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.i(TAG, "Number of pickNumber2 changed value.");
                val2 = newVal;
                Log.i(TAG, "The currant value of val is " + val2);
                changeColor(val1, val2, val3);
            }
        });

        pick3 = (NumberPicker) findViewById(R.id.pickNumber3);
        pick3.setMaxValue(255);
        pick3.setMinValue(0);
        pick3.setWrapSelectorWheel(true);

        pick3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.i(TAG, "Number of pickNumber3 changed value.");
                val3 = newVal;
                Log.i(TAG, "The currant value of val is " + val3);
                changeColor(val1, val2, val3);
            }
        });
    }
        protected void changeColor(int one, int two, int three) {
            Log.i(TAG, "Got here!");
            int hex = Color.rgb(one, two, three);
            color = (SurfaceView) findViewById(R.id.colorViewer);
            color.setBackgroundColor(hex);
        }
}

