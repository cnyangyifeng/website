package com.mocktpo.modules.pay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.mocktpo.orm.domain.Order;
import com.mocktpo.util.OrderHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class AlipayService {

    private static final ResourceBundle msgs = ResourceBundle.getBundle("alipay_sandbox");

    private AlipayClient cli = new DefaultAlipayClient(
            msgs.getString("gateway"), msgs.getString("app_id"),
            msgs.getString("mocktpo_private_key"), "json", "utf-8", msgs.getString("alipay_public_key"), "RSA2");

    public String pay(Order order) throws AlipayApiException {
        AlipayTradePagePayRequest req = new AlipayTradePagePayRequest();
        req.setReturnUrl(msgs.getString("return_url"));
        req.setNotifyUrl(msgs.getString("notify_url"));
        AlipayTradePagePayBizContent bc = new AlipayTradePagePayBizContent();
        bc.setOutTradeNo(order.getOrderNumber());
        bc.setProductCode(msgs.getString("product_code"));
        bc.setTotalAmount(Double.toString(OrderHelper.preparePrice(order.getPid())));
        bc.setSubject(OrderHelper.prepareProductName(order.getPid()));
        bc.setBody("");
        req.setBizContent(JSON.toJSONString(bc));
        return cli.pageExecute(req).getBody();
    }

    public boolean isOrderCompleted(String orderNumber) throws AlipayApiException {
        AlipayTradeQueryRequest req = new AlipayTradeQueryRequest();
        AlipayTradeQueryBizContent bc = new AlipayTradeQueryBizContent();
        bc.setOutTradeNo(orderNumber);
        req.setBizContent(JSON.toJSONString(bc));
        AlipayTradeQueryResponse resp = cli.execute(req);
        if (resp != null && !StringUtils.isEmpty(resp.getTradeStatus())) {
            if (resp.getTradeStatus().equals("TRADE_SUCCESS")) {
                return true;
            }
        }
        return false;
    }
}
