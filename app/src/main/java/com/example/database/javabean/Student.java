package com.example.database.javabean;

public class Student {
    private int student_id;
    private int user_id;
    private String interest;
    private String education;


    public Student(int studentid, int userId, String intrst, String edu){
        this.student_id = studentid;
        this.user_id = userId;
        this.interest = intrst;
        this.education = edu;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
