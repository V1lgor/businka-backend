package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategoryList();

    public List<Category> getCategoryListHierarchy();

    Category getCategoryById(int id);
}
