package com.example.lonse.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lonse.AccountEntity;
import com.example.lonse.R;

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
