package com.example.design.factory;

import com.example.design.strategy.PayContext;

/**
 * @author yanghao
 * @createTime 2024/4/1 16:46
 * @description
 */

public abstract class AbstractPayFactory<T> {

    public abstract T getContext(Integer payType);
}
