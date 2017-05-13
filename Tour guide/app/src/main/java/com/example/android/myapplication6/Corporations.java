package com.example.android.myapplication6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class Corporations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationlist);

        ArrayList<Location> corporations = new ArrayList<Location>();
        corporations.add(new Location(getString(R.string.majesco), getString(R.string.majesco_info)));
        corporations.add(new Location(getString(R.string.colavita), getString(R.string.colavita_info)));
        corporations.add(new Location(getString(R.string.zylog), getString(R.string.zylog_info)));
        corporations.add(new Location(getString(R.string.boxed), getString(R.string.boxed_info)));

        LocationAdapter adapter = new LocationAdapter(this, corporations);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

}
