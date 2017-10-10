package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select({
            "SELECT",
            "MT_ID AS id,",
            "MT_EMAIL AS email,",
            "MT_PASSCODE AS passcode",
            "FROM MT_USER",
            "WHERE",
            "MT_EMAIL = #{email}"
    })
    User findByEmail(@Param("email") String email);


    @Select({
            "SELECT",
            "MT_ID AS id,",
            "MT_EMAIL AS email,",
            "MT_PASSCODE AS passcode",
            "FROM MT_USER",
            "WHERE",
            "MT_EMAIL = #{email}",
            "AND",
            "MT_PASSCODE = #{passcode}"
    })
    User findByEmailAndPasscode(@Param("email") String email, @Param("passcode") String passcode);

    @Insert({
            "INSERT INTO MT_USER (",
            "MT_EMAIL,",
            "MT_PASSCODE",
            ") VALUES (",
            "#{email},",
            "#{passcode}",
            ")"
    })
    void create(User user);
}
