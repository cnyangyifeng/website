package com.mocktpo.orm.mapper;

import com.mocktpo.orm.domain.License;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LicenseMapper {

    @Select({
            "SELECT",
            "MT_ID AS id,",
            "MT_ORDER_NUMBER AS orderNumber,",
            "MT_PID AS pid,",
            "MT_EMAIL AS email,",
            "MT_ACTIVATION_CODE AS activationCode,",
            "MT_HARDWARE AS hardware,",
            "MT_VALID_THROUGH AS validThrough",
            "FROM MT_LICENSE",
            "WHERE",
            "MT_ACTIVATION_CODE = #{activationCode}"
    })
    List<License> findByActivationCode(@Param("activationCode") String activationCode);

    @Select({
            "SELECT",
            "MT_ID AS id,",
            "MT_ORDER_NUMBER AS orderNumber,",
            "MT_PID AS pid,",
            "MT_EMAIL AS email,",
            "MT_ACTIVATION_CODE AS activationCode,",
            "MT_HARDWARE AS hardware,",
            "MT_VALID_THROUGH AS validThrough",
            "FROM MT_LICENSE",
            "WHERE",
            "MT_EMAIL = #{email}"
    })
    List<License> findByEmail(@Param("email") String email);

    @Insert({
            "INSERT INTO MT_LICENSE (",
            "MT_ID,",
            "MT_ORDER_NUMBER,",
            "MT_PID,",
            "MT_EMAIL,",
            "MT_ACTIVATION_CODE,",
            "MT_HARDWARE,",
            "MT_VALID_THROUGH",
            ") VALUES (",
            "#{id},",
            "#{orderNumber},",
            "#{pid},",
            "#{email},",
            "#{activationCode},",
            "#{hardware},",
            "#{validThrough}",
            ")"
    })
    void create(License license);

    @Update({
            "UPDATE MT_LICENSE",
            "SET",
            "MT_ID = #{id},",
            "MT_ORDER_NUMBER = #{orderNumber},",
            "MT_PID = #{pid},",
            "MT_EMAIL = #{email},",
            "MT_ACTIVATION_CODE = #{activationCode},",
            "MT_HARDWARE = #{hardware},",
            "MT_VALID_THROUGH = #{validThrough}",
            "WHERE",
            "MT_ACTIVATION_CODE = #{activationCode}"

    })
    void update(License license);
}
