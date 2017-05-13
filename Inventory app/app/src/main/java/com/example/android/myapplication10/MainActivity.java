package com.example.android.myapplication10;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.myapplication10.data.InventoryContract;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView mListView;
    private InventoryAdapter mInventoryAdapter;
    TextView inventoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EntryActivity.class);
                startActivity(intent);
            }
        });

        mListView = (ListView) findViewById(R.id.list);
        mInventoryAdapter = new InventoryAdapter(this, null);
        mListView.setAdapter(mInventoryAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Uri uri = ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI, id);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        inventoryView = (TextView) findViewById(R.id.main_empty);
        // Find and set empty view on the ListView, so that it only shows when the list has no items.
        mListView.setEmptyView(inventoryView);
        getSupportLoaderManager().initLoader(1, null, this);

    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                InventoryContract.InventoryEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
          mInventoryAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
       mInventoryAdapter.swapCursor(null);
    }
}
