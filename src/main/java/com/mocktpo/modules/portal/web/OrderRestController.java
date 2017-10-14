package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.pay.service.AlipayService;
import com.mocktpo.modules.portal.service.OrderService;
import com.mocktpo.modules.portal.web.vo.OrderReqVo;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayService alipayService;

    @RequestMapping(value = "/order/{orderNumber}/status", method = RequestMethod.GET)
    @ResponseBody
    public OrderReqVo getOrderStatus(@PathVariable(value = "orderNumber") String orderNumber) {
        Order order = orderService.findByOrderNumber(orderNumber);
        if (order != null) {
            try {
                if (alipayService.query(order)) {
                    order.setStatus(OrderHelper.STATUS_COMPLETED);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return OrderHelper.prepareOrderReqVo(order);
    }
}
