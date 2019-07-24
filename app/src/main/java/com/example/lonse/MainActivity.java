package com.example.lonse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonWebview = (Button) findViewById(R.id.bt_webview);
        Button buttonSidebar = (Button) findViewById(R.id.bt_sidebar);
        Button buttonSlideMenu = (Button) findViewById(R.id.bt_slidemenu);
        Button buttonPwChange = (Button) findViewById(R.id.bt_passwordchange);
        Button buttonSet = (Button) findViewById(R.id.bt_set);
        Button buttonLogin = (Button) findViewById(R.id.bt_login);
        Button buttonAccounts = (Button) findViewById(R.id.bt_accouts);
        Button buttonListview = (Button) findViewById(R.id.bt_listview);
//        Button buttonGesturePassword = (Button) findViewById(R.id.bt_slidemenu);
        //webview的使用
        buttonWebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
        //侧栏的使用
        buttonSidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SideBarActivity.class);
                startActivity(intent);
            }
        });

        //侧栏的使用
        buttonSlideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SlideMenuActivity.class);
                startActivity(intent);
            }
        });

        //密码修改界面的使用
        buttonPwChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PasswordChangeActivity.class);
                startActivity(intent);
            }
        });

        //设置界面的使用
        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });

        //切换界面的使用
        buttonAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AccountsActivity.class);
                startActivity(intent);
            }
        });

        //登录界面的使用
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        //登录界面的使用
        buttonListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
        //手势密码界面
//        buttonGesturePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

}
