package com.example.user.insideout;

/**
 * Created by user on 16-Jun-17.
 */
import android.content.Context;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper{


    public DBHelper(Context context) {
        super(context, "user", null, 1);
    }

    /* public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "user", null, 1);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT," +
                "username TEXT," + " password TEXT)");

        PRE_DEFINED_VALUES(db);

    }

    // pre defined values insertion
    private void PRE_DEFINED_VALUES(SQLiteDatabase db) {

        db.execSQL("INSERT INTO user(name, username, password) VALUES ('user', 'user', '1234')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);

    }
}
