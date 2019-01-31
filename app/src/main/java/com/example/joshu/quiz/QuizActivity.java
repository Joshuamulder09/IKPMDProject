package com.example.joshu.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joshu.quiz.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizActivity extends AppCompatActivity {

    Button b1, b2, b3, b4;
    TextView t1_question, timerTxt;
    int total = 0;
    int correct = 0;
    int points = 0;
    DatabaseReference reference;
    int wrong = 0;
    long maxVragen = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        b1 = (Button) findViewById(R.id.buton1);
        b2 = (Button) findViewById(R.id.buton2);
        b3 = (Button) findViewById(R.id.buton3);
        b4 = (Button) findViewById(R.id.buton4);

        t1_question = (TextView) findViewById(R.id.questionsTxt);
        timerTxt = (TextView) findViewById(R.id.timerTxt);
        updateQuestion();
        reverseTimer(30, timerTxt);
    }


    public void updateQuestion() {
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxVragen = (dataSnapshot.getChildrenCount());
                total++;


                if (total > maxVragen) {
                    finish();
                    total--;
                    Intent i = new Intent(QuizActivity.this, ResultActivity.class);
                    i.putExtra("total", String.valueOf(total));
                    i.putExtra("correct", String.valueOf(correct));
                    i.putExtra("incorrect", String.valueOf(wrong));
                    i.putExtra("points", String.valueOf(points));
                    startActivity(i);

                } else {
                    reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total)); //vragen doorlopen database
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Question question = dataSnapshot.getValue(Question.class);
                            t1_question.setText(question.getQuestion());

                            b1.setText(question.getOption1());
                            b2.setText(question.getOption2());
                            b3.setText(question.getOption3());
                            b4.setText(question.getOption4());

                            b1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    b1.setEnabled(false);
                                    b2.setEnabled(false);
                                    b3.setEnabled(false);
                                    b4.setEnabled(false);
                                    if (b1.getText().toString().equals(question.getAnswer())) {
                                        b1.setBackgroundColor(Color.GREEN);

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                correct++;
                                                points = points + 15;
                                                b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                updateQuestion();
                                            }
                                        }, 1500);
                                    } else {
                                        // answer is wrong ... we will find the correct answer, and make it green
                                        wrong++;
                                        points = points - 5;
                                        b1.setBackgroundColor(Color.RED);

                                        if (b2.getText().toString().equals(question.getAnswer())) {
                                            b2.setBackgroundColor(Color.GREEN);
                                        } else if (b3.getText().toString().equals(question.getAnswer())) {
                                            b3.setBackgroundColor(Color.GREEN);
                                        } else if (b4.getText().toString().equals(question.getAnswer())) {
                                            b4.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                updateQuestion();
                                            }
                                        }, 1500);


                                    }
                                }
                            });

                            b2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    b1.setEnabled(false);
                                    b2.setEnabled(false);
                                    b3.setEnabled(false);
                                    b4.setEnabled(false);
                                    if (b2.getText().toString().equals(question.getAnswer())) {
                                        b2.setBackgroundColor(Color.GREEN);

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                correct++;
                                                points = points + 15;
                                                b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                updateQuestion();
                                            }
                                        }, 1500);
                                    } else {
                                        // answer is wrong ... we will find the correct answer, and make it green
                                        wrong++;
                                        points = points - 5;
                                        b2.setBackgroundColor(Color.RED);

                                        if (b1.getText().toString().equals(question.getAnswer())) {
                                            b1.setBackgroundColor(Color.GREEN);
                                        } else if (b3.getText().toString().equals(question.getAnswer())) {
                                            b3.setBackgroundColor(Color.GREEN);
                                        } else if (b4.getText().toString().equals(question.getAnswer())) {
                                            b4.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                updateQuestion();
                                            }
                                        }, 1500);


                                    }
                                }
                            });

                            b3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    b1.setEnabled(false);
                                    b2.setEnabled(false);
                                    b3.setEnabled(false);
                                    b4.setEnabled(false);
                                    if (b3.getText().toString().equals(question.getAnswer())) {
                                        b3.setBackgroundColor(Color.GREEN);

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                correct++;
                                                points = points + 15;
                                                b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                updateQuestion();
                                            }
                                        }, 1500);
                                    } else {
                                        // answer is wrong ... we will find the correct answer, and make it green
                                        wrong++;
                                        points = points - 5;
                                        b3.setBackgroundColor(Color.RED);

                                        if (b1.getText().toString().equals(question.getAnswer())) {
                                            b1.setBackgroundColor(Color.GREEN);
                                        } else if (b2.getText().toString().equals(question.getAnswer())) {
                                            b2.setBackgroundColor(Color.GREEN);
                                        } else if (b4.getText().toString().equals(question.getAnswer())) {
                                            b4.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                updateQuestion();
                                            }
                                        }, 1500);


                                    }
                                }
                            });

                            b4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    b1.setEnabled(false);
                                    b2.setEnabled(false);
                                    b3.setEnabled(false);
                                    b4.setEnabled(false);
                                    if (b4.getText().toString().equals(question.getAnswer())) {
                                        b4.setBackgroundColor(Color.GREEN);

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                correct++;
                                                points = points + 15;
                                                b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                updateQuestion();
                                            }
                                        }, 1500);
                                    } else {
                                        // answer is wrong ... we will find the correct answer, and make it green
                                        wrong++;
                                        points = points - 5;
                                        b4.setBackgroundColor(Color.RED);

                                        if (b1.getText().toString().equals(question.getAnswer())) {
                                            b1.setBackgroundColor(Color.GREEN);
                                        } else if (b2.getText().toString().equals(question.getAnswer())) {
                                            b2.setBackgroundColor(Color.GREEN);
                                        } else if (b3.getText().toString().equals(question.getAnswer())) {
                                            b3.setBackgroundColor(Color.GREEN);
                                        }

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                b4.setBackgroundColor(Color.parseColor("#03A9F4"));
                                                updateQuestion();
                                            }
                                        }, 1500);


                                    }
                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void reverseTimer(int seconds, final TextView tv) {
        new CountDownTimer(seconds * 1000 + 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish() {
                tv.setText("Completed");
                Intent myIntent = new Intent(QuizActivity.this, ResultActivity.class);
                myIntent.putExtra("total", String.valueOf(total));
                myIntent.putExtra("correct", String.valueOf(correct));
                myIntent.putExtra("incorrect", String.valueOf(wrong));
                myIntent.putExtra("points", String.valueOf(points));
                startActivity(myIntent);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
