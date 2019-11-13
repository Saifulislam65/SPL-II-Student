package com.ban.student;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentDashboard extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_dashboard, container, false);

        CardView card_assignment = view.findViewById(R.id.card_assignment);
        CardView card_marks = view.findViewById(R.id.card_marks);
        CardView card_qrCode = view.findViewById(R.id.card_QR_Code);

        card_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityAssignment.class);
                intent.putExtra("CoursePath", ActivityInsideCourses.coursePath);
                startActivity(intent);
            }
        });

        card_marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityMarks.class);
                startActivity(intent);

            }
        });

        card_qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityQRcodeGenerator.class);
                startActivity(intent);

            }
        });

        return view;
    }
}
