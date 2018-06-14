package com.example.demo.repository.Impl;

import com.example.demo.domain.ResultHistory;
import com.example.demo.repository.RHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RHRepositoryImpl implements RHRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ResultHistory> findRHListByCno(String Cno) {
        return jdbcTemplate.query("select * from Resulthistory where cno=?",
                new Object[]{Cno}, new BeanPropertyRowMapper(ResultHistory.class));
    }

    @Override
    public int add(ResultHistory newRH) {
        return jdbcTemplate.update("insert into Resulthistory (cno, filePath,regcTime) VALUES (?,?,?)",
                newRH.getCno(), newRH.getFilePath(), newRH.getRegcTime());
    }
}
