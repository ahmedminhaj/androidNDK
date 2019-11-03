package com.example.gm1.pavilion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gm1.pavilion.R;
import com.example.gm1.pavilion.adapter.ListAdapter;
import com.example.gm1.pavilion.models.AttendanceList;

import java.util.ArrayList;
import java.util.List;


public class AttendanceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<AttendanceList> attendanceLists;
    Button mHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);


        mHome = (Button)findViewById(R.id.button_home);
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeIntent = new Intent(AttendanceActivity.this,HomeActivity.class);
                startActivity(HomeIntent);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        attendanceLists = new ArrayList<>();

        for(int i = 0; i<=10; i++){
            AttendanceList attendanceList = new AttendanceList(
                    "Date" + (i+1),
                    "Entry Time",
                    "Exit Time"
            );

            attendanceLists.add(attendanceList);
        }
        adapter = new ListAdapter(attendanceLists, this);
        recyclerView.setAdapter(adapter);

    }

}

