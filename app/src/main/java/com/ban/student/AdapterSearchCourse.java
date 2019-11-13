package com.ban.student;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSearchCourse extends
    RecyclerView.Adapter<AdapterSearchCourse.ViewHolder> {
    Context context;
    ArrayList<ListCourse> courses;

    public AdapterSearchCourse(Context context, ArrayList<ListCourse> courses){
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public AdapterSearchCourse.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_course_recyclerview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearchCourse.ViewHolder viewHolder, final int i) {
        viewHolder.courseName.setText(courses.get(i).getA1_courseName());
        viewHolder.courseCode.setText(courses.get(i).getA2_courseCode());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ActivityInsideCourses.class);
                System.out.println(i);
                String x = Integer.toString(i);
                intent.putExtra("STRING_I_NEED",x);
                System.out.println("On recycle item click: "+i);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView courseName, courseCode;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.adapter_course_recyclerview_course_name);
            courseCode = itemView.findViewById(R.id.adapter_course_recyclerview_course_code);
        }

    }

}
