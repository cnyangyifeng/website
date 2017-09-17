package com.mocktpo.webmvc;

import com.mocktpo.domain.License;
import com.mocktpo.service.LicenseService;
import com.mocktpo.util.constants.MT;
import com.mocktpo.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class PayController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private LicenseService service;

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public ModelAndView toPayView() {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pay");
        return mv;
    }


    @RequestMapping(value = "/post-pay", method = RequestMethod.GET)
    public ModelAndView postPayView(HttpSession session) {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        String email = (String) session.getAttribute("email");
        if (StringUtils.isEmpty(email)) {
            mv.addObject("loginVo", new LoginVo());
            mv.setViewName("redirect:/login");
        } else {
            License lic = new License();
            lic.setActivationCode(UUID.randomUUID().toString().replaceAll("-", ""));
            lic.setEmail(email);
            service.create(lic);
            mv.setViewName("redirect:/home");
        }
        return mv;
    }


}
