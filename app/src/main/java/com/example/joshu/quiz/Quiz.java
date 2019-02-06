package com.example.joshu.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        Button buttonVragen = (Button) findViewById(R.id.buttonVragen);
        buttonVragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangeQuestionsActivity.class));
            }
        });

        Button buttonVragenToevoegen = (Button) findViewById(R.id.buttonVragenToevoegen);
        buttonVragenToevoegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MakeQuiz.class));
            }
        });


    }


}
