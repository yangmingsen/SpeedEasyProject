package com.example.demo.repository;

import com.example.demo.domain.User;

public interface UserRepository {

    User findUsersByCno(String Cno);

    int add(User newUsr);

}
