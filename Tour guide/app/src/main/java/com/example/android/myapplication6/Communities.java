package com.example.android.myapplication6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class Communities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationlist);

        ArrayList<Location> communities = new ArrayList<Location>();
        communities.add(new Location(getString(R.string.bonhamtown), getString(R.string.bonham_info)));
        communities.add(new Location(getString(R.string.clarabarton), getString(R.string.clara_info)));
        communities.add(new Location(getString(R.string.lindenau), getString(R.string.lindenau_info)));
        communities.add(new Location(getString(R.string.menlopark), getString(R.string.menlopark_info)));
        communities.add(new Location(getString(R.string.nixon), getString(R.string.nixon_info)));
        communities.add(new Location(getString(R.string.pumptown), getString(R.string.pumptown_info)));

        LocationAdapter adapter = new LocationAdapter(this, communities);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}
