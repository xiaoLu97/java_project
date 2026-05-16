package com.mvc.controller;

import com.mvc.entity.News;
import com.mvc.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @PostMapping("/add")
    public String add(News news){
        news.setCreatetime(new Date());
        this.newsMapper.add(news);
        return "redirect:/list";
    }

    @GetMapping("/get")
    public String get(Integer id,Model model){
        News news = this.newsMapper.getById(id);
        model.addAttribute("news", news);
        return "edit";
    }

    @PostMapping("/update")
    public String update(News news){
        this.newsMapper.update(news);
        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        this.newsMapper.delete(id);
        return "redirect:/list";
    }
}
