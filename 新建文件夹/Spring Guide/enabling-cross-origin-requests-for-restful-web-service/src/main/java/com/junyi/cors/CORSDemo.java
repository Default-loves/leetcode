package com.junyi.cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @time: 2020/8/13 9:53
 * @version: 1.0
 * @author: junyi Xu
 * @description: 配置CORS，有两种方法
 * 1. 直接在Controller类或类方法上面添加@CrossOrigin
 * 2. 配置WebMvcConfigurer这个Bean，在方法addCorsMappings中添加
 */
@SpringBootApplication
public class CORSDemo {
    public static void main(String[] args) {
        SpringApplication.run(CORSDemo.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/book-with-java-config")
                        .allowedOrigins("http://localhost:9090")
                        .allowedMethods("GET", "POST")
                        .maxAge(3600);
            }
        };
    }
}
