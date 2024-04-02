package com.example.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.example.design.strategy.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanghao
 * @createTime 2024/4/2 10:34
 * @description
 */
@Configuration
public class AliPayConfig {

    @Value("${alipay.gateway}")
    private String gateway;
    @Value("${alipay.appId}")
    private String appid;
    @Value("${alipay.privateKey}")
    private String privateKey;
    @Value("${alipay.publicKey}")
    private String publicKey;

    @Value("${alipay.signType}")
    private String signType;
    @Bean
    public AlipayClient initAliPayClient() {
        AlipayClient alipayClient = new DefaultAlipayClient(gateway,
                appid, privateKey, "JSON", "UTF-8",
                publicKey, signType);
        return alipayClient;
    }
}
