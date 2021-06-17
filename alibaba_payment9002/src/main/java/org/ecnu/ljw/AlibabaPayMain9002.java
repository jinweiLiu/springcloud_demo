package org.ecnu.ljw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaPayMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPayMain9002.class,args);
    }
}
