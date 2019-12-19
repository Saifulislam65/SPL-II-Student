package com.ban.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityDeviceEnrollment extends AppCompatActivity {
    EditText id, deviceSecret;
    Button  enroll;
    DatabaseReference firebaseDatabase;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_enrollment);
        id = findViewById(R.id.enroll_with_device_id);
        deviceSecret = findViewById(R.id.enroll_with_device_secret_code);
        enroll = findViewById(R.id.enroll_with_device_button);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Device/");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edittextId = id.getText().toString();
                firebaseDatabase.child(deviceSecret.getText().toString()).child("StudentID").child(edittextId).setValue(firebaseUser.getUid());
            }
        });
    }
}
