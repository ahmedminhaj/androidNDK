package com.example.gm1.testndk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.text_hello_world)).setText(helloWorld());
    }
    public native String helloWorld();
    static
    {
        System.loadLibrary("testndk");
    }
}
