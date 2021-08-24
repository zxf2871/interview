// BookManager.aidl
package com.b8a3.interview;
import com.b8a3.interview.bean.Book;
// Declare any non-default types here with import statements

interface BookManager {
    void addBook(inout Book book);

    int doAdd(int a, int b);

    Book getBook(String name);
}