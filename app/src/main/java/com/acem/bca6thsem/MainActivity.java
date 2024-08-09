package com.acem.bca6thsem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.acem.bca6thsem.adapter.RecyclerViewAdapter;
import com.acem.bca6thsem.model.MyData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    RecyclerView.LayoutManager layoutManager;

    ProgressBar progressBar;

    EditText nameEt, addressEt;
    MaterialButton addBtn, addDataBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.itemsRv);
        progressBar = findViewById(R.id.progressPv);
        addDataBtn = findViewById(R.id.addDataBtn);

        volleyGetRequest();

        progressBar.setVisibility(View.VISIBLE);

        addDataBtn.setOnClickListener(v -> showAddItemDialog());
    }


    public void volleyGetRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.65/myproject/getdata.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                this::decodeJson,
                error -> Log.d("Error are here", error.toString()
                )
        );
        queue.add(stringRequest);
    }

    public void volleyInsertRequest(String name, String address) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.65/myproject/setdata.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                response -> {
                    volleyGetRequest();

                    progressBar.setVisibility(View.VISIBLE);
                },
                error -> Log.d("Error", error.toString())
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("address", address);
                return params;
            }
        };

        queue.add(stringRequest);
    }


    private void decodeJson(String response) {
        Log.d("Response data", response);
        try {
            progressBar.setVisibility(View.GONE);
            ArrayList<MyData> data = new ArrayList<>();
            JSONObject result = new JSONObject(response);
            JSONArray array = result.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject user = array.getJSONObject(i);

                int id = user.getInt("id");
                String name = user.getString("name");
                String address = user.getString("address");
                MyData myData = new MyData(id, name, address);

                data.add(myData);
            }

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new RecyclerViewAdapter(this, data);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);

            Log.d("Error", "Error " + e);

        }
    }

    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add user");
        builder.setCancelable(true);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_items_dialog, null);
        builder.setView(view);

        nameEt = view.findViewById(R.id.nameEt);
        addressEt = view.findViewById(R.id.addressEt);
        addBtn = view.findViewById(R.id.addBtn);

        AlertDialog alert = builder.create();

        addBtn.setOnClickListener(v -> {

            String name = nameEt.getText().toString();
            String address = addressEt.getText().toString();

            if (!name.isEmpty() && !address.isEmpty()) {
                volleyInsertRequest(name, address);
                alert.dismiss();
            } else {
                Toast.makeText(getApplicationContext(), "Please fill the data", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }
}