package com.mocktpo.modules.order;

import com.mocktpo.orm.domain.Order;
import com.mocktpo.orm.mapper.OrderMapper;
import com.mocktpo.util.OrderHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper mapper;

    public Order findByOrderNumber(String orderNumber) {
        return mapper.findByOrderNumber(orderNumber);
    }

    public Order findByActivationCode(String activationCode) {
        return mapper.findByActivationCode(activationCode);
    }

    public void create(Order order) {
        mapper.create(order);
    }

    public void update(Order order) {
        mapper.update(order);
    }

    public void complete(Order order) {
        if (order.getStatus() != OrderHelper.STATUS_COMPLETED) {
            order.setStatus(OrderHelper.STATUS_COMPLETED);
        }
        if (StringUtils.isEmpty(order.getActivationCode())) {
            order.setActivationCode(OrderHelper.prepareActivationCode());
        }
        update(order);
    }
}
