package com.example.design.orderManager;

import com.example.design.state.stateMacyhinne.pojo.Order;

/**
 * @author yanghao
 * @createTime 2024/4/1 14:14
 * @description
 */

public interface OrderCommondInterface {
    void execute(Order order);
}
