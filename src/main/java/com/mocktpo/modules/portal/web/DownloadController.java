package com.mocktpo.modules.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView toDownloadView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("download");
        return mv;
    }
}
