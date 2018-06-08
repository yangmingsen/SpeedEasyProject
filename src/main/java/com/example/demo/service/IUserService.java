package com.example.demo.service;

import com.example.demo.domain.User;

public interface IUserService {
    User findUsersByCno(String Cno);

    int add(User newUser);
}
