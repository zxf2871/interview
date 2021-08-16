package com.b8a3.interview.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
        Log.i(getTag(), "onRestart");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(getTag(), "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(getTag(), "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(getTag(), "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(getTag(), "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getTag(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(getTag(), "onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(getTag(), "onNewIntent");

    }


}
