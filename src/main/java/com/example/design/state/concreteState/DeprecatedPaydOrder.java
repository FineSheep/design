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
public class DeprecatedPaydOrder extends DeprecatedAbstractOrderState {

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Autowired
    private DeprecatedSendOrder deprecatedSendOrder;

    @Override
    protected DeprecatedOrder payOrder(String orderId, DeprecatedOrderContext context) {

        DeprecatedOrder order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_PAY)){
            throw new UnsupportedOperationException("state error");
        }
        order.setState(ORDER_WAIT_SEND);

        context.setCurrentState(deprecatedSendOrder);
        return order;
    }
}
