package org.ecnu.ljw.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "payment fall back ok";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "payment fall back Timeout";
    }
}
