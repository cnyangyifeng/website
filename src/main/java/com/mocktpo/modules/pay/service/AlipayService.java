package com.mocktpo.modules.pay.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

@Service
public class AlipayService {

    protected static final ResourceBundle msgs = ResourceBundle.getBundle("alipay");

    private AlipayClient cli = new DefaultAlipayClient(
            msgs.getString("gateway"), msgs.getString("app_id"),
            msgs.getString("private_key"), "json", "utf-8", msgs.getString("public_key"), "RSA2");

    public String pay() throws AlipayApiException {
        AlipayTradePagePayRequest req = new AlipayTradePagePayRequest();
        req.setReturnUrl(msgs.getString("return_url"));
        req.setNotifyUrl(msgs.getString("notify_url"));
        AlipayBizContent bc = new AlipayBizContent();
        bc.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        bc.setProductCode(msgs.getString("product_code"));
        bc.setTotalAmount("288.00");
        bc.setSubject(msgs.getString("subject"));
        bc.setBody(msgs.getString("body"));
        req.setBizContent(JSON.toJSONString(bc));
        return cli.pageExecute(req).getBody();
    }
}
