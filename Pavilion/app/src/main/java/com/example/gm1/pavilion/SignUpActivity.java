package com.example.gm1.pavilion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextEmail;
    Button mButtonSignup;
    TextView mTextViewSignin;

private static final String REGISTER_URL="https://peddlecloud.com/pavdev/api/auth/user_registration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mTextUsername = (EditText)findViewById(R.id.edittext_name);
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
                userSignUp();
            }
        });
    }

    private void userSignUp() {
        String email = mTextEmail.getText().toString().trim();
        String password = mTextPassword.getText().toString().trim();
        String name = mTextUsername.getText().toString().trim();
        int role_id = 2;
        String android_id = Secure.getString(getApplicationContext().getContentResolver(),
                Secure.ANDROID_ID);

        if(name.isEmpty()){
            mTextUsername.setError("Name required");
            mTextUsername.requestFocus();
            return;
        }
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
        /* User registration using api call */
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(role_id, email, password, name, android_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    String s = response.body().string();
                    Toast.makeText(SignUpActivity.this, s, Toast.LENGTH_SHORT).show();

                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}


