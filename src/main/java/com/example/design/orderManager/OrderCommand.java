package com.example.design.orderManager;

import com.example.design.state.stateMacyhinne.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yanghao
 * @createTime 2024/4/1 14:16
 * @description
 */
@Component
public class OrderCommand implements OrderCommondInterface{

    @Autowired
    private OrderCommandReceiver orderCommandReceiver;
    @Override
    public void execute(Order order) {
         orderCommandReceiver.action(order);
    }
}
