package com.acem.bca6thsem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        Log.d("Activity LifeCycle", "On Create");

        Button button = findViewById(R.id.navigateBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, HomePageActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity LifeCycle", "On start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity LifeCycle", "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity LifeCycle", "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Activity LifeCycle", "On stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("Activity LifeCycle", "On restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity LifeCycle", "On Destroy");
    }
}