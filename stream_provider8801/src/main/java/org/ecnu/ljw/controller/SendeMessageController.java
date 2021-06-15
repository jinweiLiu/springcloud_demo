package org.ecnu.ljw.controller;

import org.ecnu.ljw.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendeMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/message")
    public String sendMessage(){
        return messageProvider.send();
    }
}
