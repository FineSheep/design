package com.example.design.service;

import com.example.design.facade.PayFacade;
import com.example.design.orderManager.OrderCommand;
import com.example.design.orderManager.OrderCommanndInvoker;
import com.example.design.state.stateMacyhinne.enums.OrderState;
import com.example.design.state.stateMacyhinne.enums.OrderStateChangeAction;
import com.example.design.state.stateMacyhinne.pojo.Order;
import com.example.design.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author yanghao
 * @createTime 2024/3/30 15:35
 * @description
 */
@Service
public class OrderService   {
    //依赖注入Spring状态机，与状态机进行交互
    @Autowired
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;
    //依赖注入Spring状态机的RedisPersister存取工具，持久化状态机
    @Autowired
    private StateMachinePersister<OrderState, OrderStateChangeAction, String> stateMachineRedisPersister;
    //依赖注入RedisCommonProcessor，存取订单对象
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Autowired
    private OrderCommand orderCommand;

    //订单创建
    public Order createOrder(String productId) {
        //此处orderId，需要生成全局的唯一ID，在4.4.2.1小节，笔者已经做过详细引申
        String orderId = "OID"+productId;
        //创建订单并存储到Redis
        Order order = Order.builder()
                .orderId(orderId)
                .productId(productId)
                .orderState(OrderState.ORDER_WAIT_PAY)
                .build();
        redisCommonProcessor.set(order.getOrderId(), order, 900);

        OrderCommanndInvoker invoker = new OrderCommanndInvoker();
        invoker.invoke(orderCommand,order);
        return order;
    }

    //订单支付
    public Order pay(String orderId) {
        //从Redis中获取 订单
        Order order = (Order) redisCommonProcessor.get(orderId);
        //包装 订单状态变更 Message，并附带订单操作 PAY_ORDER
        Message message = MessageBuilder
                .withPayload(OrderStateChangeAction.PAY_ORDER).setHeader("order", order).build();
        //将Message传递给Spring状态机

        if(changeStateAction(message,order)) {
            return order;
        }
        return null;
    }
    //订单发送
    public Order send(String orderId) {
        //从Redis中获取 订单
        Order order = (Order) redisCommonProcessor.get(orderId);
        //包装 订单状态变更 Message，并附带订单操作 SEND_ORDER
        Message message = MessageBuilder
                .withPayload(OrderStateChangeAction.SEND_ORDER).setHeader("order", order).build();
        //将Message传递给Spring状态机
        if(changeStateAction(message,order)) {
            return order;
        }
        return null;
    }
    //订单签收
    public Order receive(String orderId) {
        //从Redis中获取 订单
        Order order = (Order) redisCommonProcessor.get(orderId);
        //包装 订单状态变更 Message，并附带订单操作 RECEIVE_ORDER
        Message message = MessageBuilder
                .withPayload(OrderStateChangeAction.RECEIVE_ORDER).setHeader("order", order).build();
        //将Message传递给Spring状态机
        if(changeStateAction(message,order)) {
            return order;
        }
        return null;
    }
    //状态机的相关操作
    private boolean changeStateAction(Message<OrderStateChangeAction> message, Order order) {
        try {
            //启动状态机
            orderStateMachine.start();
            //从Redis缓存中读取状态机，缓存的Key为orderId+"STATE"，这是自定义的，可以根据自己喜好定义
            stateMachineRedisPersister.restore(orderStateMachine, order.getOrderId()+"STATE");
            //将Message发送给OrderStateListener
            boolean res = orderStateMachine.sendEvent(message);
            //将更改完订单状态的 状态机 存储到 Redis缓存
            stateMachineRedisPersister.persist(orderStateMachine, order.getOrderId()+"STATE");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return false;
    }

    @Autowired
    private PayFacade payFacade;

    public String getPayUrl(String orderId, Float price, Integer payType) {

        Order order = (Order) redisCommonProcessor.get(orderId);
        order.setPrice(price);
        return payFacade.pay(order, payType);
    }
}
