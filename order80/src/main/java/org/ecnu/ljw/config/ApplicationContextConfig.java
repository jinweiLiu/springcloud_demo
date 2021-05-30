package org.ecnu.ljw.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class ApplicationContextConfig{
    @Bean
    @LoadBalanced //轮询负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
