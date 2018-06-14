package com.example.demo.repository;

import com.example.demo.domain.ResultHistory;

import java.util.List;

public interface RHRepository {
    List<ResultHistory> findRHListByCno(String Cno);

    int add(ResultHistory newFH);
}
