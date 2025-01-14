package com.example.database;

public class CardBean {
    private String name;
    private String date;
    private String des;
    private int pic;
    private int score;
    private int tutorid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTutorid() {
        return tutorid;
    }

    public void setTutorid(int tutorid) {
        this.tutorid = tutorid;
    }
}