package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();

    List<Category> findRootCategoryList();
}
