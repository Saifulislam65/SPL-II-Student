package com.ban.student;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterAttendanceList extends RecyclerView.Adapter<AdapterAttendanceList.ViewHolder>{
        Context context;
        ArrayList<ListAttendance> attendance;

public AdapterAttendanceList(Context context, ArrayList<ListAttendance> attendance){
        this.context = context;
        this.attendance = attendance;
        }

@NonNull
@Override
public AdapterAttendanceList.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdapterAttendanceList.ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_attendance_list_recycleview, viewGroup, false));
        }

@Override
public void onBindViewHolder(@NonNull AdapterAttendanceList.ViewHolder viewHolder, final int i) {
        viewHolder.date.setText(attendance.get(i).getDate());
        viewHolder.attendanceStatus.setText(attendance.get(i).getAttendanceStatus());
        }

@Override
public int getItemCount() {
        return attendance.size();
        }

class ViewHolder extends RecyclerView.ViewHolder{
    TextView date, attendanceStatus;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.adapter_attendance_list_recyclerview_date);
        attendanceStatus = itemView.findViewById(R.id.adapter_attendance_list_recyclerview_status);
    }

}

}
