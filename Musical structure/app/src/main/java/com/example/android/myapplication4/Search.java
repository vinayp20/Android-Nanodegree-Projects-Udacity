package com.example.android.myapplication4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        // find the buttons by their id

        Button searchToMain= (Button) findViewById(R.id.search_main);
        Button searchToNow= (Button) findViewById(R.id.search_now);
        Button searchToShop= (Button) findViewById(R.id.search_shop);

        // set onClickListener's for all the activities

        searchToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this,MainActivity.class);
                startActivity(intent);
            }
        });
        searchToNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this,NowPlaying.class);
                startActivity(intent);
            }
        });
        searchToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this,Shop.class);
                startActivity(intent);
            }
        });
    }
}
