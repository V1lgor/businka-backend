package ru.vilgor.businkabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    private ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getProductList() {
        return productService.getAllProducts();
    }
}
