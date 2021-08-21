package com.b8a3.interview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.b8a3.core.log.LogUtil;
import com.b8a3.interview.R;
import com.b8a3.interview.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public abstract class  ABActivity extends BaseActivity {


    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i(getTag(), "onRestart");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity);
        ((TextView)findViewById(R.id.text)).setText(getTag());
        LogUtil.i(getTag(), "onCreate");

    }

    protected abstract String getTag();

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

    public abstract void startAnother(View view);

    public void moveBlack(View view) {

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.i(getTag(), "onSaveInstanceState");

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtil.i(getTag(), "onRestoreInstanceState");
    }
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
