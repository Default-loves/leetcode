package com.junyi.handle.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @time: 2020/8/13 17:46
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Controller
@RequestMapping("/book")
public class MyController {

    @GetMapping
    public String getForm(Model model) {
        model.addAttribute("book", new Book());
        return "book";
    }

    @PostMapping
    public String handleForm(Model model, @ModelAttribute Book book) {
//        model.addAttribute("book", book);
        return "result";
    }
}
