package com.acem.bca6thsem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.acem.bca6thsem.R;
import com.acem.bca6thsem.data.MyData;
import com.acem.bca6thsem.helper.MyDbHelper;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Activity context;
    ArrayList<MyData> data;

    MyDbHelper myDbHelper;

    public RecyclerViewAdapter(Activity context, ArrayList<MyData> data, MyDbHelper myDbHelper) {
        this.context = context;
        this.data = data;
        this.myDbHelper = myDbHelper;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItem = inflater.inflate(R.layout.recycler_view_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        MyData current = data.get(position);
        holder.txtId.setText(current.getId() + "");
        holder.txtName.setText(current.getName());
        holder.txtAddress.setText(current.getAddress());

        holder.deleteIv.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete items");
            builder.setMessage("Are you sure you want to delete the items ?");
            builder.setPositiveButton("Yes", (dialog, which) ->
            {
                myDbHelper.deleteData(current.getId() + "");
                Toast.makeText(context, "Items removed", Toast.LENGTH_LONG).show();
                data.remove(current);
                notifyDataSetChanged();
            });

            builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtName, txtAddress;
        ImageView deleteIv;

        public ViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.idTv);
            txtName = itemView.findViewById(R.id.titleTv);
            txtAddress = itemView.findViewById(R.id.descTv);
            deleteIv = itemView.findViewById(R.id.deleteIv);
        }
    }
}