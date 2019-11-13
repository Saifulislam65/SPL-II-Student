package com.ban.student;

class ListStudentProfileInfo {
    String email, studentName, studentID;

    public ListStudentProfileInfo() {
    }

    public ListStudentProfileInfo(String email, String studentName, String studentID) {
        this.email = email;
        this.studentName = studentName;
        this.studentID = studentID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
