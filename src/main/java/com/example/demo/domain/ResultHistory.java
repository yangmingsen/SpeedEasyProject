package com.example.demo.domain;

public class ResultHistory {
    private int id;
    private String Cno;
    private String filePath;
    private String regcTime;

    public ResultHistory(String cno, String filePath, String recoTime) {
        Cno = cno;
        this.filePath = filePath;
        this.regcTime = recoTime;
    }

    public ResultHistory() {
    }

    public String getRegcTime() {
        return regcTime;
    }

    public void setRegcTime(String regcTime) {
        this.regcTime = regcTime;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ResultHistory{" +
                "id=" + id +
                ", Cno='" + Cno + '\'' +
                ", filePath='" + filePath + '\'' +
                ", recoTime='" + regcTime + '\'' +
                '}';
    }
}
