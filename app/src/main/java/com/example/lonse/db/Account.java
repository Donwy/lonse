package com.example.lonse.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Donvy_y on 2019/8/2
 */
public class Account implements DatabaseHelper.TableCreateInterface {



    private static Account account = new Account();

    public static Account getInstance() {
        return Account.account;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
}
