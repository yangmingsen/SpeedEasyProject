package com.example.demo.domain;


import java.io.Serializable;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cno;
    private String cname;
    private String cpasd;
    private String profession;
    private String address;
    private String phone;
    private String cregTime;

    public User() { }

    public User(String cno, String cpasd, String cname) {
        this.cno = cno;
        this.cpasd = cpasd;
        this.cname = cname;
    }

    public User(String cno, String cname, String cpasd, String profession, String address, String phone, String cregTime) {
        this.cno = cno;
        this.cname = cname;
        this.cpasd = cpasd;
        this.profession = profession;
        this.address = address;
        this.phone = phone;
        this.cregTime = cregTime;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCpasd() {
        return cpasd;
    }

    public void setCpasd(String cpasd) {
        this.cpasd = cpasd;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCregTime() {
        return cregTime;
    }

    public void setCregTime(String cregTime) {
        this.cregTime = cregTime;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", cpasd='" + cpasd + '\'' +
                ", profession='" + profession + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", cregTime='" + cregTime + '\'' +
                '}';
    }
}
