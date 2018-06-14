package com.example.demo.domain;

public class FileHistory {
    private int  id;
    private String Cno;
    private String filePath;
    private String uploadTime;

    public FileHistory(String cno, String filePath, String uploadTime) {
        Cno = cno;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
    }

    public FileHistory() {
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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FileHistory{" +
                "id=" + id +
                ", Cno='" + Cno + '\'' +
                ", filePath='" + filePath + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }
}
