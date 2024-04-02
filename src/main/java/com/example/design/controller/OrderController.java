package com.example.design.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.design.service.OrderService;
import com.example.design.state.stateMacyhinne.pojo.Order;
import com.example.design.strategy.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    public String payOrder (@RequestParam String orderId,@RequestParam Float price,@RequestParam Integer payType){
        return orderService.getPayUrl(orderId,price,payType);
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

    @RequestMapping("/aliPayCallback")
    public String alipayCallback(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException, UnsupportedEncodingException {
        // 获取回调信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            //避免出现乱码
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            params.put(name, valueStr);
        }
        //验证签名，确保回调接口真的是支付宝平台触发的
        boolean signVerified = AlipaySignature.rsaCheckV1(params, Constants.ALIPAY_PUBLIC_KEY, "UTF-8", Constants.SIGN_TYPE); // 调用SDK验证签名
        //确定是 支付宝平台 发起的回调
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("=========="+out_trade_no);
            // 支付宝流水号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付金额
            float total_amount = Float.parseFloat(new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8"));
            //进行相关的业务操作
            Order order = orderService.pay(out_trade_no);

            return "支付成功页面跳转, 当前订单为：" + order;
        }else{
            throw new UnsupportedOperationException("callback verify failed!");
        }
    }
}
