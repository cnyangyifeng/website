package com.mocktpo.web;

import com.mocktpo.modules.order.OrderService;
import com.mocktpo.modules.pay.AlipayService;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.OrderHelper;
import com.mocktpo.web.vo.OrderVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class PaymentController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/payment/alipay/request", method = RequestMethod.POST)
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

    @RequestMapping(value = "/payment/alipay/response", method = RequestMethod.GET)
    public ModelAndView alipayResponse(@RequestParam Map map) {
        logger.debug("Callback request parameters from Alipay:");
        logger.debug("app_id: {}", map.get("app_id"));
        logger.debug("method: {}", map.get("method"));
        logger.debug("sign_type: {}", map.get("sign_type"));
        logger.debug("sign: {}", map.get("sign"));
        logger.debug("charset: {}", map.get("charset"));
        logger.debug("timestamp: {}", map.get("timestamp"));
        logger.debug("version: {}", map.get("version"));
        logger.debug("auth_app_id: {}", map.get("auth_app_id"));
        logger.debug("out_trade_no: {}", map.get("out_trade_no"));
        logger.debug("trade_no: {}", map.get("trade_no"));
        logger.debug("total_amount: {}", map.get("total_amount"));
        logger.debug("seller_id: {}", map.get("seller_id"));
        ModelAndView mv = new ModelAndView();
        String orderNumber = map.get("out_trade_no").toString();
        Order order = orderService.findByOrderNumber(orderNumber);
        if (order != null) {
            orderService.complete(order);
            mv.setViewName("redirect:/order/" + orderNumber);
        } else {
            mv.setViewName("/error");
        }
        return mv;
    }

    @RequestMapping(value = "/payment/paypal/request", method = RequestMethod.POST)
    public void paypalRequest() {
    }

    @RequestMapping(value = "/payment/paypal/response", method = RequestMethod.GET)
    public ModelAndView paypalResponse(@RequestParam Map map) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/order/" + map.get("out_trade_no").toString());
        return mv;
    }
}
