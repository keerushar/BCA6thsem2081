package com.acem.bca6thsem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FirstFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        EditText firstEt = v.findViewById(R.id.firstNumEt);
        EditText secondEt = v.findViewById(R.id.secondNumEt);

        Button addBtn = v.findViewById(R.id.addBtn);
        TextView resultTv = v.findViewById(R.id.resultTv);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Integer firstNum = Integer.parseInt(firstEt.getText().toString());
                    Integer secondNum = Integer.parseInt(secondEt.getText().toString());

                    Integer sum = firstNum + secondNum;
                    resultTv.setText("Result is" +sum);

                } catch (Exception e) {
                    Log.e("Exception occur", "Parse Exception");
                }

            }
        });

        return v;
    }
}