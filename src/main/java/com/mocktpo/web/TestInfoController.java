package com.mocktpo.web;

import com.mocktpo.modules.test.TestInfoService;
import com.mocktpo.modules.test.TestTagService;
import com.mocktpo.orm.domain.TestInfo;
import com.mocktpo.util.TestInfoHelper;
import com.mocktpo.util.TestTagHelper;
import com.mocktpo.web.vo.FileUploadVo;
import com.mocktpo.web.vo.TestInfoVo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@Controller
public class TestInfoController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private TestInfoService testInfoService;

    @Autowired
    private TestTagService testTagService;

    @Autowired
    ServletContext ctx;

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public ModelAndView toTestsView() {
        ModelAndView mv = new ModelAndView();
        List<TestInfo> tests = testInfoService.findAll();
        mv.addObject("testInfoVos", TestInfoHelper.prepareTestInfoVos(tests));
        mv.setViewName("tests");
        return mv;
    }

    @RequestMapping(value = "/tests/create", method = RequestMethod.GET)
    public ModelAndView toCreateTestView() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("testInfoVo", new TestInfoVo());
        mv.addObject("testTagVos", TestTagHelper.prepareTestTagVos(testTagService.findAll()));
        mv.setViewName("create_test");
        return mv;
    }

    @RequestMapping(value = "/tests/create.do", method = RequestMethod.POST)
    public ModelAndView createTest(TestInfoVo testVo) {
        ModelAndView mv = new ModelAndView();
        testInfoService.create(TestInfoHelper.createTestInfo(testVo));
        mv.setViewName("redirect:/tests");
        return mv;
    }

    @RequestMapping(value = "/tests/{tid}/upload", method = RequestMethod.GET)
    public ModelAndView toUploadTestView(@PathVariable(value = "tid") String tid) {
        ModelAndView mv = new ModelAndView();
        TestInfo testInfo = testInfoService.findByTid(tid);
        if (testInfo != null) {
            mv.addObject("testInfoVo", TestInfoHelper.prepareTestInfoVo(testInfo));
            mv.addObject("command", new FileUploadVo());
            mv.setViewName("upload_test");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/tests/{tid}/upload.do", method = RequestMethod.POST)
    public ModelAndView fileUpload(@PathVariable(value = "tid") String tid, FileUploadVo fileUploadVo, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        TestInfo testInfo = testInfoService.findByTid(tid);
        if (testInfo != null) {
            if (!result.hasErrors()) {
                String tmpDir = ctx.getRealPath("") + File.separator + "tmp" + File.separator;
                try {
                    String filename = fileUploadVo.getFile().getOriginalFilename();
                    File tmpFile = new File(tmpDir + filename);
                    FileCopyUtils.copy(fileUploadVo.getFile().getBytes(), tmpFile);
                    logger.info("File {} (tid: {}) has been uploaded to {}.", filename, tid, tmpDir);
                    if (FilenameUtils.getExtension(StringUtils.lowerCase(filename)).equals("zip")) {
                        String testsDir = ctx.getRealPath("") + File.separator + "tests" + File.separator;
                        FileUtils.copyFile(tmpFile, new File(testsDir + tid + ".zip"));
                        testInfo.setStatus(TestInfoHelper.STATUS_COMPLETED);
                        testInfoService.update(testInfo);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                mv.setViewName("redirect:/tests");
            } else {
                mv.setViewName("error");
            }
        } else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/tests/{tid}/edit", method = RequestMethod.GET)
    public ModelAndView toEditTestView(@PathVariable(value = "tid") String tid) {
        ModelAndView mv = new ModelAndView();
        TestInfo testInfo = testInfoService.findByTid(tid);
        if (testInfo != null) {
            mv.addObject("testInfoVo", TestInfoHelper.prepareTestInfoVo(testInfo));
            mv.addObject("testTagVos", TestTagHelper.prepareTestTagVos(testTagService.findAll()));
            mv.setViewName("edit_test");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/tests/{tid}/edit.do", method = RequestMethod.POST)
    public ModelAndView editTest(@PathVariable(value = "tid") String tid, TestInfoVo testInfoVo) {
        ModelAndView mv = new ModelAndView();
        TestInfo testInfo = testInfoService.findByTid(tid);
        if (testInfo != null) {
            testInfoService.update(TestInfoHelper.updateTestInfo(testInfoVo, testInfo));
            mv.setViewName("redirect:/tests");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/tests/{tid}/delete", method = RequestMethod.GET)
    public ModelAndView deleteTest(@PathVariable(value = "tid") String tid) {
        ModelAndView mv = new ModelAndView();
        TestInfo testInfo = testInfoService.findByTid(tid);
        if (testInfo != null) {
            testInfoService.deleteByTid(tid);
            mv.setViewName("redirect:/tests");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }
}
