package com.example.design.state.concreteState;

import com.example.design.state.DeprecatedAbstractOrderState;
import com.example.design.state.DeprecatedOrder;
import com.example.design.state.DeprecatedOrderContext;
import com.example.design.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yanghao
 * @createTime 2024/3/2 13:50
 * @description
 */
@Component
public class DeprecatedReceiveOrder extends DeprecatedAbstractOrderState {

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    public DeprecatedOrder receiveOrder(String orderId, DeprecatedOrderContext context) {
        DeprecatedOrder order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_RECEIVE)){
            throw new UnsupportedOperationException("state error");
        }
        order.setState(ORDER_FINNISH);
        redisCommonProcessor.remove(orderId);
        return order;
    }
}
