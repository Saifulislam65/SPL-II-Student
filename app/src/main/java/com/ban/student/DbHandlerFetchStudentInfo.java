package com.ban.student;

import com.google.firebase.database.DatabaseReference;

public class DbHandlerFetchStudentInfo {
    String studentEmailUID;
    private DatabaseReference studentData;

    public DbHandlerFetchStudentInfo(String studentEmailUID) {
        this.studentEmailUID = studentEmailUID;

    }

    public String getName(){

        return null;
    }

}
