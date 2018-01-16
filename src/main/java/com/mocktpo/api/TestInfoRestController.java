package com.mocktpo.api;

import com.mocktpo.modules.test.TestInfoService;
import com.mocktpo.util.TestInfoHelper;
import com.mocktpo.web.vo.TestInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.util.List;

@RestController
public class TestInfoRestController {

    @Autowired
    private TestInfoService testInfoService;

    @Autowired
    ServletContext ctx;

    @RequestMapping(value = "/api/v1/tests", method = RequestMethod.GET)
    @ResponseBody
    public List<TestInfoVo> getTestInfoVos() {
        return TestInfoHelper.prepareTestInfoVos(testInfoService.findAll());
    }
}
