package com.mocktpo.web;

import com.mocktpo.modules.pay.AlipayService;
import com.mocktpo.modules.order.OrderService;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderRestController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "api/v1/order/{orderNumber}/status", method = RequestMethod.GET)
    @ResponseBody
    public int getOrderStatus(@PathVariable(value = "orderNumber") String orderNumber) {
        Order order = orderService.findByOrderNumber(orderNumber);
        return order != null ? order.getStatus() : OrderHelper.STATUS_UNKNOWN;
    }

    @RequestMapping(value = "api/v1/order/{orderNumber}/status/sync", method = RequestMethod.GET)
    @ResponseBody
    public int syncOrderStatus(@PathVariable(value = "orderNumber") String orderNumber) {
        Order order = orderService.findByOrderNumber(orderNumber);
        if (order != null) {
            try {
                if (alipayService.isOrderCompleted(orderNumber)) {
                    order.setStatus(OrderHelper.STATUS_COMPLETED);
                    orderService.update(order);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return order.getStatus();
        } else {
            return OrderHelper.STATUS_UNKNOWN;
        }
    }
}
