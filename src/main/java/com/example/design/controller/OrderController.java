package com.example.design.controller;

import com.example.design.service.OrderService;
import com.example.design.state.stateMacyhinne.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单服务
 * @author yanghao
 * @createTime 2024/3/30 15:34
 * @description
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param productId 产品id
     * @return
     */
    @PostMapping("create")
    public Order createOrder (@RequestParam String productId){
        return orderService.createOrder(productId);
    }

    /**
     * 支付订单
     * @param orderId 订单id
     * @return
     */
    @PostMapping("pay")
    public Order payOrder (@RequestParam String orderId){
        return orderService.pay(orderId);
    }

    /**
     * 发货
     * @param orderId 订单id
     * @return
     */
    @PostMapping("send")
    public Order send (@RequestParam String orderId){
        return orderService.send(orderId);
    }
/**
     * 收货
     * @param orderId 订单id
     * @return
     */
    @PostMapping("receive")
    public Order receive (@RequestParam String orderId){
        return orderService.receive(orderId);
    }
}
