package com.mocktpo.modules.portal.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView toDownloadView() {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("download");
        return mv;
    }
}