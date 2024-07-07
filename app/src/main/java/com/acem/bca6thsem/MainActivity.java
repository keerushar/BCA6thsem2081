package com.acem.bca6thsem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.loginBtn);
        EditText emailEt = findViewById(R.id.emailEt);
        EditText passwordEt = findViewById(R.id.passwordEt);

        loginBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DashboardActivity.class);
            String email = emailEt.getText().toString();
            String password = passwordEt.getText().toString();

            i.putExtra("email", email);
            i.putExtra("password", password);
            startActivityForResult(i,4);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 4){
           String dataExtract = data.getStringExtra("data");
           TextView loginTv = findViewById(R.id.loginTv);

           loginTv.setText(dataExtract);
        }
    }
}