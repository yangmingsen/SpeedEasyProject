package com.example.demo.service.Impl;

import com.example.demo.domain.ResultHistory;
import com.example.demo.repository.RHRepository;
import com.example.demo.service.IRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RHServiceImpl implements IRHService {

    @Autowired
    private RHRepository rhrDao;

    @Override
    public List<ResultHistory> findRHListByCno(String Cno) {
        return rhrDao.findRHListByCno(Cno);
    }

    @Override
    public int add(ResultHistory newFH) {
        return rhrDao.add(newFH);
    }
}
