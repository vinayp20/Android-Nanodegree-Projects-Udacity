package com.example.android.myapplication7;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, if not inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_display, parent, false);
        }
        Book currentBook = getItem(position);


        if (currentBook != null) {
            // Find the TextView for title in list_item.xml
            TextView mTextView = (TextView) listItemView.findViewById(R.id.title_display);
            mTextView.setText(currentBook.getmBookTitle());
            // Find the TextView for info in list_item.xml
            TextView mTextView2 = (TextView) listItemView.findViewById(R.id.author);
            mTextView2.setText(currentBook.getmAuthorNames());
        }
        return listItemView;
    }
}