package com.mocktpo.web;

import com.mocktpo.modules.testpaper.TestPaperService;
import com.mocktpo.orm.domain.TestPaper;
import com.mocktpo.util.TestPaperHelper;
import com.mocktpo.web.vo.FileUploadVo;
import com.mocktpo.web.vo.TestPaperVo;
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
public class TestPaperController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private TestPaperService testPaperService;

    @Autowired
    ServletContext ctx;

    @RequestMapping(value = "/testpapers", method = RequestMethod.GET)
    public ModelAndView toTestPapersView() {
        ModelAndView mv = new ModelAndView();
        List<TestPaper> testPapers = testPaperService.findAll();
        mv.addObject("testPaperVos", TestPaperHelper.prepareTestPaperVos(testPapers));
        mv.setViewName("test_papers");
        return mv;
    }

    @RequestMapping(value = "/testpapers/create", method = RequestMethod.GET)
    public ModelAndView toCreateTestPaperView() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("testPaperVo", new TestPaperVo());
        mv.setViewName("create_test_paper");
        return mv;
    }

    @RequestMapping(value = "/testpapers/create.do", method = RequestMethod.POST)
    public ModelAndView createTestPaper(TestPaperVo testPaperVo) {
        ModelAndView mv = new ModelAndView();
        testPaperService.create(TestPaperHelper.createTestPaper(testPaperVo));
        mv.setViewName("redirect:/testpapers");
        return mv;
    }

    @RequestMapping(value = "/testpapers/{tid}/upload", method = RequestMethod.GET)
    public ModelAndView toUploadTestPaperView(@PathVariable(value = "tid") String tid) {
        ModelAndView mv = new ModelAndView();
        TestPaper testPaper = testPaperService.findByTid(tid);
        if (testPaper != null) {
            mv.addObject("testPaperVo", TestPaperHelper.prepareTestPaperVo(testPaper));
            mv.addObject("command", new FileUploadVo());
            mv.setViewName("upload_test_paper");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/testpapers/{tid}/upload.do", method = RequestMethod.POST)
    public ModelAndView fileUpload(@PathVariable(value = "tid") String tid, FileUploadVo fileUploadVo, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        TestPaper testPaper = testPaperService.findByTid(tid);
        if (testPaper != null) {
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
                        testPaper.setStatus(TestPaperHelper.STATUS_COMPLETED);
                        testPaperService.update(testPaper);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                mv.setViewName("redirect:/testpapers");
            } else {
                mv.setViewName("error");
            }
        } else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/testpapers/{tid}/edit", method = RequestMethod.GET)
    public ModelAndView toEditTestPaperView(@PathVariable(value = "tid") String tid) {
        ModelAndView mv = new ModelAndView();
        TestPaper testPaper = testPaperService.findByTid(tid);
        if (testPaper != null) {
            mv.addObject("testPaperVo", TestPaperHelper.prepareTestPaperVo(testPaper));
            mv.setViewName("edit_test_paper");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/testpapers/{tid}/edit.do", method = RequestMethod.POST)
    public ModelAndView editTestPaper(@PathVariable(value = "tid") String tid, TestPaperVo testPaperVo) {
        ModelAndView mv = new ModelAndView();
        TestPaper testPaper = testPaperService.findByTid(tid);
        if (testPaper != null) {
            testPaperService.update(TestPaperHelper.copyTestPaper(testPaperVo, testPaper));
            mv.setViewName("redirect:/testpapers");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/testpapers/{tid}/delete", method = RequestMethod.GET)
    public ModelAndView deleteTestPaper(@PathVariable(value = "tid") String tid) {
        ModelAndView mv = new ModelAndView();
        TestPaper testPaper = testPaperService.findByTid(tid);
        if (testPaper != null) {
            testPaperService.deleteByTid(tid);
            mv.setViewName("redirect:/testpapers");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }
}
