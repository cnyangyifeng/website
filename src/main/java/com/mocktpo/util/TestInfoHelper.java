package com.mocktpo.util;

import com.mocktpo.orm.domain.TestInfo;
import com.mocktpo.web.vo.TestInfoVo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestInfoHelper {

    public static final int STATUS_CREATED = 1;
    public static final int STATUS_COMPLETED = 2;
    public static final int STATUS_EXPIRED = 3;

    private TestInfoHelper() {
    }

    public static TestInfo createTestInfo(TestInfoVo testInfoVo) {
        TestInfo testInfo = new TestInfo();
        if (testInfoVo != null) {
            testInfo.setTid(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
            testInfo.setTagId(testInfoVo.getTagId());
            testInfo.setTitle(StringUtils.isEmpty(testInfoVo.getTitle()) ? "Unknown" : testInfoVo.getTitle());
            testInfo.setStars(testInfoVo.getStars());
            testInfo.setVersion(testInfoVo.getVersion() == 0.0d ? 1.0d : testInfoVo.getVersion());
            testInfo.setCreator("MockTPO");
            testInfo.setCreatedTime(System.currentTimeMillis());
            testInfo.setUpdatedTime(System.currentTimeMillis());
            testInfo.setStatus(STATUS_CREATED);
        }
        return testInfo;
    }

    public static TestInfo updateTestInfo(TestInfoVo testInfoVo, TestInfo testInfo) {
        if (testInfoVo != null) {
            testInfo.setTid(testInfoVo.getTid());
            testInfo.setTagId(testInfoVo.getTagId());
            testInfo.setTitle(testInfoVo.getTitle());
            testInfo.setStars(testInfoVo.getStars());
            testInfo.setVersion(testInfoVo.getVersion());
            testInfo.setCreator(testInfoVo.getCreator());
            testInfo.setUpdatedTime(System.currentTimeMillis());
            testInfo.setStatus(testInfoVo.getStatus());
        }
        return testInfo;
    }

    public static TestInfoVo prepareTestInfoVo(TestInfo testInfo) {
        TestInfoVo testInfoVo = new TestInfoVo();
        if (testInfo != null) {
            testInfoVo.setTid(testInfo.getTid());
            testInfoVo.setTagId(testInfo.getTagId());
            testInfoVo.setTitle(testInfo.getTitle());
            testInfoVo.setStars(testInfo.getStars());
            testInfoVo.setVersion(testInfo.getVersion());
            testInfoVo.setCreator(testInfo.getCreator());
            testInfoVo.setCreatedTime(testInfo.getCreatedTime());
            testInfoVo.setUpdatedTime(testInfo.getUpdatedTime());
            testInfoVo.setStatus(testInfo.getStatus());
        }
        return testInfoVo;
    }

    public static List<TestInfoVo> prepareTestInfoVos(List<TestInfo> tests) {
        List<TestInfoVo> testInfoVos = new ArrayList<>();
        for (TestInfo testInfo : tests) {
            TestInfoVo testInfoVo = new TestInfoVo();
            if (testInfo != null) {
                testInfoVo.setTid(testInfo.getTid());
                testInfoVo.setTagId(testInfo.getTagId());
                testInfoVo.setTitle(testInfo.getTitle());
                testInfoVo.setStars(testInfo.getStars());
                testInfoVo.setVersion(testInfo.getVersion());
                testInfoVo.setCreator(testInfo.getCreator());
                testInfoVo.setCreatedTime(testInfo.getCreatedTime());
                testInfoVo.setUpdatedTime(testInfo.getUpdatedTime());
                testInfoVo.setStatus(testInfo.getStatus());
            }
            testInfoVos.add(testInfoVo);
        }
        return testInfoVos;
    }
}
