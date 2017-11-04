package com.mocktpo.modules.test;

import com.mocktpo.orm.domain.TestTag;
import com.mocktpo.orm.mapper.TestTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestTagService {

    @Autowired
    private TestTagMapper mapper;

    public List<TestTag> findAll() {
        return mapper.findAll();
    }

    public TestTag findByTagId(int tagId) {
        return mapper.findByTagId(tagId);
    }

    public void create(TestTag testTag) {
        mapper.create(testTag);
    }

    public void update(TestTag testTag) {
        mapper.update(testTag);
    }

    public void deleteByTagId(int tagId) {
        mapper.deleteByTagId(tagId);
    }
}