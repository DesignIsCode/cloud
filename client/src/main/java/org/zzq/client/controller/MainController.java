package org.zzq.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MainController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String greeting(@RequestParam String name){
        return "Hi, " + name + ",i am from port:" + port;
    }
}
