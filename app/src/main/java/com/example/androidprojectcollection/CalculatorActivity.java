package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {

    int i;
    float value1 = 0;
    float value2 = 0;


    ArrayList<Button> numbers = new ArrayList<>();
    ArrayList<Button> operations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calculate();
    }


    public void calculate(){
        Button clearBtn =  (Button) findViewById(R.id.clearBtn);
        Button equalBtn =  (Button) findViewById(R.id.equalBtn);
        TextView result =  (TextView) findViewById(R.id.resultText);
        value1 = 0;
        value2 = 0;
        result.setText("");

        numbers.add( (Button) findViewById(R.id.zeroBtn));
        numbers.add( (Button) findViewById(R.id.oneBtn));
        numbers.add( (Button) findViewById(R.id.twoBtn));
        numbers.add( (Button) findViewById(R.id.threeBtn));
        numbers.add( (Button) findViewById(R.id.fourBtn));
        numbers.add( (Button) findViewById(R.id.fiveBtn));
        numbers.add( (Button) findViewById(R.id.sixBtn));
        numbers.add( (Button) findViewById(R.id.sevenBtn));
        numbers.add( (Button) findViewById(R.id.eighthBtn));
        numbers.add( (Button) findViewById(R.id.nineBtn));

        operations.add((Button) findViewById(R.id.plusBtn));
        operations.add((Button) findViewById(R.id.minusBtn));
        operations.add((Button) findViewById(R.id.multiplyBtn));
        operations.add((Button) findViewById(R.id.divideBtn));

        for(int i = 0; i < numbers.size(); i++){
            numbers.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button) view;
                    String buttonText = b.getText().toString();
                    result.append(buttonText);
                }
            });
        }

        for(int i = 0; i < operations.size(); i++){
            operations.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button) view;
                    String buttonText = b.getText().toString();
                    String current = "" + result.getText();

                    Character last = current.charAt(current.length() - 1);
                    if(last == '+' || last == '-' || last == 'X' || last == '/'){
                        current = current.substring(0, current.length() - 1) + buttonText;
                        result.setText(current);
                    }else{
                        result.append(buttonText);
                    }
                }
            });
        }
//        equalBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String input = "" + result.getText();
//                value2 = Float.parseFloat(input);
//
//                float calculateResult = value1 + value2;
//                result.setText(calculateResult + "");
//            }
//        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("");
            }
        });
    }
}