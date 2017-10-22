package com.mocktpo.web;

import com.mocktpo.modules.license.LicenseService;
import com.mocktpo.web.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private LicenseService service;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView toHomeView(HttpSession session) {
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
