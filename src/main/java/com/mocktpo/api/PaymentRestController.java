package com.mocktpo.api;

import com.mocktpo.modules.order.OrderService;
import com.mocktpo.orm.domain.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/v1/payments/alipay/notify", method = RequestMethod.POST)
    @ResponseBody
    public String alipayNotify() {
        logger.info("alipay notified.");
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
