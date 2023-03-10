package com.example.gacha_kelompok;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, phone, email, repassword;
    Button signup, signin;
    DBHelper DB;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String phonevalue = phone.getText().toString();
                String emailvalue = email.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")||phonevalue.equals("")||emailvalue.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(user.length() < 5){
                    Toast.makeText(RegisterActivity.this, "Username Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!(Patterns.EMAIL_ADDRESS.matcher(emailvalue).matches())) {
                    Toast.makeText(RegisterActivity.this, "Email Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ((phonevalue.length() < 10 || phonevalue.length() > 15) && android.util.Patterns.PHONE.matcher(phonevalue).matches()){
                    Toast.makeText(RegisterActivity.this, "Phone Number Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pass.length() < 5){
                    Toast.makeText(RegisterActivity.this, "Password Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!pass.equals(repass)){
                    Toast.makeText(RegisterActivity.this, "Passwords not Match", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkuser = DB.checkusername(user);
                Boolean checkemail = DB.checkemail(emailvalue);

                if(checkuser==true){
                    Toast.makeText(RegisterActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(checkemail==true){
                    Toast.makeText(RegisterActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                    return;
                }

                UserModel userModel = new UserModel(-1, user, emailvalue, phonevalue, pass);
                Boolean insert = DB.addOne(userModel);
                if(insert==true){
                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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