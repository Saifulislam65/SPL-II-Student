package com.ban.student;

public class ListAttendance {
    String date, attendanceStatus;

    public ListAttendance() {
    }

    public ListAttendance(String date, String attendanceStatus) {
        this.date = date;
        this.attendanceStatus = attendanceStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
