package com.b8a3.interview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.b8a3.interview.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AActivity extends ABActivity{

    View view;

    float i =0;
    float j =0;

    float x=1;
    float y=1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TextView)findViewById(R.id.button)).setText("启动B");
        view = findViewById(R.id.black);
        x = savedInstanceState.getFloat("x");
        y = savedInstanceState.getFloat("y");
        view.animate().scaleX(x).scaleY(y);
    }

    @Override
    protected String getTag() {
        return "------> A";
    }

    @Override
    public void startAnother(View view) {
        startActivity(new Intent(this, BActivity.class));

    }

    @Override
    public void moveBlack(View veiw){
        view.setX(i+=10f);
        view.setY(j+=20f);
//        view.animate().scaleX(x++).scaleX(y++);

        view.setPivotX(0f);
        view.setPivotY(0f);
        view.animate().scaleX(++x).scaleY(++y);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("x", x);
        outState.putFloat("y", y);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        x = savedInstanceState.getFloat("x");
        y = savedInstanceState.getFloat("y");
        view.animate().scaleX(x).scaleY(y);

    }
}
