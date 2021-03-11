package com.example.tipcalculator_suzara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // buttons
    Button btn_calculate, btn_reset;

    // edit text fields
    private EditText check_amount_field, number_people_field, custom_tip;

    // textview fields
    private TextView result;

    // intent to display results
    private Intent display;

    // RadioGroup for tip select
    private RadioGroup tip_group;

//    private RadioButton radio_btn_15, radio_btn_20, radio_btn_25;

    // user inputs
    private int numPeopleInput;
    private double checkAmount, tipPercentage, tipAmount, tipPerPerson, totalPerPerson, totalSum;

    // tip constants
    int FIFTEEN_PERCENT = 15;
    int TWENTY_PERCENT = 20;
    int TWENTY_FIVE_PERCENT = 25;

    // booleans for input validation
//    private boolean checkInput, peopleInput, customInput = false;

    // function to check required fields
    private void validateInput() {
        if (!check_amount_field.getText().toString().isEmpty()
                && !number_people_field.getText().toString().isEmpty()
                && tipPercentage > 1) {
            btn_calculate.setEnabled(true);
        } else {
            btn_calculate.setEnabled(false);
        }
    }

    // error alerts
    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(errorMessage)
                .setNeutralButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        findViewById(fieldId).requestFocus();
                    }
                }).show();
    }

//     check the bill amount
//    public boolean validateInput(String bill, String numPeople, String customTip) {
//        if (bill.isEmpty() || numPeople.isEmpty()) {
//            return false;
//        } else {
//            return Double.parseDouble(bill) > 1 && Integer.parseInt(numPeople) > 1
//                    && customTip.isEmpty() || customTip.equals("") || Integer.parseInt(String.valueOf(custom_tip)) > 1;
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EditText ids
        check_amount_field = findViewById(R.id.check_amount);
        number_people_field = findViewById(R.id.number_of_people);
        custom_tip = findViewById(R.id.custom_tip_edit);
//        check_amount_field.setText("");
//        number_people_field.setText("");
//        custom_tip.setText("");


        // TextView ids
        result = findViewById(R.id.result);
//        result.setText("");
        result.setVisibility(View.INVISIBLE);

        // RadioGroup ids
//        radio_btn_15 = findViewById(R.id.fifteen_percent);
//        radio_btn_20 = findViewById(R.id.twenty_percent);
//        radio_btn_25 = findViewById(R.id.twenty_five_percent);
        tip_group = findViewById(R.id.tip_group);


        // button ids
        btn_reset = findViewById(R.id.reset_button);
        btn_calculate = findViewById(R.id.calculate_button);


        display = new Intent(this, DisplayResult.class);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tip_group.clearCheck();
                check_amount_field.setText("");
                number_people_field.setText("");
                custom_tip.setText("");
                custom_tip.setVisibility(View.INVISIBLE);
                btn_calculate.setEnabled(false);
            }
        });

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAmount = Double.parseDouble(check_amount_field.getText().toString());
                numPeopleInput = Integer.parseInt(number_people_field.getText().toString());
                tipAmount = checkAmount * (tipPercentage / 100);
                tipPerPerson = tipAmount / numPeopleInput;
                totalSum = checkAmount + tipAmount;
                totalPerPerson = totalSum / numPeopleInput;
                display.putExtra("checkAmount", check_amount_field.getText().toString());
                display.putExtra("tipPercentage", String.valueOf(tipPercentage));
                display.putExtra("tipAmount", String.valueOf(tipAmount));
                display.putExtra("tipPerPerson", String.valueOf(tipPerPerson));
                display.putExtra("totalSum", String.valueOf(totalSum));
                display.putExtra("totalPerPerson", String.valueOf(totalPerPerson));
                startActivity(display);

            }
        });

        check_amount_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInput();
                if (Double.parseDouble(check_amount_field.getText().toString()) <= 1)
                    showErrorAlert(getString(R.string.errorBillAmount), check_amount_field.getId());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        number_people_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInput();
                if (Integer.parseInt(number_people_field.getText().toString()) < 1)
                    showErrorAlert(getString(R.string.errorBillAmount), number_people_field.getId());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        custom_tip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInput();
                if (Integer.parseInt(custom_tip.getText().toString()) < 1)
                    showErrorAlert(getString(R.string.errorBillAmount), custom_tip.getId());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tip_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fifteen_percent:
                        custom_tip.setVisibility(View.INVISIBLE);
                        tipPercentage = FIFTEEN_PERCENT;
//                        customInput = false;
//                        Toast.makeText(getApplicationContext(), String.valueOf(tipPercentage), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.twenty_percent:
                        custom_tip.setVisibility(View.INVISIBLE);
                        tipPercentage = TWENTY_PERCENT;
//                        customInput = false;
                        Toast.makeText(getApplicationContext(), String.valueOf(tipPercentage), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.twenty_five_percent:
                        custom_tip.setVisibility(View.INVISIBLE);
                        tipPercentage = TWENTY_FIVE_PERCENT;
//                        customInput = false;
                        Toast.makeText(getApplicationContext(), String.valueOf(tipPercentage), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.custom_tip_button:
                        custom_tip.setVisibility(View.VISIBLE);
                        tipPercentage = 0;
//                        customInput = false;
                        Toast.makeText(getApplicationContext(), String.valueOf(tipPercentage), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}