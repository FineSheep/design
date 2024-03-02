package com.example.design.state;

/**
 * 状态模式
 *
 * @author yanghao
 * @createTime 2024/3/2 13:31
 * @description
 */

public abstract class DeprecatedAbstractOrderState {

    protected final String ORDER_WAIT_PAY = "ORDER_WAIT_PAY";
    protected final String ORDER_WAIT_SEND = "ORDER_WAIT_SEND";
    protected final String ORDER_WAIT_RECEIVE = "ORDER_WAIT_RECEIVE";
    protected final String ORDER_FINNISH = "ORDER_FINNISH";

    protected DeprecatedOrder createOrder(String orderId, String productId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder sendOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder payOrder(String orderId,  DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder receiveOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }
}
