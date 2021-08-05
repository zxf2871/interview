package com.b8a3.interview.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class B8a3Thread implements Runnable {
    public static final String TAG = "B8a3Thread";
    private Handler mH;

    @Override
    public void run() {

        Log.i(TAG, "handler start");
        mH = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };
        Looper.prepare();
        Looper.loop();
    }

    public Handler getH(){
        return mH;
    }

}
