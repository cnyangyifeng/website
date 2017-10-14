package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.pay.service.AlipayService;
import com.mocktpo.modules.portal.service.OrderService;
import com.mocktpo.modules.portal.web.vo.OrderReqVo;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.EmailUtils;
import com.mocktpo.util.OrderHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayService alipayService;

    @RequestMapping(value = "/order/create", method = RequestMethod.GET)
    public ModelAndView createOrderView(String err) {
        ModelAndView mv = new ModelAndView();
        OrderReqVo vo = new OrderReqVo();
        mv.addObject("orderReqVo", vo);
        mv.addObject("err", err);
        mv.setViewName("buy");
        return mv;
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public ModelAndView createOrderView(OrderReqVo orderReqVo) {
        ModelAndView mv = new ModelAndView();
        String email = orderReqVo.getEmail();
        try {
            if (StringUtils.isEmpty(email) || !EmailUtils.validate(email)) {
                mv.setViewName("redirect:/buy?err=invalid_email");
            } else {
                orderReqVo.setOrderNumber(OrderHelper.prepareOrderNumber());
                orderReqVo.setPrice(OrderHelper.preparePrice(orderReqVo.getPid()));
                orderReqVo.setStatus(OrderHelper.STATUS_CREATED);
                mv.addObject("orderReqVo", orderReqVo);
                mv.setViewName("order");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/order/{orderNumber}", method = RequestMethod.GET)
    public ModelAndView toOrderView(@PathVariable(value = "orderNumber") String orderNumber) {
        ModelAndView mv = new ModelAndView();
        Order order = orderService.findByOrderNumber(orderNumber);
        if (order != null) {
            try {
                if (alipayService.query(order)) {
                    order.setStatus(OrderHelper.STATUS_COMPLETED);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mv.addObject("orderReqVo", OrderHelper.prepareOrderReqVo(order));
            mv.setViewName("order_complete");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }
}
