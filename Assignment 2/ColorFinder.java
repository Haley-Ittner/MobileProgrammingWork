package com.example.haley.colorblender;

import android.graphics.Color;
import android.util.Log;

/**
 * Created by Haley on 9/20/2016. Wooooo
 */
public class ColorFinder {

        static int red = 0;
        static int green = 0;
        static int blue = 0;
        static String test = "";
        static String hold = "";
        static int hex = 0;
        static final String TAGGER = "My Activity: ";

    public static String kindFinder(int color1, boolean gotColor) {
        Log.i(TAGGER, "Color is " + color1 + " boolean is " + gotColor);
        if (color1 == 0) {
            Log.i(TAGGER, "Got to black block!");
            red = 0;
            green = 0;
            blue = 0;
            return red + " " + green + " " + blue;
        }
        if (color1 == 0xFFFFFF) {
            Log.i(TAGGER, "Got to white block!");
            red = 255;
            green = 255;
            blue = 255;
            return red + " " + green + " " + blue;
        }
        if (gotColor) {
            Log.i(TAGGER, "Got to other block!");
            hold = convertColorToHexadeimal(color1);
            red = Integer.parseInt(hexDecConverter(hold.substring(3, 5)));
            green = Integer.parseInt(hexDecConverter(hold.substring(5, 7)));
            blue = Integer.parseInt(hexDecConverter(hold.substring(7, 9)));
            return red + " " + green + " " + blue;
        }
        Log.i(TAGGER, "Colors are " + red + green + blue);
        return red + " " + green + " " + blue;
    }

    public static String convertColorToHexadeimal(int color)
    {
        String hex = Integer.toHexString(color);
        if(hex.length() < 6)
        {
            if(hex.length()==5)
                hex = "0" + hex;
            if(hex.length()==4)
                hex = "00" + hex;
            if(hex.length()==3)
                hex = "000" + hex;
        }
        hex = "#" + hex;
        return hex;
    }

    protected static int changeColor(int one, int two, int three) {
        hex = Color.rgb(one, two, three);
        return hex;
    }

    protected static double colorFinder(int color1, int color2, double ratio) {
        double hold = (color2 * (ratio/100));
        double holder = (color1 * (1-(ratio/100)));
        return hold + holder;
    }

    public static String hexDecConverter(String start) {
        double endingNumber = 0.0;
        double tmp = 0.0;
        int counter = 0;
        String holder = "";
        if(start.equals("00")) {
            return "0";
        }
        if(start.equals("0")) {
            return start;
        }
        for(int x = start.length() - 1; x > -1; x--) {
            holder += start.substring(x, x + 1).toUpperCase();
            if (holder.equals("A") || holder.equals("B") || holder.equals("C") || holder.equals("D")
                    || holder.equals("E") || holder.equals("F")) {
                holder = letterToNumber(holder);
                tmp = Double.parseDouble(holder);
                endingNumber += tmp * Math.pow(16, x);
                holder = "";
                counter++;
            } else {
                tmp = Double.parseDouble(holder);
                endingNumber += tmp * Math.pow(16, x);
                holder = "";
                counter++;
            }
        }
        return (int) endingNumber + "";
    }

    public static String letterToNumber(String start) {
        String returnValue = "";
        if (start.equals("A")) {
            return 10 + "";
        } else if (start.equals("B")) {
            return 11 + "";
        } else if (start.equals("C")) {
            return 12 + "";
        } else if (start.equals("D")) {
            return 13 + "";
        } else if (start.equals("E")) {
            return 14 + "";
        } else if (start.equals("F")) {
            return 15 + "";
        }
        return "";
    }

    public static String randomColorFinder(int one, int two, int three) {
            Log.i(TAGGER, "Got to pushed block!");
            red = one;
            green = two;
            blue = three;
            return red + " " + green + " " + blue;
    }
}
