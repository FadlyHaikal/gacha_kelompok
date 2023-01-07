package com.example.gacha_kelompok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    public Button btnDisplayAll;
    public Button btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnRandom = findViewById(R.id.btnRandom);
        btnDisplayAll = findViewById(R.id.btnDisplayAll);

        btnRandom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, RandomItemActivity.class);
                startActivity(intent);
            }
        });

        btnDisplayAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, DisplayAllItemActivity.class);
                startActivity(intent);
            }
        });
    }
    }