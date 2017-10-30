package com.mocktpo.util;

import com.mocktpo.orm.domain.Test;
import com.mocktpo.web.vo.TestVo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestHelper {

    public static final int STATUS_CREATED = 1;
    public static final int STATUS_COMPLETED = 2;
    public static final int STATUS_EXPIRED = 3;

    private TestHelper() {
    }

    public static Test createTest(TestVo testVo) {
        Test test = new Test();
        if (testVo != null) {
            test.setTid(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
            test.setTitle(StringUtils.isEmpty(testVo.getTitle()) ? "Unknown" : testVo.getTitle());
            test.setStars(testVo.getStars());
            test.setCreator("MockTPO");
            test.setCreatedTime(System.currentTimeMillis());
            test.setUpdatedTime(System.currentTimeMillis());
            test.setVersion(testVo.getVersion() == 0.0d ? 1.0d : testVo.getVersion());
            test.setStatus(STATUS_CREATED);
        }
        return test;
    }

    public static Test copyTest(TestVo testVo, Test test) {
        if (testVo != null) {
            test.setTid(testVo.getTid());
            test.setTitle(testVo.getTitle());
            test.setStars(testVo.getStars());
            test.setCreator(testVo.getCreator());
            test.setUpdatedTime(System.currentTimeMillis());
            test.setVersion(testVo.getVersion());
            test.setStatus(testVo.getStatus());
        }
        return test;
    }

    public static TestVo prepareTestVo(Test test) {
        TestVo testVo = new TestVo();
        if (test != null) {
            testVo.setTid(test.getTid());
            testVo.setTitle(test.getTitle());
            testVo.setStars(test.getStars());
            testVo.setCreator(test.getCreator());
            testVo.setCreatedTime(test.getCreatedTime());
            testVo.setUpdatedTime(test.getUpdatedTime());
            testVo.setVersion(test.getVersion());
            testVo.setStatus(test.getStatus());
        }
        return testVo;
    }

    public static List<TestVo> prepareTestVos(List<Test> tests) {
        List<TestVo> testVos = new ArrayList<>();
        for (Test test : tests) {
            TestVo testVo = new TestVo();
            if (test != null) {
                testVo.setTid(test.getTid());
                testVo.setTitle(test.getTitle());
                testVo.setStars(test.getStars());
                testVo.setCreator(test.getCreator());
                testVo.setCreatedTime(test.getCreatedTime());
                testVo.setUpdatedTime(test.getUpdatedTime());
                testVo.setVersion(test.getVersion());
                testVo.setStatus(test.getStatus());
            }
            testVos.add(testVo);
        }
        return testVos;
    }
}
