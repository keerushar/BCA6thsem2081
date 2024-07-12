package com.acem.bca6thsem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        TextView titleTxt = findViewById(R.id.titleTxt);

        switch (item.getItemId()) {
            case R.id.item1:
                titleTxt.setText(item.getTitle());
                return true;

            case R.id.item2:
                titleTxt.setText(item.getTitle());
                return true;

            case R.id.item3:
                titleTxt.setText(item.getTitle());
                return true;

            case R.id.item4:
                titleTxt.setText(item.getTitle());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}