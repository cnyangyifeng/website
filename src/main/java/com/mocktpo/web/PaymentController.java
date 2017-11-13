package com.mocktpo.web;

import com.mocktpo.modules.order.OrderService;
import com.mocktpo.modules.pay.AlipayService;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.OrderHelper;
import com.mocktpo.web.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class PaymentController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/payments/alipay/request", method = RequestMethod.POST)
    public void alipayRequest(OrderVo orderVo, HttpServletResponse resp) {
        Order order = orderService.findByOrderNumber(orderVo.getOrderNumber());
        if (order == null) {
            order = OrderHelper.prepareOrder(orderVo);
            order.setStatus(OrderHelper.STATUS_PENDING);
            orderService.create(order);
            try {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter w = resp.getWriter();
                w.write(alipayService.pay(order));
                w.flush();
                w.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/payments/paypal/request", method = RequestMethod.POST)
    public void paypalRequest() {
    }
}
