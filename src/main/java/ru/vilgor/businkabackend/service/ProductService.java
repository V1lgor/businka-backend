package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();

    List<Product> getProductList(int pageNumber, int pageSize);

    List<Product> getProductListByCategoryId(int categoryId);

    List<Product> getProductListByCategoryId(int categoryId, int pageNumber, int pageSize);

    List<Product> getProductListByIdList(List<Integer> idList);

    int save(Product product);

    void update(int id, Product product);

    void delete(int id);
}
