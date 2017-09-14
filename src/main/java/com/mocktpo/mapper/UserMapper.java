package com.mocktpo.mapper;

import com.mocktpo.domain.User;

public interface UserMapper {

    User findByEmailAndPassword(String email, String password);

    void create(User user);
}
