package com.example.demo.service.Impl;

import com.example.demo.domain.FileHistory;
import com.example.demo.repository.FHRepository;
import com.example.demo.service.IFHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FHServiceImpl implements IFHService {

    @Autowired
    private FHRepository fhrDao;

    @Override
    public List<FileHistory> findFHListByCno(String Cno) {
        return fhrDao.findFHListByCno(Cno);
    }

    @Override
    public int add(FileHistory newFH) {
        return fhrDao.add(newFH);
    }
}
