package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.exceptions.ImageFileNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    byte[] getProductImage(int id) throws ImageFileNotFoundException;
}
