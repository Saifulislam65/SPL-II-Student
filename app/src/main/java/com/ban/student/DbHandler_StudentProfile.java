package com.ban.student;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbHandler_StudentProfile {
    private DatabaseReference studentEmailUID;

    public void setStudentEmailUID(String SID, String UID){
        studentEmailUID = FirebaseDatabase.getInstance().getReference("Student/1_Student_Email_UID/"+SID);
        studentEmailUID.setValue(UID);
    }
}
