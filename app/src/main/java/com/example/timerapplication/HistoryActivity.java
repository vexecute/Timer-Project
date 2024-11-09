package com.example.timerapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TimerHistoryAdapter adapter;
    private TimerDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new TimerDatabaseHelper(this);
        List<TimerModel> timerHistoryList = databaseHelper.getAllTimers();
        adapter = new TimerHistoryAdapter(timerHistoryList);
        recyclerView.setAdapter(adapter);
    }
}

