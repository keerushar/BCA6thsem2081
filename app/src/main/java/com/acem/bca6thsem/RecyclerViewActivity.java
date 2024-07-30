package com.acem.bca6thsem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acem.bca6thsem.adapter.RecyclerViewAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);

        String[] title = {
                "Pokhara", "Kathmandu", "Dharan",
                "Biratnagar", "Pokhara", "Kathmandu",
                "Dharan", "Biratnagar", "Sunsari",
                "Jhapa", "Butwal"};

        String[] desc = {"Pokhara Description", "Kathmandu Description", "Dharan Description",
                "Biratnagar Description", "Pokhara Description", "Kathmandu Description",
                "Dharan Description", "Biratnagar Description", "Sunsari Description",
                "Jhapa Description", "Butwal Description"};

        int[] imageId = {R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
        };

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(this, title, desc, imageId);
        recyclerView.setAdapter(adapter);
    }
}