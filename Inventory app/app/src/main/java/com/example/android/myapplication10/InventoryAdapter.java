package com.example.android.myapplication10;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.myapplication10.data.InventoryContract;

public class InventoryAdapter extends CursorAdapter {

    public InventoryAdapter(Context context, Cursor cursor) {

        super(context, cursor, 0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_display, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {


        TextView nameView = (TextView) view.findViewById(R.id.item_name);
        TextView quantityView = (TextView) view.findViewById(R.id.item_quantity);
        TextView priceView = (TextView) view.findViewById(R.id.item_price);

        final int id = cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry._ID));
        String name = cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_QUANTITY));
        Double price = cursor.getDouble(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE));

        nameView.setText("Item Name: " + name);
        quantityView.setText("Quantity: " + quantity);
        priceView.setText("Price: " + price);

        Button buy = (Button) view.findViewById(R.id.buy_one);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI, id);
                buyOne(context, quantity, uri);
            }
        });


    }

    private void buyOne(Context context, int quantity, Uri uri) {


        ContentValues contentValues = new ContentValues();
        int quantity2 = 0;
        if (quantity >= 1) {
            quantity2 = quantity - 1;
        }
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY, quantity2);
        context.getContentResolver().update(uri, contentValues, null, null);

    }
}

