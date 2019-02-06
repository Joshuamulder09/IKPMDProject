package com.example.joshu.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.joshu.quiz.Model.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChangeQuestionsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_questions);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_questions);
        new FirebaseDatabaseHelper().readQuestions(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Question> questions, List<String> keys) {
                findViewById(R.id.loading_questions_pb).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView, ChangeQuestionsActivity.this, questions, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.questionlist_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_question:
                startActivity(new Intent(getApplicationContext(), NewQuestionActivity.class));
                return true;
            case R.id.delete_quiz:
                startActivity(new Intent(getApplicationContext(), deleteQuestionActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
