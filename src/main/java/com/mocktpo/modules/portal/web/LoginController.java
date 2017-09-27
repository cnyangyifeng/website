package com.mocktpo.modules.portal.web;

import com.mocktpo.orm.domain.User;
import com.mocktpo.modules.portal.service.UserService;
import com.mocktpo.modules.portal.web.vo.LoginVo;
import org.apache.commons.codec.digest.DigestUtils;
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
public class LoginController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLoginView(HttpSession session) {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (StringUtils.isEmpty(email)) {
            mv.addObject("loginVo", new LoginVo());
            mv.setViewName("login");
        } else {
            mv.setViewName("redirect:/home");
        }
        return mv;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, LoginVo loginVo) {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        String email = loginVo.getEmail();
        String password = loginVo.getPassword();
        if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {
            User user = service.findByEmailAndPasscode(email, DigestUtils.md5Hex(password));
            if (user != null) {
                session.setAttribute("email", email);
                mv.setViewName("redirect:/home");
            } else {
                mv.setViewName("redirect:/login");
            }
        } else {
            mv.setViewName("redirect:/login");
        }
        return mv;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (!StringUtils.isEmpty(email)) {
            session.removeAttribute("email");
        }
        mv.setViewName("redirect:/");
        return mv;
    }
}