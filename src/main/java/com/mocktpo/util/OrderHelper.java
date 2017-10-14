package com.mocktpo.util;

import com.mocktpo.modules.portal.web.vo.OrderVo;
import com.mocktpo.orm.domain.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderHelper {

    public static final int PRODUCT_ID_BASIC = 1;
    public static final int PRODUCT_ID_PROFESSIONAL = 2;
    public static final int STATUS_UNKNOWN = 0;
    public static final int STATUS_CREATED = 1;
    public static final int STATUS_PENDING = 2;
    public static final int STATUS_FAILED = 3;
    public static final int STATUS_CANCELLED = 4;
    public static final int STATUS_COMPLETED = 5;
    public static final int STATUS_EXPIRED = 6;

    private static final ResourceBundle msgs = ResourceBundle.getBundle("messages");

    private OrderHelper() {
    }

    public static Order prepareOrder(OrderVo orderVo) {
        Order order = new Order();
        if (orderVo != null) {
            order.setOrderNumber(orderVo.getOrderNumber());
            order.setPid(orderVo.getPid());
            order.setEmail(orderVo.getEmail());
            order.setPaymentType(orderVo.getPaymentType());
            order.setPrice(orderVo.getPrice());
            order.setStatus(orderVo.getStatus());
        }
        return order;
    }

    public static OrderVo prepareOrderVo(Order order) {
        OrderVo orderVo = new OrderVo();
        if (order != null) {
            orderVo.setOrderNumber(order.getOrderNumber());
            orderVo.setPid(order.getPid());
            orderVo.setEmail(order.getEmail());
            orderVo.setPaymentType(order.getPaymentType());
            orderVo.setPrice(order.getPrice());
            orderVo.setStatus(order.getStatus());
        }
        return orderVo;
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
