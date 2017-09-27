package com.mocktpo.modules.pay.service;

import com.alibaba.fastjson.annotation.JSONField;

public class AlipayBizContent {

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    @JSONField(name = "product_code")
    private String productCode;

    @JSONField(name = "total_amount")
    private String totalAmount;

    @JSONField(name = "subject")
    private String subject;

    @JSONField(name = "body")
    private String body;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
