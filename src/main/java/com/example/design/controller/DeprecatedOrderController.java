package com.example.design.controller;

import com.example.design.service.DeprecatedOrderService;
import com.example.design.state.DeprecatedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @createTime 2024/3/2 14:43
 * @description
 */

@RestController
@RequestMapping("/deprecated/order")
public class DeprecatedOrderController {

    @Autowired
    private DeprecatedOrderService deprecatedOrderService;

    @PostMapping("create")
    public DeprecatedOrder createOrder(@RequestParam String productId){
        return  deprecatedOrderService.createOrder(productId);
    }

    @PostMapping("payOrder")
    public DeprecatedOrder payOrder(@RequestParam String orderId){
        return  deprecatedOrderService.pay(orderId);
    }

    @PostMapping("send")
    public DeprecatedOrder send(@RequestParam String orderId){
        return  deprecatedOrderService.send(orderId);
    }
    @PostMapping("receive")
    public DeprecatedOrder receive(@RequestParam String orderId){
        return  deprecatedOrderService.receive(orderId);
    }

}
