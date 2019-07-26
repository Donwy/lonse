package com.example.lonse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lonse.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextAccount;
    private EditText editTextPassword;
    private ImageView imageView;
    private ListPopupWindow listPopupWindow;
    private ToggleButton toggleButtonAccount;
    private ToggleButton toggleButtonPassword;
    private List<String> lists = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextAccount = (EditText) findViewById(R.id.login_account);
        editTextPassword = (EditText) findViewById(R.id.login_pw);
        imageView = (ImageView) findViewById(R.id.bt_back);
        toggleButtonAccount = (ToggleButton) findViewById(R.id.login_tb_account);
        toggleButtonPassword = (ToggleButton) findViewById(R.id.login_tb_pw);

        lists.add("one");
        lists.add("two");
        lists.add("three");
        listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lists));
        listPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        listPopupWindow.setAnchorView(editTextAccount);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        listPopupWindow.setModal(true);//设置是否是模式

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                editTextAccount.setText(lists.get(position));
                listPopupWindow.dismiss();
            }
        });

        toggleButtonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPopupWindow.show();
            }
        });

        //原密码显示/隐藏开关
        toggleButtonPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(compoundButton.isChecked()){
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
