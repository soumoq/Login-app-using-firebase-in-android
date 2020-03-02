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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText register_name;
    private EditText register_email;
    private EditText register_password;
    private EditText register_retype_password;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("register");
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

    public void go_login(View view) {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void register_user(final String name, final String email, final String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    Toast.makeText(Register.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    storeData(name, email, password);
                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    goToHome();
                }
            }
        });

    }

    public void goToHome() {
        Intent intent = new Intent(Register.this, Home.class);
        startActivity(intent);

    }

    public void storeData(String name, String email, String password) {
        String id = databaseReference.push().getKey();
        Get_Register_Data get_register_data = new Get_Register_Data(id, name, email, password);
        databaseReference.push().setValue(get_register_data);
    }
}

class Get_Register_Data {
    private String id;
    private String name;
    private String email;
    private String password;

    public Get_Register_Data() {
    }

    public Get_Register_Data(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}