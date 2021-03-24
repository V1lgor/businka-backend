package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategoryList();

    public List<Category> getCategoryListHierarchy();

    public boolean categoryExists(Category category);

    Category getCategoryById(int id);

    int save(Category category);

    void update(int id, Category category);

    void delete(int id);
}
