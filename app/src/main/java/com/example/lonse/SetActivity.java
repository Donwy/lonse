package com.example.lonse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SetActivity extends AppCompatActivity {
    ImageView setBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        setBack = (ImageView) findViewById(R.id.set_back);

        setBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
