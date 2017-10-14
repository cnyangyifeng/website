package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.portal.service.OrderService;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/payment/alipay/notify", method = RequestMethod.POST)
    public String alipayNotify() {
        Order order = orderService.findByOrderNumber("20171014222945891");
        if (order != null) {
            order.setStatus(OrderHelper.STATUS_COMPLETED);
            orderService.update(order);
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping(value = "/payment/paypal/notify", method = RequestMethod.POST)
    public void paypalNotify() {
    }
}
