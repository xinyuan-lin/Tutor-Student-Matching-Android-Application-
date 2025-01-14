package com.example.database.javabean;

import java.util.Date;

public class Schedule {
    private int schedule_id;
    private String start_time;
    private String end_time;
    private String subject;
    private int student_id;
    private int tutor_id;

    public Schedule(int schedule_id, String start_time, String end_time, String subject, int student_id, int tutor_id) {
        this.schedule_id = schedule_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.subject = subject;
        this.student_id = student_id;
        this.tutor_id = tutor_id;
    }

    public Schedule(String start_time, String end_time, String subject, int student_id, int tutor_id) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.subject = subject;
        this.student_id = student_id;
        this.tutor_id = tutor_id;
    }

    public Schedule() {

    }


    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(int tutor_id) {
        this.tutor_id = tutor_id;
    }
}

