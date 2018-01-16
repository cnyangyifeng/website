package com.mocktpo.util;

import com.mocktpo.orm.domain.TestTag;
import com.mocktpo.web.vo.TestTagVo;

import java.util.ArrayList;
import java.util.List;

public class TestTagHelper {

    private TestTagHelper() {
    }

    public static List<TestTagVo> prepareTestTagVos(List<TestTag> testTags) {
        List<TestTagVo> testTagVos = new ArrayList<>();
        for (TestTag testTag : testTags) {
            TestTagVo testTagVo = new TestTagVo();
            testTagVo.setTagId(testTag.getTagId());
            testTagVo.setTitle(testTag.getTitle());
            testTagVo.setStatus(testTag.getStatus());
            testTagVos.add(testTagVo);
        }
        return testTagVos;
    }
}
