package com.example.demo.service.Impl;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository usrDAO;

    @Override
    public User findUsersByCno(String Cno) {
        return usrDAO.findUsersByCno(Cno);
    }

    @Override
    public int add(User newusr) {
        return usrDAO.add(newusr);
    }

}
