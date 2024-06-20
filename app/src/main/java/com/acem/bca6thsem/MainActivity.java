package com.acem.bca6thsem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
            startActivity(i);
        });
    }
}