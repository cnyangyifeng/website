package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.portal.service.LicenseService;
import com.mocktpo.modules.portal.web.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private LicenseService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView toHomeView(HttpSession session) {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (StringUtils.isEmpty(email)) {
            mv.addObject("loginVo", new LoginVo());
            mv.setViewName("redirect:/login");
        } else {
            mv.addObject("licenses", service.findByEmail(email));
            mv.setViewName("home");
        }
        return mv;
    }
}
