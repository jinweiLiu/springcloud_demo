package org.ecnu.ljw.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        //线程数的测试
        /*
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        */
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "-----testB";
    }

    @GetMapping("/testD")
    public String testD(){
        //验证服务降级规则RT
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //验证服务降级规则 异常比例
        //int div = 10 / 0; //异常比例为100%
        log.info("testD 测试RT");
        return "-----testD";
    }

    @GetMapping("/testE")
    public String testE(){
        //验证服务降级规则 异常数
        int div = 10 / 0;
        log.info("testE 测试异常数");
        return "-----testE";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        //int age = 10 / 0; //SentinelResource不管runtime exception
        return "-----testHotKey";
    }

    //降级处理方法
    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-----deal_testHotKey";
    }
}
