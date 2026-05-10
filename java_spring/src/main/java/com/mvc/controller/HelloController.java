package com.mvc.controller;

import com.mvc.entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("test...");
        return "test";
    }

    @RequestMapping("/index")
    public String index(Model model, User user) {
        // http://localhost:8080/index?id=001&name=tom
        System.out.println("index: " + user); // 自动封装参数
        model.addAttribute("user", user);
        return "index";
    }
}
