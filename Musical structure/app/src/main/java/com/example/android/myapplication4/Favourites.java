package com.example.android.myapplication4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Favourites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        // find the buttons by their id

        Button favToMain = (Button) findViewById(R.id.fav_main);
        Button favToNow = (Button) findViewById(R.id.fav_now);
        Button favToShop = (Button) findViewById(R.id.fav_shop);

        // set onClickListener's for all the activities

        favToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favourites.this,MainActivity.class);
                startActivity(intent);
            }
        });
        favToNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favourites.this,NowPlaying.class);
                startActivity(intent);
            }
        });
        favToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favourites.this,Shop.class);
                startActivity(intent);
            }
        });
    }
}
