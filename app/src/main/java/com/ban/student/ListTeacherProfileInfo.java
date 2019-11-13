package com.ban.student;

public class ListTeacherProfileInfo {
    String email, teacherName, teacherInstitute;

    public ListTeacherProfileInfo() {
    }

    public ListTeacherProfileInfo(String email, String teacherName, String teacherInstitute) {
        this.email = email;
        this.teacherName = teacherName;
        this.teacherInstitute = teacherInstitute;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherInstitute() {
        return teacherInstitute;
    }

    public void setTeacherInstitute(String teacherInstitute) {
        this.teacherInstitute = teacherInstitute;
    }
}
