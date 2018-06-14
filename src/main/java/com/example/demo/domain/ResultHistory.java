package com.example.demo.domain;

public class ResultHistory {
    private int id;
    private String cno;
    private String filePath;
    private String regcTime;

    public ResultHistory(String cno, String filePath, String recoTime) {
        this.cno = cno;
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
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
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
                ", Cno='" + cno + '\'' +
                ", filePath='" + filePath + '\'' +
                ", recoTime='" + regcTime + '\'' +
                '}';
    }
}
