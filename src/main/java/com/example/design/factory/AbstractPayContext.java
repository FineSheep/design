package com.example.design.factory;

import com.example.design.state.stateMacyhinne.pojo.Order;

/**
 * @author yanghao
 * @createTime 2024/4/1 16:49
 * @description
 */

public abstract class AbstractPayContext {

    public abstract String execute(Order order);
}
