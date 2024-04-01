package com.example.design.orderManager;

import com.example.design.state.stateMacyhinne.pojo.Order;

/**
 * @author yanghao
 * @createTime 2024/4/1 14:18
 * @description
 */

public class OrderCommanndInvoker {

    public void invoke(OrderCommondInterface command, Order order) {
        command.execute(order);
    }
}
