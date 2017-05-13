package com.example.android.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private EditText mEditText1;
    private EditText mEditText2;
    private CheckBox mCheckBox;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private RadioGroup mRadioGroup1;
    private RadioButton mRadioButton1;
    private RadioGroup mRadioGroup2;
    private RadioButton mRadioButton2;
// initialize variables for views
    private int myScore = 0;
// set initial score to zero
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateScore(View view) {
        mEditText1 = (EditText) findViewById(R.id.edit1);
        String string = mEditText1.getText().toString().toLowerCase();
        if (string.equals("dollar")) {
            myScore++;
        }
        // take input from views
        // convert it into lower case string
        // compare with required result
        mEditText2 = (EditText) findViewById(R.id.edit2);
        String string2 = mEditText2.getText().toString().toLowerCase();
        if (string2.equals("android")) {
            myScore++;
        }

        // get checkbox reference and increase score if checked is true
        mCheckBox = (CheckBox) findViewById(R.id.check);
        mCheckBox2 = (CheckBox) findViewById(R.id.check2);
        mCheckBox3 = (CheckBox) findViewById(R.id.check3);
        if (mCheckBox.isChecked()&&mCheckBox3.isChecked()&&(!mCheckBox2.isChecked())) {
            myScore++;
        }

        mRadioGroup1 = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = mRadioGroup1.getCheckedRadioButtonId();
        mRadioButton1 = (RadioButton) findViewById(selectedId);
        if (mRadioButton1 != null) {
            if (mRadioButton1.getText().toString().equals("Helena")) {
                myScore++;
            }
        }

        // select the radio group and get id of selected button
        // select text from button and compare with result

        mRadioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        int selectedId2 = mRadioGroup2.getCheckedRadioButtonId();
        mRadioButton2 = (RadioButton) findViewById(selectedId2);
        if (mRadioButton2 != null) {
            if ((mRadioButton2.getText().toString().equals("16"))) {
                myScore++;
            }
        }

        if(myScore==5){
            Toast toast = Toast.makeText(this, "Your answered all "+myScore+" correctly", Toast.LENGTH_LONG);
            toast.show();
        }
        // create a toast to display the result
        else {
            Toast toast = Toast.makeText(this, "Your Score is " + myScore, Toast.LENGTH_LONG);
            toast.show();
        }
        myScore = 0;
        // reset the score to zero
    }

}
