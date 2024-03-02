package com.example.design.state.concreteState;

import com.example.design.state.DeprecatedAbstractOrderState;
import com.example.design.state.DeprecatedOrder;
import com.example.design.state.DeprecatedOrderContext;
import com.example.design.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yanghao
 * @createTime 2024/3/2 13:49
 * @description
 */
@Component
public class DeprecatedSendOrder extends DeprecatedAbstractOrderState {
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Autowired
    private DeprecatedReceiveOrder deprecatedReceiveOrder;

    @Override
    public DeprecatedOrder sendOrder(String orderId, DeprecatedOrderContext context) {

        DeprecatedOrder order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_SEND)){
            throw new UnsupportedOperationException("state error");
        }
        order.setState(ORDER_WAIT_RECEIVE);
//        context.setCurrentState(deprecatedReceiveOrder);
        return order;
    }
}
