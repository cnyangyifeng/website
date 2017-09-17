package com.mocktpo.mapper;

import com.mocktpo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    User findByEmail(@Param("email") String email);

    User findByEmailAndPasscode(@Param("email") String email, @Param("passcode") String passcode);

    void create(User user);
}
