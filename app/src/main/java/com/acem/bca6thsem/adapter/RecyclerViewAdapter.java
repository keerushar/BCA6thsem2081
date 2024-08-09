package com.acem.bca6thsem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acem.bca6thsem.R;
import com.acem.bca6thsem.model.MyData;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Activity context;
    ArrayList<MyData> data;

    public RecyclerViewAdapter(Activity context, ArrayList<MyData> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItem = inflater.inflate(R.layout.recycler_view_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyData current = data.get(position);
        holder.txtId.setText(current.getId()+ "");
        holder.txtName.setText(current.getName());
        holder.txtAddress.setText(current.getAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtId, txtName, txtAddress;

        public ViewHolder(View itemView){
            super(itemView);
            txtId = itemView.findViewById(R.id.idTv);
            txtName = itemView.findViewById(R.id.titleTv);
            txtAddress = itemView.findViewById(R.id.descTv);
        }
    }
}
