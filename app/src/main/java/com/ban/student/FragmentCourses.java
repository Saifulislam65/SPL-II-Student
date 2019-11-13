package com.ban.student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentCourses extends Fragment {
    private RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<ListCourse> arrayList ;
    public static List<String> parentList = null;
    AdapterSearchCourse adapterSearchCourse;
    ProgressBar progressBar;
    String courseStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_courses, container, false);
        recyclerView = view.findViewById(R.id.show_courses);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<ListCourse>();
        parentList = new ArrayList<String>();

        courseStore = showUid();

        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Course");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Student/"+courseStore+"/CourseList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setIndeterminate(true);

                arrayList.clear();
                parentList.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    try{
                        ListCourse listCourse = dataSnapshot1.getValue(ListCourse.class);
                        arrayList.add(listCourse);
                        parentList.add(dataSnapshot1.getKey());
                        System.out.println(dataSnapshot1.getKey());
                    }catch (Exception e){
                        Toast.makeText(getContext(), "Ops...something is wrong!", Toast.LENGTH_LONG).show();
                    }

                }
                adapterSearchCourse = new AdapterSearchCourse(getContext(), arrayList);
                recyclerView.setAdapter(adapterSearchCourse);

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Ops...something is wrong!", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    public List getParentList(){
        return parentList;
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

}
