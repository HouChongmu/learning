package com.yolyn.starterlearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/5/16 21:30
 * @project starter-learning
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;
    @GetMapping("/hello")
    public String hello(){
    return helloService.sayHello();
    }
}
