package com.ban.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentTeacherProfile extends Fragment {

    DatabaseReference databaseReference, fetchTeacherProfile;
    ListTeacherProfileInfo teacherProfileInfo;
    TextView email, teacherName, teacherInstitute;
    ListGetTeacherID getteacherID;
    String teacherID = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_teacher_profile, container, false);
        email = view.findViewById(R.id.teacher_profile_email);
        teacherName = view.findViewById(R.id.teacher_profile_name);
        teacherInstitute = view.findViewById(R.id.teacher_profile_institute);

        databaseReference = FirebaseDatabase.getInstance()
                .getReference(ActivityInsideCourses.coursePath).child("a4_teacherID");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getteacherID = dataSnapshot.getValue(ListGetTeacherID.class);
                teacherID = getteacherID.getA4_teacherID();
                System.out.println("Teacher ID: "+ teacherID);
                fetchTeacherProfile = FirebaseDatabase.getInstance().getReference("Teacher/"+ teacherID+"/PersonalInfo");
                fetchTeacherProfile.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ListTeacherProfileInfo teacherProfileInfo = dataSnapshot.getValue(ListTeacherProfileInfo.class);
                        System.out.println("Teacher Email: "+teacherProfileInfo.getEmail());
                        try {
                            email.setText(teacherProfileInfo.getEmail());
                            teacherName.setText(teacherProfileInfo.getTeacherName());
                            teacherInstitute.setText(teacherProfileInfo.getTeacherInstitute());

                        }catch (NullPointerException e){
                            Toast.makeText(getContext(), "Teacher has not completed his profile", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
