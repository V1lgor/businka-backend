package ru.vilgor.businkabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<News>> getNewsPreviewList(
            @RequestParam(value = "count", required = false) Integer newsCount,
            @RequestParam(value = "offset", required = false) Integer offset) {
        List<News> result;
        HttpHeaders headers = new HttpHeaders();
        if (newsCount != null) {
            if (offset != null) {
                result = newsService.getNewsPreviewListByCountAndOffset(newsCount, offset);
            }
            else {
                result = newsService.getNewsPreviewListByCountAndOffset(newsCount, 0);
            }
        }
        else {
            result = newsService.getNewsPreviewList();
        }
        headers.add("X-Total-Count", Integer.toString(result.size()));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public News getNewsItemById(@PathVariable int id) {
        return newsService.getNewsItemById(id);
    }
}
