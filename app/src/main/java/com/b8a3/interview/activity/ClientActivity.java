package com.b8a3.interview.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.b8a3.core.log.LogUtil;
import com.b8a3.interview.R;
import com.b8a3.interview.RemoteService;
import com.b8a3.interview.base.BaseActivity;
import com.b8a3.interview.binder.Stub;
import com.b8a3.interview.binder.server.BookManager;

import androidx.annotation.Nullable;

public class ClientActivity extends BaseActivity {

    public final String TAG = "ClientActivity";

    private boolean isConnected = false;
    private BookManager mBookManager;

    /**
     * 链接remote service
     */
    final private ServiceConnection mRemoteService = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.i(TAG, "service connected");
            isConnected = true;
            mBookManager = Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binder_client_layout);
        startRemoteService();
    }

    public void startRemoteService() {
        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("com.b8a3.interview.REMOTE");
        bindService(intent, mRemoteService, Context.BIND_AUTO_CREATE);
    }

    public void doAdd(View view) {
        if (isConnected) {
            int r =  mBookManager.doAdd(10, 20);
            LogUtil.e(TAG, "do add, result is " + r);

        }else {
            LogUtil.e(TAG, "未链接service");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mRemoteService);
    }
}
