package com.example.mos_innovative;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<SpinnerItem> {

    private Context context;
    private List<SpinnerItem> items;

    public CustomAdapter(Context context, List<SpinnerItem> items) {
        super(context, R.layout.list_item, items); // Use the item layout and the list of items
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Use ViewHolder pattern for better performance
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.itemImage);
            holder.textViewName = convertView.findViewById(R.id.itemName);
            holder.textViewCost = convertView.findViewById(R.id.itemCost);
            convertView.setTag(holder); // Store the holder with the view
        } else {
            holder = (ViewHolder) convertView.getTag(); // Reuse the holder
        }

        // Get the current item
        SpinnerItem currentItem = getItem(position);

        // Set the image and text
        if (currentItem != null) {
            holder.imageView.setImageResource(currentItem.getImageResId());
            holder.textViewName.setText(currentItem.getName());
            holder.textViewCost.setText("$" + currentItem.getCost());
        }

        return convertView;
    }

    // ViewHolder class to hold the views
    static class ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewCost;
    }
}
