package com.ban.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityMarks extends AppCompatActivity {
    DatabaseReference getMarks, getRoll;
    TextView q1, q2, q3, q4, m, f;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        q1 = findViewById(R.id.marks_quiz1);
        q2 = findViewById(R.id.marks_quiz2);
        q3 = findViewById(R.id.marks_quiz3);
        q4 = findViewById(R.id.marks_quiz4);
        m = findViewById(R.id.marks_mid);
        f = findViewById(R.id.marks_final);

        final String courseKey = ActivityInsideCourses.courseCodeForQrGenerator;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        getMarks = FirebaseDatabase.getInstance().getReference("Course/"+courseKey+"/marks/"+firebaseUser.getUid()+"/sheet/");
        getMarks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListMarks listMarks = dataSnapshot.getValue(ListMarks.class);
                try{
                    q1.setText(listMarks.getQuiz1());
                    q2.setText(listMarks.getQuiz2());
                    q3.setText(listMarks.getQuiz3());
                    q4.setText(listMarks.getQuiz4());
                    m.setText(listMarks.getMid());
                    f.setText(listMarks.getSemFinal());
                }catch (NullPointerException e){
                    Toast.makeText(getApplicationContext(), "Null Pointer", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
