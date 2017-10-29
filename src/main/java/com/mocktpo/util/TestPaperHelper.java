package com.mocktpo.util;

import com.mocktpo.orm.domain.TestPaper;
import com.mocktpo.web.vo.TestPaperVo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestPaperHelper {

    public static final int STATUS_CREATED = 1;
    public static final int STATUS_COMPLETED = 2;
    public static final int STATUS_EXPIRED = 3;

    private TestPaperHelper() {
    }

    public static TestPaper createTestPaper(TestPaperVo testPaperVo) {
        TestPaper testPaper = new TestPaper();
        if (testPaperVo != null) {
            testPaper.setTid(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
            testPaper.setTitle(StringUtils.isEmpty(testPaperVo.getTitle()) ? "Unknown" : testPaperVo.getTitle());
            testPaper.setStars(testPaperVo.getStars());
            testPaper.setCreator("MockTPO");
            testPaper.setCreatedTime(System.currentTimeMillis());
            testPaper.setUpdatedTime(System.currentTimeMillis());
            testPaper.setVersion(testPaperVo.getVersion() == 0.0d ? 1.0d : testPaperVo.getVersion());
            testPaper.setStatus(STATUS_CREATED);
        }
        return testPaper;
    }

    public static TestPaper copyTestPaper(TestPaperVo testPaperVo, TestPaper testPaper) {
        if (testPaperVo != null) {
            testPaper.setTid(testPaperVo.getTid());
            testPaper.setTitle(testPaperVo.getTitle());
            testPaper.setStars(testPaperVo.getStars());
            testPaper.setCreator(testPaperVo.getCreator());
            testPaper.setUpdatedTime(System.currentTimeMillis());
            testPaper.setVersion(testPaperVo.getVersion());
            testPaper.setStatus(testPaperVo.getStatus());
        }
        return testPaper;
    }

    public static TestPaperVo prepareTestPaperVo(TestPaper testPaper) {
        TestPaperVo testPaperVo = new TestPaperVo();
        if (testPaper != null) {
            testPaperVo.setTid(testPaper.getTid());
            testPaperVo.setTitle(testPaper.getTitle());
            testPaperVo.setStars(testPaper.getStars());
            testPaperVo.setCreator(testPaper.getCreator());
            testPaperVo.setCreatedTime(testPaper.getCreatedTime());
            testPaperVo.setUpdatedTime(testPaper.getUpdatedTime());
            testPaperVo.setVersion(testPaper.getVersion());
            testPaperVo.setStatus(testPaper.getStatus());
        }
        return testPaperVo;
    }

    public static List<TestPaperVo> prepareTestPaperVos(List<TestPaper> testPapers) {
        List<TestPaperVo> testPaperVos = new ArrayList<>();
        for (TestPaper testPaper : testPapers) {
            TestPaperVo testPaperVo = new TestPaperVo();
            if (testPaper != null) {
                testPaperVo.setTid(testPaper.getTid());
                testPaperVo.setTitle(testPaper.getTitle());
                testPaperVo.setStars(testPaper.getStars());
                testPaperVo.setCreator(testPaper.getCreator());
                testPaperVo.setCreatedTime(testPaper.getCreatedTime());
                testPaperVo.setUpdatedTime(testPaper.getUpdatedTime());
                testPaperVo.setVersion(testPaper.getVersion());
                testPaperVo.setStatus(testPaper.getStatus());
            }
            testPaperVos.add(testPaperVo);
        }
        return testPaperVos;
    }
}
