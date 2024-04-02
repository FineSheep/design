package com.example.design.factory;

/**
 * @author yanghao
 * @createTime 2024/4/1 16:50
 * @description
 */

public enum StrategyEnum {
    alipay("1"),
    weixin("2");

    String value;

    StrategyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
