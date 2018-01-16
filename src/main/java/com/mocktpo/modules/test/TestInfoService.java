package com.mocktpo.modules.test;

import com.mocktpo.orm.domain.TestInfo;
import com.mocktpo.orm.mapper.TestInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestInfoService {

    @Autowired
    private TestInfoMapper mapper;

    public List<TestInfo> findAll() {
        return mapper.findAll();
    }

    public TestInfo findByTid(String tid) {
        return mapper.findByTid(tid);
    }

    public void create(TestInfo testInfo) {
        mapper.create(testInfo);
    }

    public void update(TestInfo testInfo) {
        mapper.update(testInfo);
    }

    public void deleteByTid(String tid) {
        mapper.deleteByTid(tid);
    }
}