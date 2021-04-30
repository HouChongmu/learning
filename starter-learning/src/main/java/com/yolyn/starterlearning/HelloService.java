package com.yolyn.starterlearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/5/16 21:22
 * @project starter-learning
 */
@Component
public class HelloService {
    @Autowired
    private HelloProperties helloProperties;
    public String sayHello(){
        System.out.println("hi:"+helloProperties.getName()+":"+helloProperties.getSaySth());
        return "hi:"+helloProperties.getName()+":"+helloProperties.getSaySth();
    }
}
