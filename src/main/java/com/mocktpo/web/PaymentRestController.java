package com.mocktpo.web;

import com.mocktpo.modules.order.OrderService;
import com.mocktpo.orm.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/v1/payments/alipay/notify", method = RequestMethod.POST)
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
    public void paypalNotify() {
    }
}
