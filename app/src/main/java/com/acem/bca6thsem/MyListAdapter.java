package com.acem.bca6thsem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {
  Activity context;
     String[] title;
  String[] desc;
  int[] image;


    public MyListAdapter(Activity context, String[] title, String[] desc, int[] image) {
        super(context, R.layout.custom_list_layout, title);
        this.context = context;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public View getView(int position , View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_layout,null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.titleTxt);
        TextView descText = (TextView) rowView.findViewById(R.id.descTxt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.titleIv);

        titleText.setText(title[position]);
        descText.setText(desc[position]);
        imageView.setImageResource(image[position]);

        return rowView;
    }
}
