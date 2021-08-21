package com.b8a3.interview.binder.server;

import com.b8a3.core.log.LogUtil;
import com.b8a3.interview.bean.Book;
import com.b8a3.interview.binder.Stub;

public class RemoteBookManager extends Stub {
    public static final String TAG = "RemoteBookManager";

    private static RemoteBookManager sInstance;

    private RemoteBookManager(){
        LogUtil.i(TAG, "RemoteBookManager init");

    }

    public static RemoteBookManager getInstance() {
        if (sInstance == null) {
            synchronized (RemoteBookManager.class) {
                if (sInstance == null) {
                    sInstance = new RemoteBookManager();
                }
            }
        }

        return sInstance;
    }

    @Override
    public void addBook(Book book) {
        LogUtil.i(TAG, "addBook:" + book);
    }

    @Override
    public int doAdd(int a, int b) {
        LogUtil.i(TAG, "doAdd " + a + " + " + b);

        return a + b;
    }
}
