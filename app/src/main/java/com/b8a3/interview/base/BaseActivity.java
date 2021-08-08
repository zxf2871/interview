package com.b8a3.interview.base;

import androidx.fragment.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {

    protected String getTag() {
        return this.getClass().getSimpleName()+" ";
    }
}
