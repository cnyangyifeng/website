package com.mocktpo.web;

import com.mocktpo.web.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView toAccountView(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (StringUtils.isEmpty(email)) {
            mv.addObject("loginVo", new LoginVo());
            mv.setViewName("redirect:/login");
        } else {
            mv.setViewName("account");
        }
        return mv;
    }
}
