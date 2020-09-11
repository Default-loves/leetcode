package com.junyi.cors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @time: 2020/8/13 13:56
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private static final AtomicLong counter = new AtomicLong();
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:9090")
    public Book index(@RequestParam(name = "name", required = false, defaultValue = "world") String name) {
        log.info("=====");
        return new Book(counter.incrementAndGet(), name);
    }

    @GetMapping("/book-with-java-config")
    public Book indexWithJavaConfig(@RequestParam(name = "name", required = false, defaultValue = "world") String name) {
        log.info("=====");
        return new Book(counter.incrementAndGet(), name);
    }
}
