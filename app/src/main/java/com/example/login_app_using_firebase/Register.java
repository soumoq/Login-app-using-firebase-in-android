package com.example.login_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText register_name;
    private EditText register_email;
    private EditText register_password;
    private EditText register_retype_password;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        register_name = (EditText) findViewById(R.id.register_name);
        register_email = (EditText) findViewById(R.id.register_email);
        register_password = (EditText) findViewById(R.id.register_password);
        register_retype_password = (EditText) findViewById(R.id.register_retype_password);

    }

    public void register(View view) {
        String name = register_name.getText().toString();
        String email = register_email.getText().toString();
        String password = register_password.getText().toString();
        String retype_password = register_retype_password.getText().toString();
        if (verify(name, email, password, retype_password)) {
            register_user(name, email, password);
            Intent intent = new Intent(Register.this, MainActivity.class);
            //startActivity(intent);
        }

    }


    private boolean verify(String name, String email, String password, String retype_password) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || retype_password.isEmpty()) {
            Toast.makeText(this, "Text field should not be empty", Toast.LENGTH_LONG).show();
        } else if (!password.equals(retype_password)) {
            Toast.makeText(this, "password mismatch", Toast.LENGTH_LONG).show();
        } else {
            return true;
        }
        return false;
    }

    public void go_login() {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }

    public void register_user(String name, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registration complete", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
