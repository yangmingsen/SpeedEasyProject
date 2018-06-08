package com.example.demo.domain;


import java.io.Serializable;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    private String cno;
    private String cpasd;
    private String cname;
    private String cregTime;

    public User() { }

    public User(String cno, String cpasd, String cname) {
        this.cno = cno;
        this.cpasd = cpasd;
        this.cname = cname;
    }

    public User(String cno, String cpasd, String cname, String cregTime) {
        this.cno = cno;
        this.cpasd = cpasd;
        this.cname = cname;
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
}
