package org.ecnu.ljw.controller;

import lombok.extern.slf4j.Slf4j;
import org.ecnu.ljw.entity.CommonResult;
import org.ecnu.ljw.entity.Payment;
import org.ecnu.ljw.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        //调用默认等待1秒钟，此处故意等待3秒钟
        return paymentService.paymentFeignTimeOut();
    }
}
