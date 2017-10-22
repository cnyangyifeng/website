package com.mocktpo.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView toIndexView(HttpSession session) {
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
