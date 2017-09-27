package com.mocktpo.modules.portal.service;

import com.mocktpo.orm.domain.License;
import com.mocktpo.orm.mapper.LicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseService {

    @Autowired
    private LicenseMapper mapper;

    public List<License> findByActivationCode(String activationCode) {
        return mapper.findByActivationCode(activationCode);
    }

    public List<License> findByEmail(String email) {
        return mapper.findByEmail(email);
    }

    public void create(License license) {
        mapper.create(license);
    }

    public void update(License license) {
        mapper.update(license);
    }
}
