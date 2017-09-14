package com.mocktpo.webmvc;

import com.mocktpo.domain.User;
import com.mocktpo.service.LicenseService;
import com.mocktpo.util.CipherUtils;
import com.mocktpo.vo.LoginVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LicensesController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private LicenseService service;

    @RequestMapping(value = "/users/{id}/licenses", method = RequestMethod.GET)
    public ModelAndView toLicensesView(HttpSession session, @PathVariable String id) {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mv.addObject("loginVo", new LoginVo());
            mv.setViewName("redirect:/login");
        } else {
            logger.info(service.findByEmail(CipherUtils.decode(id)));
            mv.setViewName("licenses");
        }
        return mv;
    }
}
