package com.example.demo.repository;

import com.example.demo.domain.FileHistory;

import java.util.List;

public interface FHRepository {
    List<FileHistory> findFHListByCno(String Cno);

    int add(FileHistory newFH);

}
