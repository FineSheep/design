package com.example.config;

import com.example.design.state.stateMacyhinne.enums.OrderState;
import com.example.design.state.stateMacyhinne.enums.OrderStateChangeAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * @author yanghao
 * @createTime 2024/3/30 14:19
 * @description
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig  extends StateMachineConfigurerAdapter<OrderState, OrderStateChangeAction> {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderStateChangeAction> states) throws Exception {
       //初始状态为待支付
        states.withStates().initial(OrderState.ORDER_WAIT_PAY)
                //加载所有的状态到配置中
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderStateChangeAction> transitions) throws Exception {
        transitions.withExternal()
                //ORDER_WAIT_PAY到ORDER_WAIT_SEND需要PAY_ORDER操作
                .source(OrderState.ORDER_WAIT_PAY)
                .target(OrderState.ORDER_WAIT_SEND)
                .event(OrderStateChangeAction.PAY_ORDER)
                .and()
                .withExternal()
                //ORDER_WAIT_SEND到ORDER_WAIT_RECEIVE需要SEND_ORDER操作
                .source(OrderState.ORDER_WAIT_SEND)
                .target(OrderState.ORDER_WAIT_RECEIVE)
                .event(OrderStateChangeAction.SEND_ORDER)
                .and()
                //ORDER_WAIT_RECEIVE到ORDER_WAIT_FINISH需要RECEIVE_ORDER操作9999999999999999999999999999999
                .withExternal()
                .source(OrderState.ORDER_WAIT_RECEIVE)
                .target(OrderState.ORDER_FINISH)
                .event(OrderStateChangeAction.RECEIVE_ORDER);
    }


    @Bean(name = "stateMachineRedisPersister")
    public RedisStateMachinePersister<OrderState, OrderStateChangeAction> getRedisPersister() {
        RedisStateMachineContextRepository<OrderState, OrderStateChangeAction> repository
                = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        RepositoryStateMachinePersist p
                = new RepositoryStateMachinePersist<>(repository);
        return new RedisStateMachinePersister<>(p);
    }
}
