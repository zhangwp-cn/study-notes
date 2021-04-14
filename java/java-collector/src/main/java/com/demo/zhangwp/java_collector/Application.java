package com.demo.zhangwp.java_collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwp
 * @date 2021/4/10 - 11:12
 */
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping(method = RequestMethod.GET, value = "/health")
    public @ResponseBody
    String aeHealth() {
        return "OK";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
