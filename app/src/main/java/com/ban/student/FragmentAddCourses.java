package com.ban.student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class FragmentAddCourses extends Fragment {

    private DatabaseReference databaseReference, setInStudentRepo, setStudentInAttendance, setStudentInMarks, enrollmentMode;
    private DbHandlerAddCourse dbHandlerAddCourse;
    String mode ="";
    int status = 0;
    FirebaseUser firebaseUser;
    Button addCourse, addCourse2;
    EditText coursePassword;
    String code;
    ListCourse listCourse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_add_courses, container, false);
        addCourse = view.findViewById(R.id.add_course);
        coursePassword = view.findViewById(R.id.course_password);

        /*dbHandlerAddCourse = new DbHandlerAddCourse();*/

        addCourse2  = view.findViewById(R.id.add_course_2);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
                    startActivityForResult(intent, 0);

                } catch (Exception e) {

                    Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
                    startActivity(marketIntent);

                }
            }
        });


        addCourse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = coursePassword.getText().toString();
                setStudentInAttendance = FirebaseDatabase.getInstance().getReference("Course/"+code+"/attendance/"+showUid()+"/");
                setStudentInMarks = FirebaseDatabase.getInstance().getReference("Course/"+code+"/marks/"+showUid()+"/");
                /*String mode = dbHandlerAddCourse.getEnrollmentMode(code);*/
               if(!previouslyEnrolled()){
                   enrollmentMode= FirebaseDatabase.getInstance().getReference("Course/"+code+"/a5_courseEnrollmentMode");
                   enrollmentMode.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           try {
                               status = dataSnapshot.getValue(Integer.class);

                               System.out.println("Inside Mode: "+mode +"/"+ mode.length());

                               if(status == 0){
                                   Toast.makeText(getContext(), "Enrollment Closed!", Toast.LENGTH_SHORT).show();
                               } else  if(status == 1){
                                   createCourseMethod();
                               } else{
                                   Toast.makeText(getContext(), "Mode: "+mode, Toast.LENGTH_SHORT).show();
                               }

                           }catch (Exception e){
                               mode = "Enrollment Denied!";
                               Toast.makeText(getContext(), ""+mode, Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {
                           mode = "Enrollment Off!";
                           Toast.makeText(getContext(), ""+mode, Toast.LENGTH_SHORT).show();
                       }
                   });
               }


                coursePassword.setText("");
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                code = data.getStringExtra("SCAN_RESULT");
                coursePassword.setText(code);
            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }

    private void createCourseMethod() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Course").child(code);
        setInStudentRepo = FirebaseDatabase.getInstance().getReference("Student/"+showUid()+"/"+"CourseList/"+code+"/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCourse = dataSnapshot.getValue(ListCourse.class);
                ListSetStudentnCourseRepo setStudentnCourseRepo =
                        new ListSetStudentnCourseRepo(firebaseUser.getEmail(),
                         showUid());
                try{
                    System.out.println(listCourse.getA1_courseName());
                    System.out.println(listCourse.getA2_courseCode());
                    setInStudentRepo.setValue(listCourse);
                    setStudentInAttendance.setValue(setStudentnCourseRepo);
                    setStudentInMarks.setValue(setStudentnCourseRepo);
                    Toast.makeText(getContext(), "Enrollment Done"+mode, Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    Toast.makeText(getContext(), "Invalid Course", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Ops...something is wrong!", Toast.LENGTH_LONG).show();
            }
        });

    }


    private String showUid() {
        String Uid = null;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            Uid = firebaseUser.getUid();
            System.out.println("Inside Create Course: "+Uid);
        }
        return Uid;
    }

    private boolean previouslyEnrolled(){
        int loopSize = FragmentCourses.parentList.size();

        for(int i = 0; i<loopSize; i++){
            if(FragmentCourses.parentList.get(i).equals(coursePassword.getText().toString()))
                return true;
        }
        return false;
    }
}

