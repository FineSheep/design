package com.example.design.service;

import com.example.design.state.DeprecatedOrder;
import com.example.design.state.DeprecatedOrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanghao
 * @createTime 2024/3/2 14:44
 * @description
 */
@Service
public class DeprecatedOrderService {

    @Autowired
    private DeprecatedOrderContext context;

    public DeprecatedOrder createOrder(String productId) {
        String orderId = "oid" + productId;
        return context.createOrder(orderId, productId);
    }

    public DeprecatedOrder pay(String orderId) {
        return context.payOrder(orderId);
    }

    public DeprecatedOrder send(String orderId) {
        return context.sendOrder(orderId);

    }

    public DeprecatedOrder receive(String orderId) {

        return context.receiveOrder(orderId);
    }
}
