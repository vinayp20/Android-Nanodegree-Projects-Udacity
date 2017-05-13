package com.example.android.myapplication6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class Parks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationlist);
        ArrayList<Location> parks = new ArrayList<Location>();
        parks.add(new Location(getString(R.string.roosevelt), getString(R.string.roosevelt_info), R.drawable.park_default));
        parks.add(new Location(getString(R.string.edisonpark), getString(R.string.edison_info), R.drawable.park_default));
        parks.add(new Location(getString(R.string.rutgers), getString(R.string.rutgers_info), R.drawable.park_default));
        parks.add(new Location(getString(R.string.papaianni), getString(R.string.papaianni_info), R.drawable.park_default));


        LocationAdapter adapter = new LocationAdapter(this, parks);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


    }
}
