package com.yolyn.starterlearning;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2020/5/16 21:17
 * @project starter-learning
 */
@Data
@ConfigurationProperties(prefix = "hello")
@Component
public class HelloProperties {
    private String name;
    private String saySth;
}
