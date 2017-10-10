package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.pay.service.AlipayService;
import com.mocktpo.modules.portal.web.vo.PaymentReqVo;
import com.mocktpo.util.EmailUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class PaymentController {

    private static final int PAYMENT_TYPE_ALIPAY = 1;
    private static final int PAYMENT_TYPE_PAYPAL = 2;

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AlipayService alipay;

    @RequestMapping(value = "/payment/request", method = RequestMethod.POST)
    public void request(HttpServletRequest req, HttpServletResponse resp, PaymentReqVo paymentReqVo) {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        String email = paymentReqVo.getEmail();
        try {
            if (StringUtils.isEmpty(email) || !EmailUtils.validate(email)) {
                resp.sendRedirect(req.getContextPath() + "/buy?err=invalid_email");
            } else {
                switch (paymentReqVo.getPaymentType()) {
                    case PAYMENT_TYPE_ALIPAY:
                        resp.setContentType("text/html;charset=utf-8");
                        PrintWriter w = resp.getWriter();
                        w.write(alipay.pay());
                        w.flush();
                        w.close();
                        break;
                    case PAYMENT_TYPE_PAYPAL:
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/payment/response", method = RequestMethod.GET)
    public ModelAndView response() {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order_complete");
        return mv;
    }

    @RequestMapping(value = "/payment/alipay/notify", method = RequestMethod.POST)
    public void alipayNotify() {
        logger.info("{}.{}() accessed.", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
