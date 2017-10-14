package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.portal.service.EmailService;
import com.mocktpo.modules.portal.service.OrderService;
import com.mocktpo.modules.portal.web.vo.OrderVo;
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
    private EmailService emailService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/create", method = RequestMethod.GET)
    public ModelAndView createOrderView(String err) {
        ModelAndView mv = new ModelAndView();
        OrderVo vo = new OrderVo();
        mv.addObject("orderVo", vo);
        mv.addObject("err", err);
        mv.setViewName("buy");
        return mv;
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public ModelAndView createOrderView(OrderVo orderVo) {
        ModelAndView mv = new ModelAndView();
        String email = orderVo.getEmail();
        try {
            if (StringUtils.isEmpty(email) || !EmailUtils.validate(email)) {
                mv.setViewName("redirect:/buy?err=invalid_email");
            } else {
                orderVo.setOrderNumber(OrderHelper.prepareOrderNumber());
                orderVo.setPrice(OrderHelper.preparePrice(orderVo.getPid()));
                orderVo.setStatus(OrderHelper.STATUS_CREATED);
                mv.addObject("orderVo", orderVo);
                mv.setViewName("create_order");
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
            emailService.sendActivationEmail();
            mv.addObject("orderVo", OrderHelper.prepareOrderVo(order));
            mv.setViewName("order_info");
        } else {
            mv.setViewName("error");
        }
        return mv;
    }
}
