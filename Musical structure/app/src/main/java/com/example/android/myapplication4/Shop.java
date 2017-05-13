package com.example.android.myapplication4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // find the button by it's id
        Button shopToMain = (Button) findViewById(R.id.shop_main);

        // set onClickListener to go to Main

        shopToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
