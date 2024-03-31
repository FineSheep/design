package com.example.design.state.stateMacyhinne.pojo;

import com.example.design.state.stateMacyhinne.enums.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanghao
 * @createTime 2024/3/30 14:15
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String productId;
    private OrderState orderState;
    private Float price;
}
