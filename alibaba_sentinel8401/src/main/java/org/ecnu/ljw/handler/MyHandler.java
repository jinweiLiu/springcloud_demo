package org.ecnu.ljw.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.ecnu.ljw.entity.CommonResult;
import org.ecnu.ljw.entity.Payment;

public class MyHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444,"按自定义限流测试OK 111",new Payment(2020L,"serial001"));
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444,"按自定义限流测试OK 222",new Payment(2020L,"serial002"));
    }
}
