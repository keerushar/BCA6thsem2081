package com.acem.bca6thsem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.acem.bca6thsem.adapter.RecyclerViewAdapter;
import com.acem.bca6thsem.data.MyData;
import com.acem.bca6thsem.helper.MyDbHelper;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // UI Components
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MaterialButton addBtn, addItemBtn;
    private EditText idEt, nameEt, addressEt;
    private TextView noDataTv;

    // Database Helper
    private MyDbHelper myDbHelper;

    // Data List
    private ArrayList<MyData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components and database helper
        initViews();

        // Load data from the database into the RecyclerView
        data = loadDataFromDatabase();

        // Setup RecyclerView with adapter and layout manager
        setupRecyclerView();

        // Check if data exists and update UI accordingly
        checkDataVisibility(data);

        // Set click listener to add new items
        addBtn.setOnClickListener(v -> showAddItemDialog(data));
    }

    // Initialize UI components and database helper
    private void initViews() {
        recyclerView = findViewById(R.id.itemsRv);
        addBtn = findViewById(R.id.addBtn);
        noDataTv = findViewById(R.id.noDataTv);
        myDbHelper = new MyDbHelper(this);
    }

    // Setup RecyclerView with a linear layout manager and adapter
    private void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(this, data, myDbHelper); // Pass the correct data list
        recyclerView.setAdapter(adapter);
    }

    // Load data from the SQLite database into an ArrayList
    private ArrayList<MyData> loadDataFromDatabase() {
        ArrayList<MyData> data = new ArrayList<>();
        Cursor cursor = myDbHelper.selectData();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            data.add(new MyData(id, name, address));
        }
        cursor.close(); // Close the cursor to prevent memory leaks
        return data;
    }

    // Show dialog to add new items to the database
    private void showAddItemDialog(ArrayList<MyData> data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Items");
        builder.setCancelable(true);

        // Inflate the custom dialog view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_items_dialog, null);
        builder.setView(view);

        // Initialize dialog UI components
        idEt = view.findViewById(R.id.idEt);
        nameEt = view.findViewById(R.id.nameEt);
        addressEt = view.findViewById(R.id.addressEt);
        addItemBtn = view.findViewById(R.id.addItemBtn);

        // Create and show the dialog
        AlertDialog alert = builder.create();
        addItemBtn.setOnClickListener(v -> handleAddItem(alert));
        alert.show();
    }

    // Handle the addition of a new item when the button in the dialog is clicked
    private void handleAddItem(AlertDialog alert) {
        String idStr = idEt.getText().toString();
        String name = nameEt.getText().toString();
        String address = addressEt.getText().toString();

        // Validate inputs before adding to the database
        if (idStr.isEmpty() || name.isEmpty() || address.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill the data", Toast.LENGTH_SHORT).show();
        } else {
            int id = Integer.parseInt(idStr);
            MyData newData = new MyData(id, name, address);
            data.add(newData);

            // Insert new data into the database
            myDbHelper.insertData(newData);

            // Notify the adapter of the new data
            adapter.notifyDataSetChanged();
            alert.dismiss(); // Close the dialog

            // Check if data exists and update UI accordingly
            checkDataVisibility(data);
        }
    }

    // Check if data exists and update visibility of RecyclerView and no-data TextView
    private void checkDataVisibility(ArrayList<MyData> data) {
        if (data.isEmpty()) {
            noDataTv.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noDataTv.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
