package com.example.joshu.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.joshu.quiz.Model.Question;

import java.util.List;

public class deleteQuestionActivity extends AppCompatActivity {
    private Button mDelete_btn;
    private Button mTerug_btn;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_question);

        mDelete_btn = (Button) findViewById(R.id.delete_btn);
        mTerug_btn = (Button) findViewById(R.id.terug_btn);
        key = getIntent().getStringExtra("key");

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteQuestion(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Question> questions, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {


                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(deleteQuestionActivity.this, "Alle vragen zijn verwijderd!!", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });

        mTerug_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish(); return;
            }
        });




    }
}
