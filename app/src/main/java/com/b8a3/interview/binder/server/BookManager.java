package com.b8a3.interview.binder.server;

import android.os.IInterface;

import com.b8a3.interview.bean.Book;

public interface BookManager extends IInterface {
    void addBook(Book book);
    int doAdd(int a, int b);
}
