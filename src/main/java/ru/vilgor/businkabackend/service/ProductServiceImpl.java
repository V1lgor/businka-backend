package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.exceptions.ImageFileNotFoundException;
import ru.vilgor.businkabackend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ImageService imageService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
    }


    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public byte[] getProductImage(int id) throws ImageFileNotFoundException {
        Product product = productRepository.find(id);
        return imageService.getImageByPath("static/images/product/" + product.getImageFilename());
    }
}
