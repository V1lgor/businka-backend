package ru.vilgor.businkabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vilgor.businkabackend.entity.News;
import ru.vilgor.businkabackend.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("")
    public List<News> getNewsPreviewList(
            @RequestParam(value = "count", required = false) Integer newsCount,
            @RequestParam(value = "offset", required = false) Integer offset) {
        if (newsCount != null) {
            if (offset != null) {
                return newsService.getNewsPreviewListByCountAndOffset(newsCount, offset);
            }
            else {
                return newsService.getNewsPreviewListByCountAndOffset(newsCount, 0);
            }
        }
        else {
            return newsService.getNewsPreviewList();
        }
    }
}
