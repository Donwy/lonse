package com.example.lonse;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AccountsActivity extends AppCompatActivity {

    private ListView lvAccount;

    private ArrayList<AccountEntity> mAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
    }
}
