package com.example.gm1.pavilion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonSignin;
    TextView mTextViewSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonSignin = (Button)findViewById(R.id.button_singin);
        mTextViewSignup = (TextView)findViewById(R.id.textview_singup);
        mTextViewSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
        mButtonSignin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(homeIntent);
            }
        });
    }
}
