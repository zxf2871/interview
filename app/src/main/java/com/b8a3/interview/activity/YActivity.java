package com.b8a3.interview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.b8a3.interview.R;

import androidx.annotation.Nullable;

public class YActivity extends ABActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TextView)findViewById(R.id.button)).setText("启动B");
    }

    @Override
    protected String getTag() {
        return "------> Y";
    }

    @Override
    public void startAnother(View view) {
        startActivity(new Intent(this, BActivity.class));

    }
}
