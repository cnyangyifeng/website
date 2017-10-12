package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.pay.service.AlipayService;
import com.mocktpo.modules.portal.web.vo.OrderReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class PaymentController {

    @Autowired
    private AlipayService alipay;

    @RequestMapping(value = "/payment/alipay/request", method = RequestMethod.POST)
    public void alipayRequest(OrderReqVo orderReqVo, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter w = resp.getWriter();
            w.write(alipay.pay(orderReqVo));
            w.flush();
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/payment/alipay/notify", method = RequestMethod.POST)
    public void alipayNotify() {
    }

    @RequestMapping(value = "/payment/paypal/request", method = RequestMethod.POST)
    public void paypalRequest() {
    }

    @RequestMapping(value = "/payment/paypal/notify", method = RequestMethod.POST)
    public void paypalNotify() {
    }

    @RequestMapping(value = "/payment/response", method = RequestMethod.GET)
    public ModelAndView response() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order_complete");
        return mv;
    }
}
