package com.mocktpo.mapper;

import com.mocktpo.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findByEmailAndPassword(String email, String password);

    void create(User user);
}
