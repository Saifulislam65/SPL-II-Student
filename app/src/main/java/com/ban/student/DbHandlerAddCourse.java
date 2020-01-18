package com.ban.student;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DbHandlerAddCourse {
    private DatabaseReference databaseReference, setInStudentRepo, setStudentInAttendance, setStudentInMarks, enrollmentMode;
    String mode ="Enrollment is turned off!";

    public String getEnrollmentMode(String courseKey){
        enrollmentMode= FirebaseDatabase.getInstance().getReference("Course/"+courseKey+"/a5_courseEnrollmentMode");
        enrollmentMode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    mode = dataSnapshot.getValue(String.class).trim();
                    System.out.println("Inside Mode: "+mode);
                }catch (Exception e){
                    mode = "Enrollment Denied!";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mode = "Enrollment Off!";
            }
        });
        return mode;
    }
}
