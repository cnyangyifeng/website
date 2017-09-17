package com.mocktpo.mapper;

import com.mocktpo.domain.License;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LicenseMapper {

    List<License> findByActivationCode(@Param("activationCode") String activationCode);

    List<License> findByEmail(@Param("email") String email);

    void create(License license);

    void update(License license);
}
