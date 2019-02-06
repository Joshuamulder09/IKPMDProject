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

public class MakeQuiz extends AppCompatActivity {
    EditText txtQuestion, txtAnswer1, txtAnswer2, txtAnswer3, txtAnswer4, txtCorrectAnswer;
    Button btnSave;
    DatabaseReference reference;
    Question question;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_quiz);
        txtQuestion = (EditText) (findViewById(R.id.question));
        txtAnswer1 = (EditText) (findViewById(R.id.answer1));
        txtAnswer2 = (EditText) (findViewById(R.id.answer2));
        txtAnswer3 = (EditText) (findViewById(R.id.answer3));
        txtAnswer4 = (EditText) (findViewById(R.id.answer4));
        txtCorrectAnswer = (EditText) (findViewById(R.id.correctAnswer));
        btnSave = (Button) (findViewById(R.id.save));
        question = new Question();



        reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setQuestion(txtQuestion.getText().toString());
                question.setOption1(txtAnswer1.getText().toString());
                question.setOption2(txtAnswer2.getText().toString());
                question.setOption3(txtAnswer3.getText().toString());
                question.setOption4(txtAnswer4.getText().toString());
                question.setAnswer(txtCorrectAnswer.getText().toString());
                reference.child(String.valueOf(maxid + 1)).setValue(question);
                Toast t = Toast.makeText(MakeQuiz.this,  maxid + " Vraag toegevoegd aan database", Toast.LENGTH_LONG);
                t.show();
                startActivity(new Intent(getApplicationContext(), MakeQuiz.class));
            }
        });
    }
}
