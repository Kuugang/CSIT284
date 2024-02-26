package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {
    Button btnHideButton;
    Button btnDisplayToast;
    Button btnOpenActivity;
    Button btnChangeBackground;
    Button btnChangeButtonColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);

        btnHideButton = (Button) findViewById(R.id.btnMakeInvisible);
        btnDisplayToast = (Button) findViewById(R.id.btnDisplayToast);
        btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
        btnChangeBackground = (Button) findViewById(R.id.changeBackgroundBtn);
        btnChangeButtonColor = (Button) findViewById(R.id.changeButtonColorBtn);

        btnHideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHideButton.setAlpha(0);
            }
        });

        btnDisplayToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "TEST", Toast.LENGTH_LONG).show();
            }
        });

        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ButtonNewActivity.class);
                startActivity(intent);
            }
        });

        btnChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActivityBackgroundColor(Color.GREEN);
            }
        });

        btnChangeButtonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnChangeButtonColor.setBackgroundColor(Color.BLACK);
            }
        });
    }

    private void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}