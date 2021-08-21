package com.b8a3.interview.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.b8a3.core.log.LogUtil;
import com.b8a3.interview.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {

    protected String getTag() {
        return this.getClass().getSimpleName()+" ";
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i(getTag(), "onRestart");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(getTag(), "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(getTag(), "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(getTag(), "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(getTag(), "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(getTag(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(getTag(), "onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.i(getTag(), "onNewIntent");

    }


}
