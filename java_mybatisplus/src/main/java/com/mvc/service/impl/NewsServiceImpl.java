package com.mvc.service.impl;

import com.mvc.entity.News;
import com.mvc.mapper.NewsMapper;
import com.mvc.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2026-05-16
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

}
