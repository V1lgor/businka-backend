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
            @RequestParam(value = "page", required = false) Integer pageNumber,
            @RequestParam(value = "size", required = false) Integer pageSize) {

        List<News> newsList;
        HttpHeaders headers = new HttpHeaders();
        if (pageSize == null) {
            newsList = newsService.getNewsPreviewList();
        }
        else {
            if (pageNumber == null) {
                newsList = newsService.getNewsPreviewList(0, pageSize);
            }
            else {
                newsList = newsService.getNewsPreviewList(pageNumber, pageSize);
            }
        }
        headers.add("X-Total-Count", Integer.toString(newsList.size()));
        return new ResponseEntity<>(newsList, headers, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public News getNewsItemById(@PathVariable int id) {
        return newsService.getNewsItemById(id);
    }
}
