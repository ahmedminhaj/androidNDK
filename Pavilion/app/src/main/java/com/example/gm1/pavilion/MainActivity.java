package com.example.gm1.pavilion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText mTextEmail;
    EditText mTextPassword;
    Button mButtonSignin;
    TextView mTextViewSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextEmail = (EditText)findViewById(R.id.edittext_email);
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
                /* Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(homeIntent); */
                userSingIn();
            }
        });
    }

    private void userSingIn() {
        String email = mTextEmail.getText().toString().trim();
        String password = mTextPassword.getText().toString().trim();
        int role_id = 2;

        if(email.isEmpty()){
            mTextEmail.setError("Email required");
            mTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mTextEmail.setError("Required vail email");
            mTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            mTextPassword.setError("Password required");
            mTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            mTextPassword.setError("Password require 6 digit length");
            mTextPassword.requestFocus();
            return;
        }

        Call<SignInRespose> call = RetrofitClient
                .getInstance()
                .getApi()
                .userSignIn(email, password, role_id);

        call.enqueue(new Callback<SignInRespose>() {
            @Override
            public void onResponse(Call<SignInRespose> call, Response<SignInRespose> response) {
                SignInRespose signInRespose = response.body();
                if(signInRespose.isStatus()){
                    Toast.makeText(MainActivity.this, signInRespose.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(homeIntent);
                }else{
                    Toast.makeText(MainActivity.this, signInRespose.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignInRespose> call, Throwable t) {

            }
        });
    }
}
