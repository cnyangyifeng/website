package com.mocktpo.web;

import com.mocktpo.modules.test.TestTagService;
import com.mocktpo.util.TestTagHelper;
import com.mocktpo.web.vo.TestTagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.util.List;

@RestController
public class TestTagRestController {

    @Autowired
    private TestTagService testTagService;

    @Autowired
    ServletContext ctx;

    @RequestMapping(value = "/api/v1/test-tags", method = RequestMethod.GET)
    @ResponseBody
    public List<TestTagVo> toTestTagsView() {
        return TestTagHelper.prepareTestTagVos(testTagService.findAll());
    }
}
