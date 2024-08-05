package com.acem.bca6thsem;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.acem.bca6thsem.adapter.RecyclerViewAdapter;
import com.acem.bca6thsem.data.MyData;
import com.acem.bca6thsem.helper.MyDbHelper;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    MyDbHelper myDbHelper;
    MaterialButton addBtn, addItemBtn;
    EditText idEt, nameEt, addressEt;
    TextView noDataTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.itemsRv);
        addBtn = findViewById(R.id.addBtn);
        noDataTv = findViewById(R.id.noDataTv);
        myDbHelper = new MyDbHelper(this);

        ArrayList<MyData> data = new ArrayList<>();

        Cursor cursor = myDbHelper.selectData();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);

            data.add(new MyData(id, name, address));

        }

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(this, data, myDbHelper);
        recyclerView.setAdapter(adapter);

        checkData(data);

        addBtn.setOnClickListener(v -> showAddItemDialog(data));
    }

    private void showAddItemDialog(ArrayList<MyData> data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Items");
        builder.setCancelable(true);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_items_dialog, null);
        builder.setView(view);

        idEt = view.findViewById(R.id.idEt);
        nameEt = view.findViewById(R.id.nameEt);
        addressEt = view.findViewById(R.id.addressEt);
        addItemBtn = view.findViewById(R.id.addItemBtn);

        AlertDialog alert = builder.create();

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString();
                String address = addressEt.getText().toString();

                if (!name.isEmpty() && !address.isEmpty() && !idEt.getText().toString().isEmpty()) {
                    int id = Integer.parseInt(idEt.getText().toString());

                    data.add(new MyData(id, name, address));
                    alert.dismiss();
                    myDbHelper.insertData(new MyData(id, name, address));
                    adapter.notifyDataSetChanged();

                    checkData(data);
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill the data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alert.show();
    }

    private void checkData(ArrayList<MyData> data) {
        if (data.isEmpty()) {
            noDataTv.setVisibility(VISIBLE);
            recyclerView.setVisibility(GONE);
        } else {
            noDataTv.setVisibility(GONE);
            recyclerView.setVisibility(VISIBLE);
        }
    }
}