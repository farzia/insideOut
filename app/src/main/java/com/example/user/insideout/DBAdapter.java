package com.example.user.insideout;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 27-Jul-17.
 */

public class DBAdapter {

    private Context context;
    private DBHelper dbHelper;
    public SQLiteDatabase db;

    // set Context to access the database
    public DBAdapter(Context context) {
        this.context = context;
    }

    // open the database
    public DBAdapter open() throws SQLException {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // close the database
    public void close() {
        dbHelper.close();
    }

    //check user
    public boolean check(String userName, String pass) {
        Cursor cursor = db.rawQuery("SELECT name FROM user WHERE password='" + pass + "' AND username='" + userName + "'", null);
        if (cursor.getCount() > 0)
            return true;

        return false;
    }




}
