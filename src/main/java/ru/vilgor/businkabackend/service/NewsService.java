package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getNewsPreviewList();
    List<News> getNewsPreviewList(int pageNumber, int pageSize);

    News getNewsItemById(int id);
}
