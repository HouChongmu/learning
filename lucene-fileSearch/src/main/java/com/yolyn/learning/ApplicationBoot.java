package com.yolyn.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2021/5/29 9:52 PM
 * @project lucene-fileSearch
 * @description
 */
@Configuration
@SpringBootApplication
public class ApplicationBoot {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBoot.class, args);
    }
}
