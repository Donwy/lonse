package com.example.lonse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lonse.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonWebView = (Button) findViewById(R.id.bt_webView);
        Button buttonSidebar = (Button) findViewById(R.id.bt_sidebar);
        Button buttonSlideMenu = (Button) findViewById(R.id.bt_slideMenu);
        Button buttonPwChange = (Button) findViewById(R.id.bt_passwordChange);
        Button buttonSet = (Button) findViewById(R.id.bt_set);
        Button buttonLogin = (Button) findViewById(R.id.bt_login);
//        Button buttonAccounts = (Button) findViewById(R.id.bt_accouts);
        Button buttonListView = (Button) findViewById(R.id.bt_listView);
        Button buttonRecyclerView = (Button) findViewById(R.id.bt_recycleView);
        Button buttonSelectorListView = (Button) findViewById(R.id.bt_selectorListView);
        Button buttonSelectorRecyclerView = (Button) findViewById(R.id.bt_selectorRecycleView);
        Button buttonViewPage = (Button) findViewById(R.id.bt_viewPage);

        //webview的使用
        buttonWebView.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(MainActivity.this, SlideMainActivity.class);
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

//        //切换界面的使用
//        buttonAccounts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AccountsActivity.class);
//                startActivity(intent);
//            }
//        });

        //登录界面的使用
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        //listView界面的使用
        buttonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
        //RecyclerView界面的使用
        buttonRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        //selectorListView界面的使用
        buttonSelectorListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectorListViewActivity.class);
                startActivity(intent);
            }
        });
        //selectorRecyclerView界面的使用
        buttonSelectorRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectorRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        //viewPage界面的使用
        buttonViewPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BottomNavigationViewActivity.class);
                startActivity(intent);
            }
        });





    }

}
