package com.example.design.state.stateMacyhinne.listener;

import com.example.design.orderManager.OrderCommand;
import com.example.design.orderManager.OrderCommanndInvoker;
import com.example.design.state.stateMacyhinne.enums.OrderState;
import com.example.design.state.stateMacyhinne.enums.OrderStateChangeAction;
import com.example.design.state.stateMacyhinne.pojo.Order;
import com.example.design.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @author yanghao
 * @createTime 2024/3/30 14:54
 * @description
 */
@Component
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListener {

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Autowired
    private OrderCommand orderCommand;

    @OnTransition(source = "ORDER_WAIT_PAY",target = "ORDER_WAIT_SEND")
    public boolean payToSennd(Message<OrderStateChangeAction> message){
        Order order = (Order) message.getHeaders().get("order");
        if (order.getOrderState()!= OrderState.ORDER_WAIT_PAY){
            throw new UnsupportedOperationException();
        }

        order.setOrderState(OrderState.ORDER_WAIT_SEND);
        redisCommonProcessor.set(order.getOrderId(), order);

        OrderCommanndInvoker invoker = new OrderCommanndInvoker();
        invoker.invoke(orderCommand,order);
        return true;
    }

    @OnTransition(source = "ORDER_WAIT_RECEIVE", target = "ORDER_FINISH")
    public boolean receiveToFinish(Message<OrderStateChangeAction> message){
        Order order = (Order) message.getHeaders().get("order");
        if(order.getOrderState() != OrderState.ORDER_WAIT_RECEIVE) {
            throw new UnsupportedOperationException("Order state error!");
        }
        order.setOrderState(OrderState.ORDER_FINISH);
        redisCommonProcessor.remove(order.getOrderId());

        OrderCommanndInvoker invoker = new OrderCommanndInvoker();
        invoker.invoke(orderCommand,order);
        return true;
    }
}
