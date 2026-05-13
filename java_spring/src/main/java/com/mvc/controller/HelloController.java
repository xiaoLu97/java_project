package com.mvc.controller;

import com.mvc.entity.News;
import com.mvc.entity.User;
import com.mvc.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private NewsMapper newsMapper;

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

    @RequestMapping("/list")
    public String list(Model model) {
        List<News> list = this.newsMapper.list();
        for (News news : list) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(news.getCreatetime());
            news.setTime(format);
        }
        model.addAttribute("list", list);
        return "news";
    }

    @RequestMapping("/add")
    public String add(News news){
        news.setCreatetime(new Date());
        this.newsMapper.add(news);
        return "redirect:/list";
    }

    @RequestMapping("/get")
    public String get(Integer id,Model model){
        News news = this.newsMapper.getById(id);
        model.addAttribute("news", news);
        return "edit";
    }

    @RequestMapping("/update")
    public String update(News news){
        this.newsMapper.update(news);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        this.newsMapper.deleteById(id);
        return "redirect:/list";
    }
}
