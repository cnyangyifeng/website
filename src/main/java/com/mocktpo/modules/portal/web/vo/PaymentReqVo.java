package com.mocktpo.modules.portal.web.vo;

import java.io.Serializable;

public class PaymentReqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pid;
    private String email;
    private int paymentType;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
}
