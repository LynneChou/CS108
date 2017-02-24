package edu.stanford.cs108.shippingcalculator;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateCost(View view) {
        // First, get user input
        EditText et = (EditText) findViewById(R.id.weight);
        System.out.println("Let me see:" + et.getText().toString());
        if(et.getText().toString().equals("")) {
            return;
        }
        double w = Double.parseDouble(et.getText().toString());
        // Second, calculate cost
        int times = 0;
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        int currentCheck = rg.getCheckedRadioButtonId();
        switch(currentCheck) {
            case R.id.next:
                times = 10;
                break;
            case R.id.second:
                times = 5;
                break;
            case R.id.standard:
                times = 3;
                break;
        }
        double cost = w * times;
        // Third, include insurance
        Long total = (long) 0;
        CheckBox cb = (CheckBox) findViewById(R.id.checkbox);
        if(cb.isChecked()) {
            total = Math.round(cost * 1.2);
        } else {
            total = Math.round(cost);
        }
        // Fourth, show the total
        TextView tv = (TextView) findViewById(R.id.tbd);
        tv.setText("$" + total.toString());
    }
}
