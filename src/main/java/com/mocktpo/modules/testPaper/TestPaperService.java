package com.mocktpo.modules.testPaper;

import com.mocktpo.orm.domain.TestPaper;
import com.mocktpo.orm.mapper.TestPaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestPaperService {

    @Autowired
    private TestPaperMapper mapper;

    public List<TestPaper> findAll() {
        return mapper.findAll();
    }

    public void create(TestPaper testPaper) {
        mapper.create(testPaper);
    }

    public void update(TestPaper testPaper) {
        mapper.update(testPaper);
    }
}