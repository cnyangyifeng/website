package com.mocktpo.webmvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PaymentController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = "/paypal/payment/complete", method = RequestMethod.GET)
    public ModelAndView paypalComplete() {
        logger.debug("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/home");
        return mv;
    }
}
