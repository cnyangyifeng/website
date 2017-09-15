package com.mocktpo.webmvc;

import com.mocktpo.domain.User;
import com.mocktpo.service.UserService;
import com.mocktpo.util.Base64Utils;
import com.mocktpo.vo.LoginVo;
import com.mocktpo.vo.SignupVo;
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
public class SignupController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserService service;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView toSignupView(HttpSession session) {
        logger.debug("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (StringUtils.isEmpty(email)) {
            mv.addObject("signupVo", new LoginVo());
            mv.setViewName("signup");
        } else {
            mv.setViewName("redirect:/users/" + Base64Utils.encode(email) + "/home");
        }
        return mv;
    }

    @RequestMapping(value = "/signup.do", method = RequestMethod.POST)
    public ModelAndView signup(HttpSession session, SignupVo signupVo) {
        logger.debug("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        String email = signupVo.getEmail();
        String password = signupVo.getPassword();
        if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {
            User existedUser = service.findByEmail(email);
            if (existedUser == null) {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPasscode(DigestUtils.md5Hex(password));
                service.create(newUser);
                session.setAttribute("email", email);
                mv.setViewName("redirect:/users/" + Base64Utils.encode(email) + "/home");
            } else {
                mv.setViewName("redirect:/signup");
            }
        } else {
            mv.setViewName("redirect:/signup");
        }
        return mv;
    }
}
