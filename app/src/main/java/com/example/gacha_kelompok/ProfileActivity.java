package com.example.gacha_kelompok;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    ActionBar actionBar;
    public Button btnLogout;
    DBHelper DB;
    TextView username, password, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Profile");
        DB = new DBHelper(this);
        String sessionUsername = getIntent().getStringExtra("username");
        List <UserModel> users = DB.getOne(sessionUsername);
        UserModel user = users.get(0);
//        Toast.makeText(this, sessionUsername, Toast.LENGTH_SHORT).show();
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = (TextView)findViewById(R.id.nama);
        username.setText(user.getUsername());
        email = (TextView)findViewById(R.id.gmail);
        email.setText(user.getEmail());
        phone = (TextView)findViewById(R.id.mobile);
        phone.setText(user.getPhone());

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
