package com.acem.bca6thsem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        String[] items = {
                "Pokhara",
                "Kathmandu",
                "Butwal",
                "Sindhuli", "Dharan","Pokhara",
                "Kathmandu",
                "Butwal",
                "Sindhuli", "Dharan","Pokhara",
                "Kathmandu",
                "Butwal",
                "Sindhuli", "Dharan","Pokhara",
                "Kathmandu",
                "Butwal",
                "Sindhuli", "Dharan"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this, R.layout.listview_items,R.id.titleText, items);
        listView.setAdapter(arrayAdapter);
    }
}