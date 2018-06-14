package com.example.demo.service;

import com.example.demo.domain.ResultHistory;

import java.util.List;

public interface IRHService {
    List<ResultHistory> findRHListByCno(String Cno);

    int add(ResultHistory newFH);
}
