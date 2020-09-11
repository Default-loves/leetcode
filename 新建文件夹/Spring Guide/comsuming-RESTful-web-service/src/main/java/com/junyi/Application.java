package com.junyi;

import com.junyi.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @time: 2020/8/12 18:14
 * @version: 1.0
 * @author: junyi Xu
 * @description: 消费 RESTful web service，服务来源：https://gturnquist-quoters.cfapps.io/api/random
 */
@SpringBootApplication
public class Application {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            Message message = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Message.class);
            log.info(message.toString());
        };
    }
}
