package com.example.design.visitor;

import com.example.design.combination.AbstractProductItem;

/**
 * @author yanghao
 * @createTime 2024/1/27 16:53
 * @description
 */
public interface ItemVisitor<T> {

    T visitor(AbstractProductItem productItem);
}
