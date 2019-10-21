package com.example.gm1.logtimendk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        Button button = findViewById(R.id.gettime);
        button.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Calendar calendar = Calendar.getInstance();
               SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
               String time = "Logged Time: " + format.format(calendar.getTime());

               TextView textView = findViewById(R.id.time);
               textView.setText(time);
           }
        });
    }

    public native String stringFromJNI();
}
