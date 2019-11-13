package com.ban.student;

public class ListCourse {
    String a1_courseName, a2_courseCode;

    public ListCourse() {
    }

    public ListCourse(String courseName, String courseCode) {
        this.a1_courseName = courseName;
        this.a2_courseCode = courseCode;
    }

    public String getA1_courseName() {
        return a1_courseName;
    }

    public void setA1_courseName(String a1_courseName) {
        this.a1_courseName = a1_courseName;
    }

    public String getA2_courseCode() {
        return a2_courseCode;
    }

    public void setA2_courseCode(String a2_courseCode) {
        this.a2_courseCode = a2_courseCode;
    }

}
