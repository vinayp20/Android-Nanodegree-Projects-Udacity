package com.example.android.myapplication10;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myapplication10.data.InventoryContract;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EntryActivity extends AppCompatActivity {
    private TextView mNameView;
    private TextView mQuantityView;
    private TextView mPriceView;
    private Button mImageButton;
    private ImageView mImageView;
    private static final int FILE_SELECT_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        mNameView = (EditText) findViewById(R.id.edit_name);
        mQuantityView = (EditText) findViewById(R.id.edit_quantity);
        mPriceView = (EditText) findViewById(R.id.edit_price);
        mImageButton = (Button) findViewById(R.id.edit_image_button);
        mImageView = (ImageView) findViewById(R.id.edit_image_view);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


    }

    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Photo"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            try {
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                mImageView = (ImageView) findViewById(R.id.edit_image_view);
                mImageView.setImageBitmap(bitmap);
                if (mImageView.getDrawable() == null) {
                    Toast.makeText(this, "You must upload an image.", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save:
                if (mImageView.getDrawable() == null) {
                    Toast.makeText(this, "You must upload an image.", Toast.LENGTH_LONG).show();
                }
                saveEntry();
                break;

            case R.id.home:
                NavUtils.navigateUpFromSameTask(EntryActivity.this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void saveEntry() {
        String name = mNameView.getText().toString();
        String quantityString = mQuantityView.getText().toString();
        String priceString = mPriceView.getText().toString();
        if (name.isEmpty() || quantityString.isEmpty() || priceString.isEmpty()) {
            Toast.makeText(this, "Text fields can't be empty.", Toast.LENGTH_LONG).show();
            return;
        }

        int quantity = Integer.valueOf(quantityString);
        int price = Integer.valueOf(priceString);

        if (quantity < 0 || price < 0) {
            Toast.makeText(this, "Price or quantity cannot be less than zero.", Toast.LENGTH_LONG).show();
            return;
        }

        if (mImageView.getDrawable() == null) {
            Toast.makeText(this, "You must upload an image.", Toast.LENGTH_LONG).show();
            return;
        }

        //get ImageView as Bitmap
        Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, outStream);
        byte[] byteImageArray = outStream.toByteArray();


        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME, name);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY, quantity);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_PRICE, price);
        contentValues.put(InventoryContract.InventoryEntry.COLUMN_IMAGE, byteImageArray);

        Uri createUri = getContentResolver().insert(InventoryContract.InventoryEntry.CONTENT_URI, contentValues);

        if (createUri == null) {
            Toast.makeText(this, "Couldn't add item", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Item inserted.", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
