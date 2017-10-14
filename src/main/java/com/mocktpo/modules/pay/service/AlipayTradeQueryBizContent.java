package com.mocktpo.modules.pay.service;

import com.alibaba.fastjson.annotation.JSONField;

public class AlipayTradeQueryBizContent {

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
