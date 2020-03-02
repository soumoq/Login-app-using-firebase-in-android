package com.example.login_app_using_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText login_email;
    private EditText login_password;
    private TextView click_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        click_register = (TextView) findViewById(R.id.click_register);
    }

    public void login(View view) {
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();

        if (email.equals("soumo") && password.equals("123")) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    public void go_register(View view) {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
        finish();
    }
}