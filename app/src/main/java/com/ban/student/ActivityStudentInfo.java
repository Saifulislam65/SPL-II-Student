package com.ban.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityStudentInfo extends AppCompatActivity {
    DatabaseReference studentProfileData;
    FirebaseUser firebaseUser;
    TextView textViewEmail;
    EditText studentName, studentID;
    Button update;
    private DbHandler_StudentProfile  dbHandler_studentProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        textViewEmail = findViewById(R.id.student_profile_email);
        studentName = findViewById(R.id.student_profile_name);
        studentID = findViewById(R.id.student_profile_id);
        update = findViewById(R.id.student_profile_button_update);


        dbHandler_studentProfile = new DbHandler_StudentProfile();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        textViewEmail.setText(firebaseUser.getEmail());
        studentProfileData = FirebaseDatabase.getInstance().getReference("Student/"+firebaseUser.getUid()+"/PersonalInfo");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentID.getText().toString().trim().length()==9){
                    if(studentName.getText().toString().trim().length()<2){
                        studentName.setText("");
                        studentName.setHint("Invalid Name");
                    }else {
                        ListStudentProfileInfo info = new ListStudentProfileInfo(
                                firebaseUser.getEmail(),
                                studentName.getText().toString(),
                                studentID.getText().toString()
                        );
                        dbHandler_studentProfile.setStudentEmailUID(studentID.getText().toString(), firebaseUser.getUid());
                        studentProfileData.setValue(info);
                    }


                }else {
                    studentID.setText("");
                    studentID.setHint("Invalid ID");
                }
            }
        });
    }
}
