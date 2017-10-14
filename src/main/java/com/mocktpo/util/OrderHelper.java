package com.mocktpo.util;

import com.mocktpo.modules.portal.web.vo.OrderReqVo;
import com.mocktpo.orm.domain.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderHelper {

    public static final int PRODUCT_ID_BASIC = 1;
    public static final int PRODUCT_ID_PROFESSIONAL = 2;
    public static final int STATUS_CREATED = 1;
    public static final int STATUS_PENDING = 2;
    public static final int STATUS_FAILED = 3;
    public static final int STATUS_CANCELLED = 4;
    public static final int STATUS_COMPLETED = 5;
    public static final int STATUS_EXPIRED = 6;

    private static final ResourceBundle msgs = ResourceBundle.getBundle("messages");

    private OrderHelper() {
    }

    public static Order prepareOrder(OrderReqVo orderReqVo) {
        Order order = new Order();
        order.setOrderNumber(orderReqVo.getOrderNumber());
        order.setPid(orderReqVo.getPid());
        order.setEmail(orderReqVo.getEmail());
        order.setPaymentType(orderReqVo.getPaymentType());
        order.setPrice(orderReqVo.getPrice());
        order.setStatus(orderReqVo.getStatus());
        return order;
    }

    public static OrderReqVo prepareOrderReqVo(Order order) {
        OrderReqVo orderReqVo = new OrderReqVo();
        orderReqVo.setOrderNumber(order.getOrderNumber());
        orderReqVo.setPid(order.getPid());
        orderReqVo.setEmail(order.getEmail());
        orderReqVo.setPaymentType(order.getPaymentType());
        orderReqVo.setPrice(order.getPrice());
        orderReqVo.setStatus(order.getStatus());
        return orderReqVo;
    }

    public static String prepareOrderNumber() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    public static String prepareProductName(int pid) {
        String productName;
        switch (pid) {
            case PRODUCT_ID_BASIC:
                productName = msgs.getString("mocktpo_basic");
                break;
            case PRODUCT_ID_PROFESSIONAL:
                productName = msgs.getString("mocktpo_professional");
                break;
            default:
                productName = msgs.getString("mocktpo_basic");
        }
        return productName;
    }

    public static double preparePrice(int pid) {
        double price;
        switch (pid) {
            case PRODUCT_ID_BASIC:
                price = 99.00;
                break;
            case PRODUCT_ID_PROFESSIONAL:
                price = 299.00;
                break;
            default:
                price = 99.00;
        }
        return price;
    }
}
