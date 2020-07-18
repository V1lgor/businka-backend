package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.Product;

import java.util.List;

public interface ProductRepository {
    public List<Product> findAll();

    public Product find(Integer id);
}
