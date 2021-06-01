package org.ecnu.ljw.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池 " + Thread.currentThread().getName() + "paymentInfo_OK " + id;
    }

    /**
     * 超时
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    //3秒以内为正常逻辑
    public String paymentInfo_TimeOut(Integer id){
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池 " + Thread.currentThread().getName() + "paymentInfo_TimeOut " + id;
    }

    //超时异常和运行异常处理 int age = 10/0
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池 " + Thread.currentThread().getName() + "稍后再试 " + id;
    }
}
