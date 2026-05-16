package com.mvc.controller;


import com.mvc.entity.News;
import com.mvc.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 新闻表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2026-05-16
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/list")
    public List<News> list() {
        return this.newsService.list();
    }

    @GetMapping("/list/{id}")
    public News getById(@PathVariable("id") Integer id) {
        return this.newsService.getById(id);
    }
}

