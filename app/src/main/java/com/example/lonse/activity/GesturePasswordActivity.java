package com.example.lonse.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lonse.R;
import com.example.lonse.view.GesturePassword;

/**
 * @author Donvy_y
 * @date 2019/8/16
 */
public class GesturePasswordActivity extends AppCompatActivity {
    GesturePassword gesturePassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_password);

    }
}
