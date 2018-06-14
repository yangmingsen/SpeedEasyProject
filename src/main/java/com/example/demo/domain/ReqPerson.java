package com.example.demo.domain;

public class ReqPerson {
    /** Request username */
    private String username;

    /** Request password */
    private String password;

    public ReqPerson() {
    }

    public ReqPerson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ReqPerson{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

