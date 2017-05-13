package com.example.android.myapplication10;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.myapplication10.data.InventoryContract;



public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    TextView mName;
    TextView mQuantity;
    TextView mPrice;
    Button order;
    Button delete;
    Button plus;
    Button minus;
    TextView quanDisp;
    ImageView mImageView;

    private Uri mItemUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mName = (TextView) findViewById(R.id.detail_name);
        mQuantity = (TextView) findViewById(R.id.detail_quantity);
        mPrice = (TextView) findViewById(R.id.detail_price);
        quanDisp = (TextView) findViewById(R.id.quantity_add_sub);
        order = (Button) findViewById(R.id.detail_order);
        delete = (Button) findViewById(R.id.detail_delete_product);
        plus = (Button) findViewById(R.id.detail_quantity_add);
        minus = (Button) findViewById(R.id.detail_quantity_sub);
        mImageView = (ImageView) findViewById(R.id.detail_image);




        Intent intent = getIntent();
        mItemUri = intent.getData();
        if(mItemUri!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 showDeleteConfirmationDialog();
                 getContentResolver().delete(mItemUri, null, null);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderConfirmationDialog();
                String email = "xyz@abc.com";
                String product = mName.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("Send an order mail:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Order for" +product);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    private void showOrderConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to order this?");
        builder.setPositiveButton("ORDER", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String email = "xyz@abc.com";
                String product = mName.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Order for" +product);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this?");
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getContentResolver().delete(mItemUri, null, null);
                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                mItemUri,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if(data.moveToFirst()){
            int nameIndex = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME);
            int quantityIndex = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_QUANTITY);
            int priceIndex = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE);
            int imageIndex = data.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_IMAGE);


            final String name = data.getString(nameIndex);
            final int quantity = data.getInt(quantityIndex);
            double price = data.getDouble(priceIndex);
            byte[] imageByteArray = data.getBlob(imageIndex);

           final Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

            ViewTreeObserver viewTreeObserver = mImageView.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    mImageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, mImageView.getWidth(),
                            mImageView.getHeight(), false));
                }
            });


            mName.setText("Name: "+name);
            mQuantity.setText("Quantity: "+quantity);
            mPrice.setText("Price: "+price);



            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(quantity>=0){
                        ContentValues contentValues = new ContentValues();
                        int quantity2 = quantity+1;

                        contentValues.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY,quantity2);
                        getContentResolver().update(mItemUri, contentValues, null, null);
                        getContentResolver().notifyChange(mItemUri, null);

                    }
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(quantity>=1){
                        ContentValues contentValues = new ContentValues();
                        int quantity2 = quantity -1;
                        contentValues.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY,quantity2);
                        getContentResolver().update(mItemUri, contentValues, null, null);
                        getContentResolver().notifyChange(mItemUri, null);
                    }
                }
            });
        }



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
