package com.example.design.strategy;

import com.example.design.state.stateMacyhinne.pojo.Order;
import org.aspectj.weaver.ast.Or;

/**
 * @author yanghao
 * @createTime 2024/4/1 15:19
 * @description
 */

public interface StrategyInterface {
    String pay(Order order);
}
