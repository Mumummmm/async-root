package com.spike.spring;

import com.spike.spring.async.TaskThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author spike.lin
 */
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
