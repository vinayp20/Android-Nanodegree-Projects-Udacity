package com.example.android.myapplication6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoricalPlaces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationlist);

        ArrayList<Location> historicalPlaces = new ArrayList<Location>();
        historicalPlaces.add(new Location(getString(R.string.benjamin), getString(R.string.benjamin_info), R.drawable.benjamin_house));
        historicalPlaces.add(new Location(getString(R.string.homesteadfarm), getString(R.string.homesteadfarm_info), R.drawable.homestead_farm));
        historicalPlaces.add(new Location(getString(R.string.lainghouse), getString(R.string.lainghouse_info), R.drawable.laing_house));


        LocationAdapter adapter = new LocationAdapter(this, historicalPlaces);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}
