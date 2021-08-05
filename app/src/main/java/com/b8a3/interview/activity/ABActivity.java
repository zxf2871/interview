package com.b8a3.interview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.b8a3.interview.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public abstract class  ABActivity extends FragmentActivity {


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(getTag(), "onRestart");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity);
        ((TextView)findViewById(R.id.text)).setText(getTag());
        Log.i(getTag(), "onCreate");

    }

    protected abstract String getTag();

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

    public abstract void startAnother(View view);
}

/*

AB两个Activity生命周期
start A -> start B -> backpress
| A: onCreate
| A: onStart
| A: onResume

| A: onPause
| B: onCreate
| B: onStart
| B: onResume
| A: onStop

| B: onPause
| A: onRestart
| A: onStart
| A: onResume
| B: onStop
| B: onDestroy

*/
