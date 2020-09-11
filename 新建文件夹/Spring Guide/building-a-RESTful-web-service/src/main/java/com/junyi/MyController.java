package com.junyi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @time: 2020/8/12 18:31
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
public class MyController {
    private static final String template = "The book of %s";
    private static final AtomicLong counter = new AtomicLong();

    @GetMapping("/book")
    public Book index(@RequestParam(name = "name", defaultValue = "world") String name) {
        return new Book(counter.incrementAndGet(), String.format(template, name));
    }
}
