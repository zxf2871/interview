package com.b8a3.interview.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class B8a3Thread extends Thread {
    public static final String TAG = "B8a3Thread";
    private Handler mH;
    private Looper mLooper;

    @Override
    public void run() {
        Log.i(TAG, "handler start");
        Looper.prepare();
        mLooper = Looper.myLooper();
        mH = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };
        Looper.loop();
    }

    public Handler getH() {
        return mH;
    }

    public Looper getLooper() {
        if (mLooper == null) {
            throw new RuntimeException("looper 未初始化");
        }
        return mLooper;
    }

    public void cancel() {
        if (mH == null) {
            return;
        }
        mH.getLooper().quitSafely();
    }
}
