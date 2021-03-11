package com.example.tipcalculator_suzara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayResult extends AppCompatActivity {

    TextView display_result, bill_total, tip_total, tip_per_person, total_per_person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_result);

        display_result = findViewById(R.id.bill_total);
        Intent intent = getIntent();
        String checkAmount = intent.getExtras().getString("checkAmount");
//        String tipPercentage = intent.getExtras().getString("tipPercentage");
        String tipAmount = intent.getExtras().getString("tipAmount");
        String tipPerPerson = intent.getExtras().getString("tipPerPerson");
//        String totalSum = intent.getExtras().getString("totalSum");
        String totalPerPerson = intent.getExtras().getString("totalPerPerson");

//        display_result.setText(checkAmount);
        String formatted = getString(R.string.bill, checkAmount);

//        text = String.format(getResources().getString(R.string.tip_total), tipAmount);
//        text = String.format(getResources().getString(R.string.tip_per_person), tipPerPerson);
//        text = String.format(getResources().getString(R.string.total_per_person), totalPerPerson);

    }
}