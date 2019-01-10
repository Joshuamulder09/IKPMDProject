package com.example.joshu.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        naarActivity();
    }

    public void naarActivity() {
        Button buttonOverOns = (Button) findViewById(R.id.buttonOverOns);
        buttonOverOns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OverOns.class));
            }
        });

        Button buttonSpelregels = (Button) findViewById(R.id.buttonSpelregels);
        buttonSpelregels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), spelregels.class));
            }
        });
    }
}
