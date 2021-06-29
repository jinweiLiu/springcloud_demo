package org.ecnu.ljw.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.ecnu.ljw.entity.CommonResult;
import org.ecnu.ljw.entity.Payment;
import org.ecnu.ljw.handler.MyHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName() + "\t服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }

    @GetMapping("/rateLimit/MyHandler")
    @SentinelResource(value = "MyHandler",blockHandlerClass = MyHandler.class, blockHandler = "handlerException2") //指定类和方法
    public CommonResult MyHandler(){
        return new CommonResult(200,"按自定义限流测试OK",new Payment(2020L,"serial003"));
    }
}
