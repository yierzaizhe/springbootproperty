package com.ylz.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.ylz.alipay.entity.Order;
import com.ylz.alipay.service.AliPayService;
import com.ylz.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PayController {

    @Autowired
    private AliPayService aliPayService;

    /**
     * 支付宝支付 api
     *
     * @param outTradeNo
     * @param subject
     * @param totalAmount
     * @param description
     * @return
     * @throws AlipayApiException
     */
    @PostMapping(value = "/order/alipay")
    public String alipay(String outTradeNo, String subject,
                         String totalAmount, String description) throws AlipayApiException {
        Order order = new Order();
        order.setOut_trade_no(StringUtil.getUUID());
        //order.setOut_trade_no(outTradeNo);
        order.setSubject(subject);
        order.setTotal_amount(totalAmount);
        order.setDescription(description);
        System.out.println(order);
        return aliPayService.aliPay(order);
    }
}
