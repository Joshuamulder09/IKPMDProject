package com.example.joshu.quiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joshu.quiz.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewQuestionActivity extends AppCompatActivity {
    private EditText mQuestion_editTxt;
    private EditText mAnswer_editTxt;
    private EditText mOption1_editTxt;
    private EditText mOption2_editTxt;
    private EditText mOption3_editTxt;
    private EditText mOption4_editTxt;
    private Button mAdd_btn;
    private Button mBack_btn;
    DatabaseReference reference;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);
        mQuestion_editTxt = (EditText) findViewById(R.id.question_editTxt);
        mAnswer_editTxt = (EditText) findViewById(R.id.answer_editTxt);
        mOption1_editTxt = (EditText) findViewById(R.id.option1_editTxt);
        mOption2_editTxt = (EditText) findViewById(R.id.option2_editTxt);
        mOption3_editTxt = (EditText) findViewById(R.id.option3_editTxt);
        mOption4_editTxt = (EditText) findViewById(R.id.option4_editTxt);


        mAdd_btn = (Button) findViewById(R.id.update_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);

        reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Question question = new Question();
                question.setQuestion(mQuestion_editTxt.getText().toString());
                question.setAnswer(mAnswer_editTxt.getText().toString());
                question.setOption1(mOption1_editTxt.getText().toString());
                question.setOption2(mOption2_editTxt.getText().toString());
                question.setOption3(mOption3_editTxt.getText().toString());
                question.setOption4(mOption4_editTxt.getText().toString());
                reference.child(String.valueOf(maxid + 1)).setValue(question);
                Toast.makeText(NewQuestionActivity.this, maxid + " Vraag toegevoegd aan database", Toast.LENGTH_LONG).show();
                finish();return;
            }
        });

        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish(); return;
    }

}

