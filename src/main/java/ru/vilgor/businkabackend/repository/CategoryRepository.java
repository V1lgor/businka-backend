package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();

    List<Category> findRootCategoryList();

    Category findById(int id);
    Category findByName(String name);

    int save(Category category);

    void update(int id, Category category);

    void delete(int id);
}
