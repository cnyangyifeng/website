package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.portal.web.vo.OrderReqVo;
import com.mocktpo.util.EmailUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class OrderController {


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
                orderReqVo.setOrderNumber(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                switch (orderReqVo.getPid()) {
                    case 1:
                        orderReqVo.setPrice(99.00);
                        break;
                    case 2:
                        orderReqVo.setPrice(299.00);
                        break;
                    default:
                        orderReqVo.setPrice(99.00);
                }
                mv.addObject("orderReqVo", orderReqVo);
                mv.setViewName("order");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mv;
    }

    @RequestMapping(value = "/order/{orderNumber}", method = RequestMethod.GET)
    public ModelAndView check(@PathVariable(value = "orderNumber") String orderNumber) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order_complete");
        return mv;
    }
}
