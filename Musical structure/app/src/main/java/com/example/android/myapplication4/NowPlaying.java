package com.example.android.myapplication4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NowPlaying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        // find the buttons by their id

        Button nowToMain = (Button) findViewById(R.id.now_main);
        Button nowToShop = (Button) findViewById(R.id.now_shop);

        // set onClickListener's for all the activities

        nowToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NowPlaying.this,MainActivity.class);
                startActivity(intent);
            }
        });
        nowToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NowPlaying.this,Shop.class);
                startActivity(intent);
            }
        });
    }
}
