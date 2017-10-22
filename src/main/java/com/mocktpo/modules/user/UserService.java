package com.mocktpo.modules.user;

import com.mocktpo.orm.domain.User;
import com.mocktpo.orm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public User findByEmailAndPasscode(String email, String passcode) {
        return userMapper.findByEmailAndPasscode(email, passcode);
    }

    public void create(User user) {
        userMapper.create(user);
    }
}
