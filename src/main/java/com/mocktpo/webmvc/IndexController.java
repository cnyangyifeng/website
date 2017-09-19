package com.mocktpo.webmvc;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView toIndexView(HttpSession session) {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (StringUtils.isEmpty(email)) {
            mv.setViewName("index");
        } else {
            mv.setViewName("redirect:/home");
        }
        return mv;
    }
}
