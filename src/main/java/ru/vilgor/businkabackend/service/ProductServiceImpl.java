package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
