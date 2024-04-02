package com.example.design.factory;

import com.example.design.strategy.PayContext;
import com.example.design.strategy.StrategyInterface;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author yanghao
 * @createTime 2024/4/1 16:46
 * @description
 */
@Component
public class PayFactory extends AbstractPayFactory<PayContext> {

    public static final Map<String, PayContext> payContexts = new ConcurrentHashMap<>();

    @Override
    public PayContext getContext(Integer payType) {
        StrategyEnum strategyEnum = payType == 1 ? StrategyEnum.alipay :
                payType == 2 ? StrategyEnum.weixin : null;
        if (strategyEnum == null) {
            throw new UnsupportedOperationException();
        }
        PayContext context = payContexts.get(strategyEnum.name());
        if (context==null){
            throw new UnsupportedOperationException("unsupport pay type");
        }
        return context;
    }
}
