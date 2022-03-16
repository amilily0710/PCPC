package com.example.solution;

public class Users {
    private String email;
    private String name;
    private String Uid;
    private String time;

    public Users(){}

    public String getemail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name = name;
    }
    public String gettime(){
        return time;
    }
    public void settime(String time){
        this.time = time;
    }
    public String getUid(){
        return Uid;
    }
    public void setUid(String Uid){
        this.Uid = Uid;
    }
    String uid;


    public Users(String email, String name, String time){
        this.email = email;
        this.name = name;
        this.time = time;
    }
}