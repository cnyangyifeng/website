package com.mocktpo.service;

import com.mocktpo.domain.User;
import com.mocktpo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByEmailAndPassword(String email, String password) {
        return userMapper.findByEmailAndPassword(email, password);
    }

    public void create(User user) {
        userMapper.create(user);
    }
}
