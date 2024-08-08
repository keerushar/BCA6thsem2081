package com.acem.bca6thsem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private Activity context;
    private ArrayList<MyData> data;
    private MyDbHelper myDbHelper;

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
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        MyData current = data.get(position);
        holder.txtId.setText(String.valueOf(current.getId()));
        holder.txtName.setText(current.getName());
        holder.txtAddress.setText(current.getAddress());

        // Handle delete action
        holder.deleteIv.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete item");
            builder.setMessage("Are you sure you want to delete this item?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                myDbHelper.deleteData(String.valueOf(current.getId()));
                data.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();
            });

            builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
        });

        // Handle edit action
        holder.editIv.setOnClickListener(v -> showEditItemDialog(position, current));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // Show dialog to edit item
    private void showEditItemDialog(int position, MyData currentData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Item");

        // Inflate the dialog with custom layout
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_items_dialog, null);
        builder.setView(dialogView);

        EditText idEt = dialogView.findViewById(R.id.idEt);
        EditText nameEt = dialogView.findViewById(R.id.nameEt);
        EditText addressEt = dialogView.findViewById(R.id.addressEt);
        Button updateBtn = dialogView.findViewById(R.id.addItemBtn);

        // Pre-fill the dialog with current data
        idEt.setText(String.valueOf(currentData.getId()));
        idEt.setEnabled(false); // Disable editing of the ID

        nameEt.setText(currentData.getName());
        addressEt.setText(currentData.getAddress());

        AlertDialog alertDialog = builder.create();

        updateBtn.setText("Update"); // Change the button text to "Update"
        updateBtn.setOnClickListener(v -> {
            String updatedName = nameEt.getText().toString();
            String updatedAddress = addressEt.getText().toString();

            if (!updatedName.isEmpty() && !updatedAddress.isEmpty()) {
                // Update data in database
                currentData.setName(updatedName);
                currentData.setAddress(updatedAddress);
                myDbHelper.updateData(currentData);

                // Update data in the ArrayList and notify adapter
                data.set(position, currentData);
                notifyDataSetChanged();

                alertDialog.dismiss();
                Toast.makeText(context, "Item updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtName, txtAddress;
        ImageView deleteIv, editIv;

        public ViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.idTv);
            txtName = itemView.findViewById(R.id.titleTv);
            txtAddress = itemView.findViewById(R.id.descTv);
            deleteIv = itemView.findViewById(R.id.deleteIv);
            editIv = itemView.findViewById(R.id.editIv); // Bind the edit ImageView
        }
    }
}
