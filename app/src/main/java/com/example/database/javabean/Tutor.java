package com.example.database.javabean;

public class Tutor {
    private int tutor_id;
    private int user_id;
    private String intro;
    private String teach_exp;
    private String certificate;
    private String tutor_name;


    public Tutor(int tutor_id, int user_id, String intro, String teach_exp, String certificate, String tutor_name){
        this.tutor_id = tutor_id;
        this.user_id = user_id;
        this.intro = intro;
        this.teach_exp = teach_exp;
        this.certificate = certificate;
        this.tutor_name = tutor_name;
    }



    public Tutor(int tutorId, String name, String intro, String teachingExp, String certificate) {
        this.tutor_id = tutorId;
        this.tutor_name = name;
        this.intro = intro;
        this.teach_exp = teachingExp;
        this.certificate = certificate;
    }
    public Tutor(int tutorId, String name, String intro, String teachingExp) {
        this.tutor_id = tutorId;
        this.tutor_name = name;
        this.intro = intro;
        this.teach_exp = teachingExp;
    }

    public int getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(int tutor_id) {
        this.tutor_id = tutor_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTeach_exp() {
        return teach_exp;
    }

    public void setTeach_exp(String teach_exp) {
        this.teach_exp = teach_exp;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }
}
