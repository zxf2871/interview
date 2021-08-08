package com.b8a3.interview.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.b8a3.interview.R;
import com.b8a3.interview.base.BaseActivity;

import androidx.annotation.Nullable;

public class ViewVisibilityActivity extends BaseActivity {

    private View view;
    private ScrollView scrollView;
    private TextView showView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_visibility);
        view = findViewById(R.id.view);
        scrollView = findViewById(R.id.scroll_view);
        showView = findViewById(R.id.show_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getRec() {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(getTag(), "---------left----------top------------right---------------bottom-----------");
                Rect rect = new Rect();
                scrollView.getDrawingRect(rect);
                Log.e(getTag() + "getDrawingRect", getRectString(scrollView, rect));
//                view.getLocalVisibleRect(rect);
                Log.e(getTag() + "getLocalVisibleRect", view.getLocalVisibleRect(rect) + getRectString(view, rect));

                Log.e(getTag() + "getGlobalVisibleRect", view.getGlobalVisibleRect(rect) + getRectString(view, rect));
                view.getHitRect(rect);
                Log.e(getTag() + "getHitRect", getRectString(view, rect));
            }
        }, 100);
    }

    public void showParams(View view) {
        getRec();
    }

    private String getRectString(View view, Rect rect) {
        return rect+ " 显示百分比:" + (rect.top >= 0 && rect.bottom <= view.getHeight()) + (rect.height() * 100 / view.getHeight());
    }
}
