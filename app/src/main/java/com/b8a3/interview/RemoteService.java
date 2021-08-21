package com.b8a3.interview;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;

import com.b8a3.core.log.LogUtil;
import com.b8a3.interview.binder.server.RemoteBookManager;
import com.b8a3.interview.handler.B8a3Thread;

import androidx.annotation.Nullable;

public class RemoteService extends Service {

    public static String TAG = "RemoteService";

    private B8a3Thread mThread;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate");
        mThread = new B8a3Thread();
        if (!mThread.isAlive()) {
            mThread.start();
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i(TAG, "onStartCommand");
        new Handler(mThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                LogUtil.i(TAG, "flags" + flags + " intent:" + intent);
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy");
        mThread.cancel();
        mThread.interrupt();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.i(TAG, "onUnbind");

        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        LogUtil.i(TAG, "onRebind");

    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.i(TAG, "onBind ");
        return RemoteBookManager.getInstance();
    }
}
