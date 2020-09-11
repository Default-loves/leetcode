package com.junyi.access.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @time: 2020/8/13 9:01
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/add")
    public Book add(@RequestParam String name, @RequestParam String description) {
        return bookRepository.save(new Book(name, description));
    }

    @GetMapping("/all")
    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }
}
