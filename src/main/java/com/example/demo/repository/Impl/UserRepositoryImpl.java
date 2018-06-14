package com.example.demo.repository.Impl;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findUsersByCno(String Cno) {
        List<User> list = jdbcTemplate.query("select * from Client where Cno = ?",
                new Object[]{Cno}, new BeanPropertyRowMapper(User.class));
        if(list!=null && list.size()>0){
           return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public int add(User newusr) {
        return jdbcTemplate.update("insert into Client (Cno,Cname,Cpasd)values(?,?,?)",
                newusr.getCno(),newusr.getCname(),newusr.getCpasd());
    }

}
