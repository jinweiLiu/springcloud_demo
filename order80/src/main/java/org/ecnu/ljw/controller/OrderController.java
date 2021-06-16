package org.ecnu.ljw.controller;

import org.ecnu.ljw.entity.CommonResult;
import org.ecnu.ljw.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController{
    //public static final String PaymentSrv_URL = "http://localhost:8001";
    public static final String PaymentSrv_URL = "http://PAYMENT-SERVICE"; //eureka中的服务名

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create") //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PaymentSrv_URL + "/payment/create",payment,CommonResult.class);
    }

    //返回对象为响应体中数据转化成的对象，基本上可以理解为JSON
    @GetMapping("/consumer/payment/getObject/{id}")
    public CommonResult getPayment(@PathVariable Long id){
        return restTemplate.getForObject(PaymentSrv_URL + "/payment/get/"+id, CommonResult.class, id);
    }

    //返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult getEntity(@PathVariable Long id){
        ResponseEntity<CommonResult> result = restTemplate.getForEntity(PaymentSrv_URL + "/payment/get/"+id,CommonResult.class);
        if(result.getStatusCode().is2xxSuccessful()){
            return result.getBody();
        }else{
            return new CommonResult(444,"操作失败");
        }
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymenZipkin(){
        String result = restTemplate.getForObject(PaymentSrv_URL + "/payment/zipkin",String.class);
        return result;
    }
}
