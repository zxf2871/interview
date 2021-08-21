package com.b8a3.interview.binder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.b8a3.core.log.LogUtil;
import com.b8a3.interview.BaseApplication;
import com.b8a3.interview.bean.Book;
import com.b8a3.interview.binder.server.BookManager;

import static com.b8a3.interview.binder.Stub.DESCRIPTOR;

public class BookProxy implements BookManager {

    public static final String TAG = "BookProxy";
    IBinder remote;

    @Override
    public void addBook(Book book) {
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            if (book != null) {
                data.writeInt(1);
                book.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            remote.transact(Stub.TRANSAVTION_addBook, data, replay, 0);
            replay.readException();
        } catch (Exception e) {
        } finally {
            replay.recycle();
            data.recycle();
        }
    }

    @Override
    public int doAdd(int a, int b) {
        LogUtil.i(TAG, BaseApplication.getCurrentProcessName() + " doAdd");
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();
        int result = 0;

        try {
            data.writeInt(a);
            data.writeInt(b);
            remote.transact(Stub.TRANSAVTION_doAdd, data, replay, 0);
            result = replay.readInt();
            replay.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            replay.recycle();
            data.recycle();
        }
        return result;
    }

    @Override
    public IBinder asBinder() {
        return remote;
    }

    public BookProxy(IBinder binder) {
        this.remote = binder;
    }
}
