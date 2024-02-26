package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnLayoutExercise;
    Button btnButtonExercise;
    Button btnCalculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLayoutExercise = (Button) findViewById(R.id.btnLayoutExercise);
        btnButtonExercise = (Button) findViewById(R.id.btnButtonExercise);
        btnCalculator = (Button) findViewById(R.id.btnCalculator);

        btnLayoutExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LayoutExercise.class);
                startActivity(intent);
            }
        });
        btnButtonExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ButtonExercise.class);
                startActivity(intent);
            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });
    }
}