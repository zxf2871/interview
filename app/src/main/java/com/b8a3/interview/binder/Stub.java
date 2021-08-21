package com.b8a3.interview.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.b8a3.interview.bean.Book;
import com.b8a3.interview.binder.BookProxy;
import com.b8a3.interview.binder.server.BookManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class Stub extends Binder implements BookManager {

    public static final String DESCRIPTOR = "add_book";
    public static final int TRANSAVTION_addBook = IBinder.FIRST_CALL_TRANSACTION;
    public static final int TRANSAVTION_doAdd = IBinder.FIRST_CALL_TRANSACTION + 1;

    @Override
    public IBinder asBinder() {
        return this;
    }

    public static BookManager asInterface(IBinder binder) {
        if (binder == null) {
            return null;
        }
        return new BookProxy(binder);
    }


    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {

            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;

            case TRANSAVTION_addBook:
                data.enforceInterface(DESCRIPTOR);
                Book arg0 = null;
                if (data.readInt() != 0) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                }
                this.addBook(arg0);
                reply.writeNoException();
                return true;
            case TRANSAVTION_doAdd:
                int a = data.readInt();
                int b = data.readInt();
                int r = this.doAdd(a,b);
                reply.writeInt(r);

        }

        return super.onTransact(code, data, reply, flags);
    }
}
