package com.acem.bca6thsem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button contextMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contextMenuBtn = findViewById(R.id.contextMenuBtn);

        registerForContextMenu(contextMenuBtn);

        contextMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContextMenu(view);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
    }

    //Create menu item view
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.my_menu, menu);
//        return true;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    //Operation on selecting menu item
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