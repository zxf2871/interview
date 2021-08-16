package com.b8a3.interview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.b8a3.interview.R;
import com.b8a3.interview.RemoteService;
import com.b8a3.interview.base.BaseActivity;

import androidx.annotation.Nullable;

public class ClientActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binder_client_layout);
    }

    public void startRemoteService(View view) {
        startService(new Intent(this, RemoteService.class));
    }
}
