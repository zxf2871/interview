package com.b8a3.interview.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.b8a3.interview.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        printDe();
        printDe1();
        getScreenSizeOfDevice();
        getScreenSizeOfDevice2();
        test();

        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                return false;
            }
        });
    }

    private void test() {
        String a = new String("abc");
        String b = new String("abc");
        ;
        Log.e("------>", "a==b:" + (a == b));
        String as = "abs";
        String bs = "abs";
        Log.e("------>", "as==bs:" + (as == bs));
        changeA(a);
        Log.e("------>", "a:" + a);
        Integer ai = 127;
        Integer bi = 127;
        Log.e("------>", "ai==bi:" + (ai == bi));
        Integer ai1 = new Integer(1);
        Integer bi1 = new Integer(1);
        Log.e("------>", "ai1==bi1:" + (ai1 == bi1));
        changeAi(ai1);
        Log.e("------>", "ai1:" + ai1 + null);
    }

    private void changeA(String a) {
        a.toUpperCase();

        a = null;
    }

    private void changeAi(Integer ai) {
        ai = new Integer(2);
    }

    private void printDe() {
        float density = getResources().getDisplayMetrics().density;
        int densityDpi = getResources().getDisplayMetrics().densityDpi;
        int w = getResources().getDisplayMetrics().widthPixels;
        int h = getResources().getDisplayMetrics().heightPixels;
        Log.e("------>", "density:" + density + " densityDpi:"
                + densityDpi + " w:" + w + " h:" + h);
    }

    private void printDe1() {
        WindowManager windowManager = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);

        Log.e("------>", "density:" + displayMetrics.density + " densityDpi:"
                + displayMetrics.densityDpi + " w:" + displayMetrics.widthPixels + " h:" + displayMetrics.heightPixels + " 屏幕dp:" + (1080 / displayMetrics.density));
    }

    private void getScreenSizeOfDevice() {
        DisplayMetrics dm = getResources().getDisplayMetrics();

        WindowManager windowManager = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        double x = Math.pow(width, 2);
        double y = Math.pow(height, 2);
        double diagonal = Math.sqrt(x + y);

        int dens = dm.densityDpi;
        double screenInches = diagonal / (double) dens;
        Log.d("------>", "The screenInches " + screenInches);
    }

    private void getScreenSizeOfDevice2() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(point);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double x = Math.pow(point.x / dm.xdpi, 2);
        double y = Math.pow(point.y / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        Log.d("------>", "Screen inches : " + screenInches);
    }

    public void startA(View view) {
        startActivity(new Intent(this, AActivity.class));
    }

    public void startPost(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("------>", "new Handler().post: ");
            }
        }, 100000);
    }

    public void startView(View view) {
        startActivity(new Intent(this, ViewVisibilityActivity.class));
    }
}
