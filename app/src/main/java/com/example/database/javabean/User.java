package com.example.database.javabean;

public class User {
    private int user_id;
    private String email;
    private String password;
    private String user_name;
    private String phone;
    private int user_type;

    public User(String username, String email, String pwd, int user_type){
        this.user_name = username;
        this.password = pwd;
        this.email = email;
        this.user_type = user_type;
    }

    public User(String email, int user_id, String username, String password, String phone, int user_type) {
        this.email = email;
        this.user_id = user_id;
        this.phone = phone;
        this.user_type = user_type;
        this.user_name = username;
        this.password = password;
    }

    public User(int id, String name, String userEmail, int type, String phone) {
        this.user_id = id;
        this.user_name = name;
        this.email = userEmail;
        this.user_type = type;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
