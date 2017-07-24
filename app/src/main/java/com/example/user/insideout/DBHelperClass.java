package com.example.user.insideout;

/**
 * Created by user on 16-Jun-17.
 */
import android.content.Context;
import android.database.sqlite.*;

public class DBHelperClass extends SQLiteOpenHelper{

    public DBHelperClass(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
