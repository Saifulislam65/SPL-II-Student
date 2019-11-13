package com.ban.student;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSearchAssignment extends
        RecyclerView.Adapter<AdapterSearchAssignment.ViewHolder>{
    Context context;
    ArrayList<ListAssignment> assignments;

    public AdapterSearchAssignment(Context context, ArrayList<ListAssignment> assignments){
        this.context = context;
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public AdapterSearchAssignment.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterSearchAssignment.ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_assignment_recycleview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearchAssignment.ViewHolder viewHolder, final int i) {
        viewHolder.assignmentTitle.setText(assignments.get(i).getA1_assignmentTitle());
        viewHolder.assignmentDetails.setText(assignments.get(i).getA2_assignmentDetails());
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView assignmentTitle, assignmentDetails;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentTitle = itemView.findViewById(R.id.adapter_assignment_recyclerview_assignment_title);
            assignmentDetails = itemView.findViewById(R.id.adapter_assignment_recyclerview_assignment_details);
        }

    }

}
