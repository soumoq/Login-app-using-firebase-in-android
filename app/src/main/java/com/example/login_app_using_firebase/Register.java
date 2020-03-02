package com.example.login_app_using_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText register_name;
    private EditText register_email;
    private EditText register_password;
    private EditText register_retype_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_name=(EditText)findViewById(R.id.register_name);
        register_email=(EditText)findViewById(R.id.register_email);
        register_password=(EditText)findViewById(R.id.register_password);
        register_retype_password=(EditText)findViewById(R.id.register_retype_password);

    }

    public void register(View view)
    {
        String name=register_name.getText().toString();
        String email=register_email.getText().toString();
        String password=register_password.getText().toString();
        String retype_password=register_retype_password.getText().toString();
        if(verify(name,email,password,retype_password))
        {
            Intent intent=new Intent(Register.this,MainActivity.class);
        }

    }


    private boolean verify(String name, String email, String password, String retype_password)
    {
        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || retype_password.isEmpty())
        {
            Toast.makeText(this,"The text fild are empty",Toast.LENGTH_LONG).show();
        }
        else if(!password.equals(retype_password))
        {
            Toast.makeText(this,"password misematch",Toast.LENGTH_LONG).show();
        }
        else
        {
            return true;
        }
        return false;
    }

    public void go_login()
    {
        Intent intent=new Intent(Register.this,MainActivity.class);
        startActivity(intent);
    }
}
