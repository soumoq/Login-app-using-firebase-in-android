package com.example.login_app_using_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    TextView user_info_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user_info_email=(TextView)findViewById(R.id.user_info_email);

        String email=getIntent().getStringExtra("email");
        user_info_email.setText("Hello "+email);


    }
}
