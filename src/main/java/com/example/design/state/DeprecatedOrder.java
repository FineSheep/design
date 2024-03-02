package com.example.design.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanghao
 * @createTime 2024/3/2 13:35
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeprecatedOrder {
    private String orderId;
    private String productId;
    private String state;
}
