package com.example.haley.colorblender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    public SurfaceView firstColor = null;
    public SurfaceView secondColor = null;
    public SurfaceView colorBlend = null;
    public TextView textOne = null;
    public TextView textTwo = null;
    public SeekBar seeker = null;
    public Button magic = null;
    public final int CONTROL = 666;
    public final int CHAOS = 777;
    public int redTotal = 0;
    public int greenTotal = 0;
    public int blueTotal = 0;
    public int color = 0x000000;
    public int color2 = 0xFFFFFF;
    int fade = 0;
    public String test = "";
    public String hold = "";
    public int hex = 0;
    public boolean gotColor1 = false;
    public boolean gotColor2 = false;
    public StringTokenizer token;
    public StringTokenizer smoken;
    public Random random = new Random();
    public boolean getLucky = false;
    public int ran;
    public int dom;
    public int ness;
    public int fus;
    public int ro;
    public int dah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        magic = (Button) findViewById(R.id.magicButton);
        textOne = (TextView) findViewById(R.id.firstColorText);
        textTwo = (TextView) findViewById(R.id.secondColorText);
        firstColor = (SurfaceView) findViewById(R.id.firstColor);
        secondColor = (SurfaceView) findViewById(R.id.secondColor);
        colorBlend = (SurfaceView) findViewById(R.id.blendedColor);
        colorBlend.setBackgroundColor(color);
        seeker = (SeekBar) findViewById(R.id.seeker);
        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                fade = seeker.getProgress();
                if(!getLucky) {
                    Log.i("Poop", "getLucky == false");
                    hold = ColorFinder.kindFinder(color, gotColor1);
                    test = ColorFinder.kindFinder(color2, gotColor2);
                    token = new StringTokenizer(hold);
                    smoken = new StringTokenizer(test);
                    redTotal = (int) ColorFinder.colorFinder(Integer.parseInt(token.nextToken())
                            , Integer.parseInt(smoken.nextToken()), fade);
                    greenTotal = (int) ColorFinder.colorFinder(Integer.parseInt(token.nextToken()),
                            Integer.parseInt(smoken.nextToken()), fade);
                    blueTotal = (int) ColorFinder.colorFinder((Integer.parseInt(token.nextToken()))
                            , Integer.parseInt(smoken.nextToken()), fade);
                    hex = ColorFinder.changeColor(redTotal, greenTotal, blueTotal);
                    colorBlend.setBackgroundColor(hex);
                } else if(getLucky && gotColor2){
                    Log.i("Poop", "getLuck == true and gotColor2 ==true");
                    hold = ColorFinder.randomColorFinder(ran, dom, ness);
                    test = ColorFinder.kindFinder(color2, gotColor2);
                    token = new StringTokenizer(hold);
                    smoken = new StringTokenizer(test);
                    redTotal = (int) ColorFinder.colorFinder(Integer.parseInt(token.nextToken())
                            , Integer.parseInt(smoken.nextToken()), fade);
                    greenTotal = (int) ColorFinder.colorFinder(Integer.parseInt(token.nextToken()),
                            Integer.parseInt(smoken.nextToken()), fade);
                    blueTotal = (int) ColorFinder.colorFinder((Integer.parseInt(token.nextToken()))
                            , Integer.parseInt(smoken.nextToken()), fade);
                    hex = ColorFinder.changeColor(redTotal, greenTotal, blueTotal);
                    colorBlend.setBackgroundColor(hex);
                } else if(getLucky && gotColor1) {
                    Log.i("Poop", "getLucky == true and gotColor1 == true");
                    hold = ColorFinder.kindFinder(color, gotColor1);
                    test = ColorFinder.randomColorFinder(fus, ro, dah);
                    token = new StringTokenizer(hold);
                    smoken = new StringTokenizer(test);
                    redTotal = (int) ColorFinder.colorFinder(Integer.parseInt(token.nextToken())
                            , Integer.parseInt(smoken.nextToken()), fade);
                    greenTotal = (int) ColorFinder.colorFinder(Integer.parseInt(token.nextToken()),
                            Integer.parseInt(smoken.nextToken()), fade);
                    blueTotal = (int) ColorFinder.colorFinder((Integer.parseInt(token.nextToken()))
                            , Integer.parseInt(smoken.nextToken()), fade);
                    hex = ColorFinder.changeColor(redTotal, greenTotal, blueTotal);
                    colorBlend.setBackgroundColor(hex);
                } else {
                    Log.i("Poop", "getLucky == true and nothing else");
                    hold = ColorFinder.randomColorFinder(ran, dom, ness);
                    test = ColorFinder.randomColorFinder(fus, ro, dah);
                    token = new StringTokenizer(hold);
                    smoken = new StringTokenizer(test);
                    redTotal = (int) ColorFinder.colorFinder(Integer.parseInt(token.nextToken())
                            , Integer.parseInt(smoken.nextToken()), fade);
                    greenTotal = (int) ColorFinder.colorFinder(Integer.parseInt(token.nextToken()),
                            Integer.parseInt(smoken.nextToken()), fade);
                    blueTotal = (int) ColorFinder.colorFinder((Integer.parseInt(token.nextToken()))
                            , Integer.parseInt(smoken.nextToken()), fade);
                    hex = ColorFinder.changeColor(redTotal, greenTotal, blueTotal);
                    colorBlend.setBackgroundColor(hex);
                }
                if(gotColor1&& gotColor2) {
                    getLucky = false;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


        firstColor.setOnClickListener(firstColorListener);
        secondColor.setOnClickListener(secondColorListener);
        magic.setOnClickListener(magicListener);
    }

    private OnClickListener firstColorListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivityForResult(new Intent("com.example.haley.colorpickeragain.ACTION_POO"), CONTROL);
        }
    };

    private OnClickListener secondColorListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivityForResult(new Intent("com.example.haley.colorpickeragain.ACTION_POO"), CHAOS);
        }
    };

    private OnClickListener magicListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            getLucky = true;
            ran = random.nextInt(256);
            dom = random.nextInt(256);
            ness = random.nextInt(256);
            Log.i("CHECKING: ", "Values: " + ran + " " + dom + " " + ness);
            int find = ColorFinder.changeColor(ran, dom, ness);
            firstColor.setBackgroundColor(find);
            textOne.setTextColor(find);
            colorBlend.setBackgroundColor(find);
            fus = random.nextInt(256);
            ro = random.nextInt(256);
            dah = random.nextInt(256);
            int check = ColorFinder.changeColor(fus, ro, dah);
            secondColor.setBackgroundColor(check);
            textTwo.setTextColor(check);
            color = find;
            color2 = check;
            Log.i("TAG:", "Color 1: " + color + " Color 2: " + color2);
            gotColor2 = false;
            gotColor1 = false;
        }
    };



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTROL) {
            if (resultCode == RESULT_OK) {
                color = data.getIntExtra("color", 0);
                firstColor = (SurfaceView) findViewById(R.id.firstColor);
                firstColor.setBackgroundColor(color);
                colorBlend = (SurfaceView) findViewById(R.id.blendedColor);
                colorBlend.setBackgroundColor(color);
                textOne = (TextView) findViewById(R.id.firstColorText);
                textOne.setTextColor(color);
                gotColor1 = true;
            }
        }
        if (requestCode == CHAOS) {
            color2 = data.getIntExtra("color", 0);
            secondColor = (SurfaceView) findViewById(R.id.secondColor);
            secondColor.setBackgroundColor(color2);
            textTwo = (TextView) findViewById(R.id.secondColorText);
            textTwo.setTextColor(color2);
            gotColor2 = true;
        }
    }
    // Got this code from Stack overflow but modified the parameter to fit my code
}
