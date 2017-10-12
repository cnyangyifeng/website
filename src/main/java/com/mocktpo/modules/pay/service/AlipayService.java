package com.mocktpo.modules.pay.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.mocktpo.modules.portal.web.vo.OrderReqVo;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class AlipayService {

    protected static final ResourceBundle msgs = ResourceBundle.getBundle("alipay");

    private AlipayClient cli = new DefaultAlipayClient(
            msgs.getString("gateway"), msgs.getString("app_id"),
            msgs.getString("private_key"), "json", "utf-8", msgs.getString("public_key"), "RSA2");

    public String pay(OrderReqVo orderReqVo) throws AlipayApiException {
        AlipayTradePagePayRequest req = new AlipayTradePagePayRequest();
        req.setReturnUrl(msgs.getString("return_url"));
        req.setNotifyUrl(msgs.getString("notify_url"));
        AlipayBizContent bc = new AlipayBizContent();
        bc.setOutTradeNo(orderReqVo.getOrderNumber());
        bc.setProductCode(msgs.getString("product_code"));
        bc.setTotalAmount(Double.toString(orderReqVo.getPrice()));
        bc.setSubject(getSubject(orderReqVo));
        bc.setBody(getBody());
        req.setBizContent(JSON.toJSONString(bc));
        return cli.pageExecute(req).getBody();
    }

    private String getSubject(OrderReqVo orderReqVo) {
        String subject = "";
        switch (orderReqVo.getPid()) {
            case 1:
                subject = "MockTPO Basic";
                break;
            case 2:
                subject = "MockTPO Professional";
                break;
        }
        return subject;
    }

    private String getBody() {
        return "MockTPO is a TOEFL Practice Offline Applicaiton.";
    }
}
