package com.mocktpo.util;

import com.mocktpo.orm.domain.TestTag;
import com.mocktpo.web.vo.TestTagVo;

import java.util.ArrayList;
import java.util.List;

public class TestTagHelper {

    public static final int STATUS_CREATED = 1;
    public static final int STATUS_COMPLETED = 2;
    public static final int STATUS_EXPIRED = 3;

    private TestTagHelper() {
    }

    public static List<TestTagVo> prepareTestTagVos(List<TestTag> testTags) {
        List<TestTagVo> testTagVos = new ArrayList<>();
        for (TestTag testTag : testTags) {
            TestTagVo testTagVo = new TestTagVo();
            if (testTag != null) {
                testTagVo.setTagId(testTag.getTagId());
                testTagVo.setTitle(testTag.getTitle());
                testTagVo.setStatus(testTag.getStatus());
            }
            testTagVos.add(testTagVo);
        }
        return testTagVos;
    }
}
