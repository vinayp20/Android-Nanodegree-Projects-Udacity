package com.example.android.myapplication4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // take all the views by their id

        setContentView(R.layout.activity_main);
        TextView nowPlaying=(TextView) findViewById(R.id.main_now);
        TextView favourites=(TextView) findViewById(R.id.main_fav);
        TextView search=(TextView) findViewById(R.id.main_search);
        TextView shop=(TextView) findViewById(R.id.main_shop);


        // set onClickListener's for all the activities

        nowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NowPlaying.class);
                startActivity(intent);
            }
        });

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Favourites.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Shop.class);
                startActivity(intent);
            }
        });

    }
}
