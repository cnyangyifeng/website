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
            "MT_PRODUCT AS product,",
            "MT_EDITION AS edition,",
            "MT_VERSION AS version,",
            "MT_ACTIVATION_CODE AS activationCode,",
            "MT_EMAIL AS email,",
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
            "MT_PRODUCT AS product,",
            "MT_EDITION AS edition,",
            "MT_VERSION AS version,",
            "MT_ACTIVATION_CODE AS activationCode,",
            "MT_EMAIL AS email,",
            "MT_HARDWARE AS hardware,",
            "MT_VALID_THROUGH AS validThrough",
            "FROM MT_LICENSE",
            "WHERE",
            "MT_EMAIL = #{email}"
    })
    List<License> findByEmail(@Param("email") String email);

    @Insert({
            "INSERT INTO MT_LICENSE (",
            "MT_PRODUCT,",
            "MT_EDITION,",
            "MT_VERSION,",
            "MT_ACTIVATION_CODE,",
            "MT_EMAIL,",
            "MT_HARDWARE,",
            "MT_VALID_THROUGH",
            ") VALUES (",
            "#{product},",
            "#{edition},",
            "#{version},",
            "#{activationCode},",
            "#{email},",
            "#{hardware},",
            "#{validThrough}",
            ")"
    })
    void create(License license);

    @Update({
            "UPDATE MT_LICENSE",
            "SET",
            "MT_PRODUCT = #{product},",
            "MT_EDITION = #{edition},",
            "MT_VERSION = #{version},",
            "MT_EMAIL = #{email},",
            "MT_HARDWARE = #{hardware},",
            "MT_VALID_THROUGH = #{validThrough}",
            "WHERE",
            "MT_ACTIVATION_CODE = #{activationCode}"

    })
    void update(License license);
}
