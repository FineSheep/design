package com.example.design.strategy;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.design.state.stateMacyhinne.pojo.Order;

/**
 * @author yanghao
 * @createTime 2024/4/1 15:19
 * @description
 */

public class AliPayStrategy implements StrategyInterface{
    @Override
    public String pay(Order order) {
        //创建 Alipay client
        AlipayClient alipayClient = new DefaultAlipayClient(Constants.ALIPAY_GATEWAY,
                Constants.APP_ID,Constants.APP_PRIVATE_LEY,"JSON","UTF-8",
                Constants.ALIPAY_PUBLIC_KEY,Constants.SIGN_TYPE);
        //设置请求参数
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setReturnUrl(Constants.CALLBACK_URL);
        payRequest.setBizContent("{\"out_trade_no\":\"" + order.getOrderId() + "\","
                + "\"total_amount\":\"" + order.getPrice() + "\","
                + "\"subject\":\"" + "test" + "\","
                + "\"body\":\"" + "商品描述" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        try {
            String result = alipayClient.pageExecute(payRequest,"GET").getBody();
            return result;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Alipay failed! " + e);
        }
    }
}
