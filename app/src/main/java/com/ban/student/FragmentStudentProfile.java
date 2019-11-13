package com.ban.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FragmentStudentProfile extends Fragment {
    DatabaseReference studentProfileData;
    FirebaseUser firebaseUser;
    TextView textViewEmail;
    EditText studentName, studentID;
    Button update;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_student_profile, container, false);
        textViewEmail = view.findViewById(R.id.student_profile_email);
        studentName = view.findViewById(R.id.student_profile_name);
        studentID = view.findViewById(R.id.student_profile_id);
        update = view.findViewById(R.id.student_profile_button_update);

        String key = showUserEmail();
        studentProfileData = FirebaseDatabase.getInstance().getReference("Student/"+key+"/PersonalInfo");

        studentProfileData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    ListStudentProfileInfo listTeacherProfileInfo = dataSnapshot.getValue(ListStudentProfileInfo.class);
                    studentName.setText(listTeacherProfileInfo.getStudentName());
                    studentID.setText(listTeacherProfileInfo.getStudentID());

                }catch (Exception e){
                    Toast.makeText(getContext(), "Ops...something is wrong!", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Ops...something is wrong!", Toast.LENGTH_LONG);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListStudentProfileInfo info = new ListStudentProfileInfo(
                        textViewEmail.getText().toString(),
                        studentName.getText().toString(),
                        studentID.getText().toString()
                );

                studentProfileData.setValue(info);
            }
        });


        return view;
    }

    private String showUserEmail() {
        String  email = "User E-mail";
        String providerId = null;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            email = firebaseUser.getEmail();
            textViewEmail.setText(email);
            providerId = firebaseUser.getUid();
            System.out.println("Inside Student Profile: "+providerId);
        }
        return providerId;
    }
}
