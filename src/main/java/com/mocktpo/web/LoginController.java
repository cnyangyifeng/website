package com.mocktpo.web;

import com.mocktpo.modules.user.UserService;
import com.mocktpo.orm.domain.User;
import com.mocktpo.util.EmailUtils;
import com.mocktpo.web.vo.LoginVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLoginView(HttpSession session, String err) {
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (StringUtils.isEmpty(email)) {
            mv.addObject("loginVo", new LoginVo());
            mv.addObject("err", err);
            mv.setViewName("login");
        } else {
            mv.setViewName("redirect:/home");
        }
        return mv;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, LoginVo loginVo) {
        ModelAndView mv = new ModelAndView();
        String email = loginVo.getEmail();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            mv.addObject("err", "email_or_password_cant_be_empty");
            mv.setViewName("redirect:/login");
        } else {
            if (!EmailUtils.validate(email)) {
                mv.addObject("err", "invalid_email");
                mv.setViewName("redirect:/login");
            } else {
                User user = service.findByEmailAndPasscode(StringUtils.lowerCase(email), DigestUtils.md5Hex(password));
                if (user == null) {
                    mv.addObject("err", "email_or_password_is_incorrect");
                    mv.setViewName("redirect:/login");
                } else {
                    session.setAttribute("email", StringUtils.lowerCase(email));
                    mv.setViewName("redirect:/home");
                }
            }
        }
        return mv;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (!StringUtils.isEmpty(email)) {
            session.removeAttribute("email");
        }
        mv.setViewName("redirect:/");
        return mv;
    }
}
