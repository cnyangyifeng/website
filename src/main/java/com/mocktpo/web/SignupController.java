package com.mocktpo.web;

import com.mocktpo.modules.user.UserService;
import com.mocktpo.web.vo.SignupVo;
import com.mocktpo.orm.domain.User;
import com.mocktpo.util.EmailUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView toSignupView(String email, String err) {
        ModelAndView mv = new ModelAndView();
        SignupVo signupVo = new SignupVo();
        signupVo.setEmail(email);
        mv.addObject("signupVo", signupVo);
        mv.addObject("err", err);
        mv.setViewName("signup");
        return mv;
    }


    @RequestMapping(value = "/signup.do", method = RequestMethod.POST)
    public ModelAndView signup(HttpSession session, SignupVo signupVo) {
        ModelAndView mv = new ModelAndView();
        String email = signupVo.getEmail();
        String newPassword = signupVo.getNewPassword();
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(newPassword)) {
            mv.addObject("email", email);
            mv.addObject("err", "email_or_new_password_cant_be_empty");
            mv.setViewName("redirect:/signup");
        } else {
            if (!EmailUtils.validate(email)) {
                mv.addObject("email", email);
                mv.addObject("err", "invalid_email");
                mv.setViewName("redirect:/signup");
            } else {
                User user = service.findByEmail(StringUtils.lowerCase(email));
                if (user != null) {
                    mv.addObject("email", email);
                    mv.addObject("err", "email_exists");
                    mv.setViewName("redirect:/signup");
                } else {
                    user = new User();
                    user.setEmail(email);
                    user.setPasscode(DigestUtils.md5Hex(newPassword));
                    service.create(user);
                    session.setAttribute("email", StringUtils.lowerCase(email));
                    mv.setViewName("redirect:/home");
                }
            }
        }
        return mv;
    }
}
