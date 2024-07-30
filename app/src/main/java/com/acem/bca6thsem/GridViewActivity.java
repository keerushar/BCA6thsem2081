package com.acem.bca6thsem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GridViewActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid_view);

        gridView = findViewById(R.id.gridView);

        String[] items = {
                "Pokhara",
                "Kathmandu",
                "Butwal",
                "Sindhuli", "Dharan", "Pokhara",
                "Kathmandu",
                "Butwal",
                "Sindhuli", "Dharan", "Pokhara",
                "Kathmandu",
                "Butwal",
                "Sindhuli", "Dharan", "Pokhara",
                "Kathmandu",
                "Butwal",
                "Sindhuli", "Dharan"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        gridView.setAdapter(adapter);

        // Set item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}