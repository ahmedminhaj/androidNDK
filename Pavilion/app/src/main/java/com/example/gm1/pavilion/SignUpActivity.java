package com.example.gm1.pavilion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextEmail;
    Button mButtonSignup;
    TextView mTextViewSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextEmail = (EditText)findViewById(R.id.edittext_email);
        mButtonSignup = (Button)findViewById(R.id.button_singup);
        mTextViewSignin = (TextView)findViewById(R.id.textview_signin);
        mTextViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(signInIntent);
            }
        });
        mButtonSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent nowSignInIntent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(nowSignInIntent);
            }
        });
    }
}


