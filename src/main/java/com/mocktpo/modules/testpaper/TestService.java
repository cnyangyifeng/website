package com.mocktpo.modules.testpaper;

import com.mocktpo.orm.domain.TestPaper;
import com.mocktpo.orm.mapper.TestPaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestPaperMapper mapper;

    public List<TestPaper> findAll() {
        return mapper.findAll();
    }

    public TestPaper findByTid(String tid) {
        return mapper.findByTid(tid);
    }

    public void create(TestPaper testPaper) {
        mapper.create(testPaper);
    }

    public void update(TestPaper testPaper) {
        mapper.update(testPaper);
    }

    public void deleteByTid(String tid) {
        mapper.deleteByTid(tid);
    }
}