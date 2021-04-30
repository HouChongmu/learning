package com.yolyn.starterlearning;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/5/16 21:35
 * @project starter-learning
 */
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnProperty(prefix = "hello",name = "enable",havingValue = "true")
@ComponentScan(basePackageClasses = HelloAutoConfiguration.class)
public class HelloAutoConfiguration {

}
