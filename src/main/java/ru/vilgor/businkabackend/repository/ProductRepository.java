package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product find(Integer id);

    void detachEntity(Object entity);
}
