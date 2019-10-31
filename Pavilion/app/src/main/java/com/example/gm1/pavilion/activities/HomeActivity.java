package com.example.gm1.pavilion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gm1.pavilion.R;
import com.example.gm1.pavilion.api.RetrofitClient;
import com.example.gm1.pavilion.models.EntryExitResponse;
import com.example.gm1.pavilion.models.User;
import com.example.gm1.pavilion.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    TextView mUserName;
    Button mButtonLogIn;//entry time button
    Button mButtonLogOff;//exit time button
    Button mButtonSignOut;//account sign out

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mUserName = findViewById(R.id.edittext_name);
        User user = SharedPrefManager.getInstance(this).getData(); //user data
        mUserName.setText(user.getName()); //show user name

        mButtonSignOut = findViewById(R.id.button_signOut);
        mButtonLogIn = findViewById(R.id.button_logIn);
        mButtonLogOff = findViewById(R.id.button_logOff);
        mButtonLogIn.setVisibility(View.VISIBLE);
        mButtonLogOff.setVisibility(View.GONE);

        //entry time button
        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonLogIn.setVisibility(View.GONE);
                mButtonLogOff.setVisibility(View.VISIBLE);
                mButtonLogIn.postDelayed(new Runnable() {
                    public void run() {
                        mButtonLogIn.setVisibility(View.VISIBLE);
                    }
                }, 600000);

                entryTime();
            }
        });

        //exit time button
        mButtonLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonLogOff.setVisibility(View.GONE);

                exitTime();
            }
        });

        //account sign out
        mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    //account signOut method
    private void signOut(){
        SharedPrefManager.getInstance(this).clear();
        Intent homeIntent = new Intent(this,MainActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
    }

    //entry time method
    private void entryTime(){
        User user = SharedPrefManager.getInstance(this).getData();
        int user_id = user.getId();

        Call<EntryExitResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .entryTime(user_id);

        call.enqueue(new Callback<EntryExitResponse>() {
            @Override
            public void onResponse(Call<EntryExitResponse> call, Response<EntryExitResponse> response) {
                EntryExitResponse entryExitResponse = response.body();
                if(entryExitResponse.isStatus()){

                    TextView textView = findViewById(R.id.login_time);
                    textView.setText(" Entry Time: " + entryExitResponse.getEntry_time());
                    Toast.makeText(HomeActivity.this, entryExitResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(HomeActivity.this, entryExitResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EntryExitResponse> call, Throwable t) {
            }
        });
    }

    //exit time method
    private void exitTime(){
        User user = SharedPrefManager.getInstance(this).getData();
        int user_id = user.getId();

        Call<EntryExitResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .exitTime(user_id);

        call.enqueue(new Callback<EntryExitResponse>() {
            @Override
            public void onResponse(Call<EntryExitResponse> call, Response<EntryExitResponse> response) {
                EntryExitResponse entryExitResponse = response.body();
                if(entryExitResponse.isStatus()){

                    TextView textView = findViewById(R.id.logoff_time);
                    textView.setText(" Exit Time: " + entryExitResponse.getExit_time());
                    Toast.makeText(HomeActivity.this, entryExitResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(HomeActivity.this, entryExitResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EntryExitResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent homeIntent = new Intent(this,MainActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);

        }
    }
}
