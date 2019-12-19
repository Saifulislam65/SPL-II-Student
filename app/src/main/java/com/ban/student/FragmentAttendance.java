package com.ban.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentAttendance extends Fragment {
    ArrayList<ListAttendance> arrayList;
    AdapterAttendanceList adapterAttendanceList;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    TextView progress;
    String attendanceProgress;
    RecyclerView recyclerView;
    int attendanceCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_attendance, container, false);
        progress = view.findViewById(R.id.attendance_progress);
        recyclerView = view.findViewById(R.id.attendance_list_recycleview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<ListAttendance>();
        final String courseKey = ActivityInsideCourses.courseCodeForQrGenerator;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();



        databaseReference = FirebaseDatabase.getInstance().getReference("Course/"+courseKey+"/a5_studentList/"+firebaseUser.getUid()+"/attendance/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();
                attendanceCounter = 0;
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String attendanceStatus = dataSnapshot1.getValue(String.class);
                    String date = dataSnapshot1.getKey();
                    attendanceCounter = attendanceCounter + Integer.parseInt(attendanceStatus);
                    ListAttendance listAttendance = new ListAttendance(date, attendanceStatus);
                    arrayList.add(listAttendance);
                    System.out.println("Date: "+listAttendance.getDate());
                    System.out.println("Attendance Status: "+listAttendance.getAttendanceStatus());
                }
                adapterAttendanceList = new AdapterAttendanceList(getContext(), arrayList);
                recyclerView.setAdapter(adapterAttendanceList);
                if(arrayList.size()!= 0){
                    attendanceProgress = Integer.toString((attendanceCounter*100)/arrayList.size());
                    progress.setText(attendanceProgress+"%");
                }
                else
                    progress.setText("NULL");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
