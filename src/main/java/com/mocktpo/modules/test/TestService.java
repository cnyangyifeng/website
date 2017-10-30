package com.mocktpo.modules.test;

import com.mocktpo.orm.domain.Test;
import com.mocktpo.orm.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper mapper;

    public List<Test> findAll() {
        return mapper.findAll();
    }

    public Test findByTid(String tid) {
        return mapper.findByTid(tid);
    }

    public void create(Test test) {
        mapper.create(test);
    }

    public void update(Test test) {
        mapper.update(test);
    }

    public void deleteByTid(String tid) {
        mapper.deleteByTid(tid);
    }
}