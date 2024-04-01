package com.example.design.orderManager;

import com.example.design.state.stateMacyhinne.pojo.Order;
import org.springframework.stereotype.Component;

/**
 * @author yanghao
 * @createTime 2024/4/1 14:13
 * @description
 */
@Component
public class OrderCommandReceiver {

    public void action(Order order){
        switch (order.getOrderState()){
            case ORDER_WAIT_PAY:
                System.out.println("订单等待支付");
                return;
            case ORDER_WAIT_SEND:
                System.out.println("订单等待发货");
                return;
            case ORDER_WAIT_RECEIVE:
                System.out.println("订单等待收货");
                return;
            case ORDER_FINISH:
                System.out.println("订单完成");
               break;
            default:
                throw new UnsupportedOperationException("state error");
           }
    }
}
