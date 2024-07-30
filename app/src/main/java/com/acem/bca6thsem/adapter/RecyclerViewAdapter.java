package com.acem.bca6thsem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.acem.bca6thsem.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Activity context;

    String[] titles;
    String[] descriptions;

    int[] images;

    public RecyclerViewAdapter(Activity context, String[] titles, String[] descriptions, int[] image) {
        this.titles = titles;
        this.descriptions = descriptions;
        this.images = image;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(images[position]);
        holder.titleTxt.setText(titles[position]);
        holder.descTxt.setText(descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, descTxt;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            descTxt = itemView.findViewById(R.id.descTxt);
            image = itemView.findViewById(R.id.titleIv);
        }
    }
}
