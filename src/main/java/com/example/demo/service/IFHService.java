package com.example.demo.service;

import com.example.demo.domain.FileHistory;

import java.util.List;

public interface IFHService {
    List<FileHistory> findFHListByCno(String Cno);

    int add(FileHistory newFH);
}
