package org.ecnu.ljw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HystrixOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(HystrixOrderMain.class,args);
    }
}
