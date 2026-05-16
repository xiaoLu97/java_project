package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {



    @GetMapping("/welcome")
    public String index(){
        return "welcome";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
