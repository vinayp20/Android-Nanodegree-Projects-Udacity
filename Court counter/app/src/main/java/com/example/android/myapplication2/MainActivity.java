
package com.example.android.myapplication2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int foulsTeamA = 0;
    int foulsTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }


    public void setFoulsTeamA(View v) {
        foulsTeamA = foulsTeamA + 1;
        displayFoulsTeamA(foulsTeamA);
    }

    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }


    public void setFoulsTeamB(View v) {
        foulsTeamB = foulsTeamB + 1;
        displayFoulsTeamB(foulsTeamB);
    }


    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        foulsTeamA = 0;
        foulsTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayFoulsTeamA(foulsTeamA);
        displayFoulsTeamB(foulsTeamB);
    }


    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }


    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayFoulsTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_fouls);
        scoreView.setText(String.valueOf(score));
    }
    public void displayFoulsTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_fouls);
        scoreView.setText(String.valueOf(score));
    }

}
