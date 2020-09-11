package com.junyi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @time: 2020/8/13 11:02
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Controller
public class MyController {
    
    @GetMapping("/book")
    public String index(@RequestParam(required = false, name = "name", defaultValue = "world") String name, Model model) {
        model.addAttribute("name", name);
        return "book";
        
        
    }
}
