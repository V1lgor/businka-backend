package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.News;

import java.util.List;

public interface NewsRepository {
    List<News> findAll();
    List<News> findByCountAndOffset(int count, int offset);

    News findById(int id);
}
