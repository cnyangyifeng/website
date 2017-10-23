package com.mocktpo.util;

import com.mocktpo.orm.domain.Order;

public class LicenseHelper {

    private LicenseHelper() {
    }

    public static String preparePlainText(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("order_number=");
        sb.append(order.getOrderNumber());
        sb.append("\npid=");
        sb.append(order.getPid());
        sb.append("\nemail=");
        sb.append(order.getEmail());
        sb.append("\npayment_type=");
        sb.append(order.getPaymentType());
        sb.append("\nprice=");
        sb.append(order.getPrice());
        sb.append("\nstatus=");
        sb.append(order.getStatus());
        sb.append("\nactivation_code=");
        sb.append(order.getActivationCode());
        sb.append("\nhardware=");
        sb.append(order.getHardware());
        return sb.toString();
    }
}
