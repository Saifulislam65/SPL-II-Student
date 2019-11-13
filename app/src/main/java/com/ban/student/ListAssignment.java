package com.ban.student;

public class ListAssignment {
    String a1_assignmentTitle, a2_assignmentDetails;

    public ListAssignment() {
    }

    public ListAssignment(String a1_assignmentTitle, String a2_assignmentDetails) {
        this.a1_assignmentTitle = a1_assignmentTitle;
        this.a2_assignmentDetails = a2_assignmentDetails;
    }

    public String getA1_assignmentTitle() {
        return a1_assignmentTitle;
    }

    public void setA1_assignmentTitle(String a1_assignmentTitle) {
        this.a1_assignmentTitle = a1_assignmentTitle;
    }

    public String getA2_assignmentDetails() {
        return a2_assignmentDetails;
    }

    public void setA2_assignmentDetails(String a2_assignmentDetails) {
        this.a2_assignmentDetails = a2_assignmentDetails;
    }
}
