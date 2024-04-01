package com.example.design.strategy;

import com.example.design.state.stateMacyhinne.pojo.Order;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

/**
 * @author yanghao
 * @createTime 2024/4/1 15:21
 * @description
 */

public class PayContext {
    private StrategyInterface strategyInterface;

    public PayContext (StrategyInterface payStrategy){
        this.strategyInterface = payStrategy;
    }

    public String execute(Order order){
        return strategyInterface.pay(order);
    }
}
