package com.example.lonse.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Donvy_y on 2019/7/30
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NASE = "lonse.db";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static interface TableCreateInterface{
        void onCreate(SQLiteDatabase sqLiteDatabase);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Account.getInstance().onCreate(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
