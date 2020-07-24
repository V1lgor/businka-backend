package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    List<Product> findByCountAndOffset(int count, int offset);

    Product find(Integer id);

    List<Product> findByCategoryId(int categoryId);

    List<Product> findByCategoryId(int categoryId, int count, int offset);

    List<Product> findByCategoryIdList(List<Integer> categoryIdList);

    List<Product> findByCategoryIdList(List<Integer> categoryIdList, int count, int offset);

    List<Product> findByIdList(List<Integer> idList);

    void detachEntity(Object entity);
}
