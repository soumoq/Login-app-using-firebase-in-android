package com.example.login_app_using_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText login_email;
    private EditText login_password;
    private TextView click_register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        click_register = (TextView) findViewById(R.id.click_register);

        if(firebaseAuth.getCurrentUser() != null)
        {
            Intent intent=new Intent(MainActivity.this,Home.class);
            startActivity(intent);
        }


    }

    public void login(View view) {
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();

       login_validation(email,password);
    }

    private void login_validation(final String email, String password)
    {

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,Home.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void go_register(View view) {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
        finish();
    }
}