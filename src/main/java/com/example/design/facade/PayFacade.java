package com.example.design.facade;

import com.example.design.factory.PayFactory;
import com.example.design.state.stateMacyhinne.pojo.Order;
import com.example.design.strategy.PayContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yanghao
 * @createTime 2024/4/1 16:44
 * @description
 */
@Component
public class PayFacade {

    @Autowired
    private PayFactory payFactory;

    public String pay(Order order,Integer type){
        PayContext context = payFactory.getContext(type);
        return context.execute(order);
    }
}
