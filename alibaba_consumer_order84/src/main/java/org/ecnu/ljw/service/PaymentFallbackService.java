package org.ecnu.ljw.service;

import org.ecnu.ljw.entity.CommonResult;
import org.ecnu.ljw.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回，--paymenfallbackservice",new Payment(id,"errorSerial"));
    }
}
