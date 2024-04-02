package com.example.design.strategy;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.design.factory.PayFactory;
import com.example.design.factory.StrategyEnum;
import com.example.design.state.stateMacyhinne.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.ws.Action;

/**
 * @author yanghao
 * @createTime 2024/4/1 15:19
 * @description
 */
@Component
public class AliPayStrategy implements StrategyInterface {

    @Autowired
    private AlipayClient alipayClient;
    @PostConstruct
    private void innitPay(){
        PayFactory.payContexts.put(StrategyEnum.alipay.name(), new PayContext(this));
    }
    @Override
    public String pay(Order order) {
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
            String result = alipayClient.pageExecute(payRequest, "GET").getBody();
            return result;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Alipay failed! " + e);
        }
    }
}
