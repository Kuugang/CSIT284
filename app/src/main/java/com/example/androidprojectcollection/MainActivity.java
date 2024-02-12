package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Button LayoutExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutExercise = (Button) findViewById(R.id.layoutexercise);
        LayoutExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLayoutExercise();
            }
        });
    }

    public void openLayoutExercise(){
        Intent intent = new Intent(this, LayoutExercise.class);
        startActivity(intent);
    }
}