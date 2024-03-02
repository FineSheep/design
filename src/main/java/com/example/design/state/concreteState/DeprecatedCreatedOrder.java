package com.example.design.state.concreteState;

import com.example.design.state.DeprecatedAbstractOrderState;
import com.example.design.state.DeprecatedOrder;
import com.example.design.state.DeprecatedOrderContext;
import com.example.design.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yanghao
 * @createTime 2024/3/2 13:48
 * @description
 */
@Component
public class DeprecatedCreatedOrder extends DeprecatedAbstractOrderState {

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Autowired
    private DeprecatedPaydOrder paydOrder;

    @Override
    public DeprecatedOrder createOrder(String orderId, String productId, DeprecatedOrderContext context) {
        DeprecatedOrder order = DeprecatedOrder.builder()
                .orderId(orderId)
                .productId(productId)
                .state(ORDER_WAIT_PAY)
                .build();

        redisCommonProcessor.set(orderId, order, 900);
//        context.setCurrentState(this.paydOrder);
        return order;
    }
}
