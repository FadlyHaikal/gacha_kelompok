package com.example.gacha_kelompok;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    ActionBar actionBar;
    public Button btnLogout, btnUpdate;
    DBHelper DB;
    EditText username, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Profile");
        DB = new DBHelper(this);
        String sessionId = getIntent().getStringExtra("sessionId");
        UserModel user = DB.getOne("-1", sessionId);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = (EditText)findViewById(R.id.nama);
        username.setText(user.getUsername());
        email = (EditText)findViewById(R.id.gmail);
        email.setText(user.getEmail());
        phone = (EditText)findViewById(R.id.mobile);
        phone.setText(user.getPhone());

        btnLogout = findViewById(R.id.btnLogout);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String phoneValue = phone.getText().toString();
                String emailValue = email.getText().toString();
                String usernameValue = username.getText().toString();

                UserModel oldUser = DB.getOne("-1", sessionId);

                if(oldUser.getEmail().equals(emailValue) && oldUser.getUsername().equals(usernameValue) && oldUser.getPhone().equals(phoneValue)){
                    return;
                }

                if(phoneValue.equals("")||emailValue.equals("")||usernameValue.equals("")){
                    Toast.makeText(ProfileActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!oldUser.getEmail().equals(emailValue) && !(Patterns.EMAIL_ADDRESS.matcher(emailValue).matches())) {
                    Toast.makeText(ProfileActivity.this, "Email Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!oldUser.getPhone().equals(phoneValue) && (phoneValue.length() < 10 || phoneValue.length() > 15) && android.util.Patterns.PHONE.matcher(phoneValue).matches()){
                    Toast.makeText(ProfileActivity.this, "Phone number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkEmail = DB.checkemail(emailValue);
                if(!oldUser.getEmail().equals(emailValue) && checkEmail){
                    Toast.makeText(ProfileActivity.this, "Email already taken", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkUsername = DB.checkusername(usernameValue);
                if(!oldUser.getUsername().equals(usernameValue) && checkUsername){
                    Toast.makeText(ProfileActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserModel updatedUser = new UserModel(user.getId(), usernameValue, emailValue, phoneValue, user.getPassword());
                Boolean isSuccess = DB.updateOne(updatedUser);
                if(isSuccess) {
                    oldUser = updatedUser;
                    Toast.makeText(ProfileActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }

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
