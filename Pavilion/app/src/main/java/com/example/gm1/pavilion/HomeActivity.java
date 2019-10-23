package com.example.gm1.pavilion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    ListView mListView;
    ArrayAdapter aAdapter;
    String[] logList = {"22/10/2019  11:25:45 am","21/10/2019  11:10:45 am", "20/10/2019  11:20:45 am"};
    Button mButtonLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mListView = (ListView) findViewById(R.id.logInList);
        aAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, logList);
        mListView.setAdapter(aAdapter);
        mButtonLogIn = (Button)findViewById(R.id.button_logIn);
        mButtonLogIn.setVisibility(View.VISIBLE);
        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonLogIn.setVisibility(View.INVISIBLE);
                mButtonLogIn.postDelayed(new Runnable() {
                    public void run() {
                        mButtonLogIn.setVisibility(View.VISIBLE);
                    }
                }, 10000);
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String time = "Today's Login Time: " + format.format(calendar.getTime());

                TextView textView = findViewById(R.id.login_time);
                textView.setText(time);
            }
        });
    }
}
