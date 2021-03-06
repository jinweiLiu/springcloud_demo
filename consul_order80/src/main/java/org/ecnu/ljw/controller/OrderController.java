package org.ecnu.ljw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    private  static  final String INVOKE_URL = "http://payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/cs")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/cs",String.class);
        return result;
    }

}
