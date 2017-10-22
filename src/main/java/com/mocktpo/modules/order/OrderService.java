package com.mocktpo.modules.order;

import com.mocktpo.orm.domain.Order;
import com.mocktpo.orm.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper mapper;

    public Order findByOrderNumber(String orderNumber) {
        return mapper.findByOrderNumber(orderNumber);
    }

    public void create(Order order) {
        mapper.create(order);
    }

    public void update(Order order) {
        mapper.update(order);
    }
}
