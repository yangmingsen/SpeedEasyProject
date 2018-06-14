package com.example.demo.repository.Impl;

import com.example.demo.domain.FileHistory;
import com.example.demo.repository.FHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FHRepositoryImpl implements FHRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<FileHistory> findFHListByCno(String Cno) {
        List<FileHistory> res = jdbcTemplate.query("select * from Filehistory where cno=?",
                new Object[]{Cno},new BeanPropertyRowMapper(FileHistory.class));
        if(res != null && res.size()>0) {
            return res;
        } else {
            return null;
        }
    }

    @Override
    public int add(FileHistory newFH) {
        return jdbcTemplate.update("insert into Filehistory (cno,filePath,uploadTime)values(?,?,?)",
                newFH.getCno(),newFH.getFilePath(),newFH.getUploadTime());
    }

}
