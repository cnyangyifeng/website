package com.mocktpo.web;

import com.mocktpo.modules.order.OrderService;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LicenseRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "api/v1/license/{orderNumber}/status", method = RequestMethod.GET)
    @ResponseBody
    public int getOrderStatus(@PathVariable(value = "orderNumber") String orderNumber) {
        Order order = orderService.findByOrderNumber(orderNumber);
        return order != null ? order.getStatus() : OrderHelper.STATUS_UNKNOWN;
    }
}
