package com.acem.bca6thsem;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.acem.bca6thsem.databinding.ActivityCustomListBinding;

public class CustomListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        listView = findViewById(R.id.customListView);

        String[] title = {
                "Pokhara", "Kathmandu", "Dharan",
                "Biratnagar", "Pokhara", "Kathmandu",
                "Dharan", "Biratnagar", "Sunsari",
                "Jhapa", "Butwal"};

        String[] desc = {"Pokhara Description", "Kathmandu Description", "Dharan Description",
                "Biratnagar Description", "Pokhara Description", "Kathmandu Description",
                "Dharan Description", "Biratnagar Description", "Sunsari Description",
                "Jhapa Description", "Butwal Description"};

        int [] imageId = {R.drawable.ic_launcher_background,
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

        //Creating the instance of our custom adapter.
        MyListAdapter adapter = new MyListAdapter(this, title, desc, imageId);

        //Setting the adapter to the listView
        listView.setAdapter(adapter);
    }
}