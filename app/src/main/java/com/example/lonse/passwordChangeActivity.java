package com.example.lonse;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class passwordChangeActivity extends AppCompatActivity{
    private static final String TAG = "passwordChangeActivity";
    ToggleButton toggleButtonOld;
    EditText pwOld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw);

        ImageView back = (ImageView) findViewById(R.id.bt_back);
        toggleButtonOld = (ToggleButton) findViewById(R.id.tb_old);
        pwOld = (EditText) findViewById(R.id.pw_old);


        //返回主界面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(passwordChangeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //原密码显示/隐藏开关
        toggleButtonOld.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(compoundButton.isChecked()){
                    pwOld.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else{
                    pwOld.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //EditText获取光标，修改toggleButton的颜色
        pwOld.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View view, boolean hasFocur) {

                if(hasFocur){
                    toggleButtonOld.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
                }else{
                    toggleButtonOld.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9b9b9b")));
                }
            }
        });

    }
}
