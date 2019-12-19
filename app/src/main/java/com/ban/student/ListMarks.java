package com.ban.student;

public class ListMarks {
    String quiz1, quiz2, quiz3, quiz4, mid, semFinal;

    public ListMarks() {
    }

    public ListMarks(String quiz1, String quiz2, String quiz3, String quiz4, String mid, String semFinal) {
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.quiz4 = quiz4;
        this.mid = mid;
        this.semFinal = semFinal;
    }

    public String getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(String quiz1) {
        this.quiz1 = quiz1;
    }

    public String getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(String quiz2) {
        this.quiz2 = quiz2;
    }

    public String getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(String quiz3) {
        this.quiz3 = quiz3;
    }

    public String getQuiz4() {
        return quiz4;
    }

    public void setQuiz4(String quiz4) {
        this.quiz4 = quiz4;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getSemFinal() {
        return semFinal;
    }

    public void setSemFinal(String semFinal) {
        this.semFinal = semFinal;
    }
}
