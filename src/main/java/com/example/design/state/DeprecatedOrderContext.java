package com.example.design.state;

import com.example.design.state.concreteState.DeprecatedCreatedOrder;
import com.example.design.state.concreteState.DeprecatedPaydOrder;
import com.example.design.state.concreteState.DeprecatedReceiveOrder;
import com.example.design.state.concreteState.DeprecatedSendOrder;
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

//    private DeprecatedAbstractOrderState currentState;

    @Autowired
    private  DeprecatedCreatedOrder deprecatedCreatedOrder;
    @Autowired
    private DeprecatedPaydOrder deprecatedPaydOrder;
    @Autowired
    private DeprecatedSendOrder deprecatedSendOrder;
    @Autowired
    private DeprecatedReceiveOrder deprecatedReceiveOrder;


//    public void setCurrentState(DeprecatedAbstractOrderState currentState) {
//        this.currentState = currentState;
//    }

    public DeprecatedOrder createOrder(String orderId, String productId) {
//        this.currentState = this.deprecatedCreatedOrder;

        return deprecatedCreatedOrder.createOrder(orderId, productId, this);
    }

    public DeprecatedOrder sendOrder(String orderId) {
        return deprecatedSendOrder.sendOrder(orderId, this);
    }

    public DeprecatedOrder payOrder(String orderId) {
        return deprecatedPaydOrder.payOrder(orderId, this);
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        return deprecatedReceiveOrder.receiveOrder(orderId, this);
    }
}
