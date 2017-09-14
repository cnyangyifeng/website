package com.mocktpo.webmvc;

import com.mocktpo.util.CipherUtils;
import com.mocktpo.vo.LoginVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLoginView() {
        logger.debug("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        mv.addObject("loginVo", new LoginVo());
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(LoginVo loginVo) {
        logger.debug("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/users/" + CipherUtils.encode(loginVo.getEmail()) + "/licenses");
        return mv;
    }
}
