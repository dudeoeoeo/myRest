package com.myHome.myrest.repository;

import com.myHome.myrest.model.User;

import java.util.List;

public interface CustomizedUserRepository {
    List<User> findByCustomUsername(String username);

    List<User> findByUsernameJdbc(String username);
}
