package com.example.design.state;

import com.example.design.state.concreteState.DeprecatedCreatedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单操作类
 * @author yanghao
 * @createTime 2024/3/2 13:36
 * @description
 */
@Component
public class DeprecatedOrderContext {

    private DeprecatedAbstractOrderState currentState;

    @Autowired
    private DeprecatedCreatedOrder deprecatedCreatedOrder;

    public void setCurrentState(DeprecatedAbstractOrderState currentState) {
        this.currentState = currentState;
    }

    public DeprecatedOrder createOrder(String orderId, String productId) {
        this.currentState = this.deprecatedCreatedOrder;
        return currentState.createOrder(orderId, productId, this);
    }

    public DeprecatedOrder sendOrder(String orderId) {
        return currentState.sendOrder(orderId, this);
    }

    public DeprecatedOrder payOrder(String orderId) {
        return currentState.payOrder(orderId, this);
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        return currentState.receiveOrder(orderId, this);
    }
}
