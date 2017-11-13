package com.mocktpo.web;

import com.mocktpo.modules.order.OrderService;
import com.mocktpo.orm.domain.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class PaymentRestController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/v1/payments/alipay/response", method = RequestMethod.GET)
    public ModelAndView alipayResponse(@RequestParam Map map) {
        logger.info("Callback request parameters from Alipay:");
        logger.info("app_id: {}", map.get("app_id"));
        logger.info("method: {}", map.get("method"));
        logger.info("sign_type: {}", map.get("sign_type"));
        logger.info("sign: {}", map.get("sign"));
        logger.info("charset: {}", map.get("charset"));
        logger.info("timestamp: {}", map.get("timestamp"));
        logger.info("version: {}", map.get("version"));
        logger.info("auth_app_id: {}", map.get("auth_app_id"));
        logger.info("out_trade_no: {}", map.get("out_trade_no"));
        logger.info("trade_no: {}", map.get("trade_no"));
        logger.info("total_amount: {}", map.get("total_amount"));
        logger.info("seller_id: {}", map.get("seller_id"));
        ModelAndView mv = new ModelAndView();
        String orderNumber = map.get("out_trade_no").toString();
        Order order = orderService.findByOrderNumber(orderNumber);
        if (order != null) {
            orderService.complete(order);
            mv.setViewName("redirect:/orders/" + orderNumber + "/complete");
        } else {
            mv.setViewName("/error");
        }
        return mv;
    }

    @RequestMapping(value = "/api/v1/payments/paypal/response", method = RequestMethod.GET)
    public ModelAndView paypalResponse(@RequestParam Map map) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/orders/" + map.get("out_trade_no").toString());
        return mv;
    }

    @RequestMapping(value = "/api/v1/payments/alipay/notify", method = RequestMethod.POST)
    @ResponseBody
    public String alipayNotify() {
        Order order = orderService.findByOrderNumber("20171014222945891");
        if (order != null) {
            orderService.complete(order);
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping(value = "/api/v1/payments/paypal/notify", method = RequestMethod.POST)
    @ResponseBody
    public void paypalNotify() {
    }
}
