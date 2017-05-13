package com.example.android.myapplication6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter<Location> {

    public LocationAdapter(Context context, ArrayList<Location> locations) {
        super(context, 0, locations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, if not inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Location currentLocation = getItem(position);

        // Find the TextView for title in list_item.xml
        TextView mTextView = (TextView) listItemView.findViewById(R.id.edison_text_view);
        mTextView.setText(currentLocation.getmLocation());
        // Find the TextView for info in list_item.xml
        TextView mTextView2 = (TextView) listItemView.findViewById(R.id.edison_text_info);
        mTextView2.setText(currentLocation.getmInformation());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // Check if there is an image
        if (currentLocation.hasImage()) {
            // If an image is available, display the provided image
            imageView.setImageResource(currentLocation.getImageResourceId());
            // Make the image visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise set the visibility to GONE
            imageView.setVisibility(View.GONE);
        }
        return listItemView;
    }
}