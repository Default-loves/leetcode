package com.junyi.access.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @time: 2020/8/13 9:07
 * @version: 1.0
 * @author: junyi Xu
 * @description: Mysql + JPA
 */
@SpringBootApplication
@EnableJpaRepositories
public class AccessingDataMySQLDemo {
    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMySQLDemo.class, args);
    }
}
