package com.ylz.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.ylz.alipay.config.Alipay;
import com.ylz.alipay.entity.Order;
import com.ylz.alipay.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: csp1999
 * @Date: 2020/11/13/21:56
 * @Description: 支付service 实现类
 */
@Service
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(Order order) throws AlipayApiException {
        return alipay.pay(order);
    }
}
