package com.mvc.controller;

import com.mvc.entity.News;
import com.mvc.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private NewsMapper newsMapper;

    @RequestMapping("/list")
    public String list(Model model){
        List<News> list = this.newsMapper.list();
        model.addAttribute("list", list);
        return "index";
    }
}
