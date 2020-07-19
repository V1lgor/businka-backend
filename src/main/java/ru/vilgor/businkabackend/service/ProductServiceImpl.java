package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.exceptions.ImageFileNotFoundException;
import ru.vilgor.businkabackend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private Environment environment;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
        this.productRepository = productRepository;
        this.environment = environment;
    }



    @Override
    @Transactional
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        for (Product product : productList) {
            productRepository.detachEntity(product);
            product.setImageURL("http://localhost:" + environment.getProperty("server.port") + product.getImageURL());
        }

        return productList;
    }
}
