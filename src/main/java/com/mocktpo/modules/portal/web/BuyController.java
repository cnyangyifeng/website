package com.mocktpo.modules.portal.web;

import com.mocktpo.modules.portal.web.vo.OrderReqVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuyController {

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ModelAndView toBuyView(String err) {
        ModelAndView mv = new ModelAndView();
        OrderReqVo vo = new OrderReqVo();
        mv.addObject("orderReqVo", vo);
        mv.addObject("err", err);
        mv.setViewName("buy");
        return mv;
    }
}
