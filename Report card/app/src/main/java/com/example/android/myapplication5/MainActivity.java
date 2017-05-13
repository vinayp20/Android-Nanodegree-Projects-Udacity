package com.example.android.myapplication5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReportCard mRC=new ReportCard('A','B','C');
        Log.d(MainActivity.class.getSimpleName(),mRC.toString());

    }
}
