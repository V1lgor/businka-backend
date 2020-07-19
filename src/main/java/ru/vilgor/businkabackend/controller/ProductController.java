package ru.vilgor.businkabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vilgor.businkabackend.entity.News;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getProductList(
            @RequestParam(value = "page", required = false) Integer pageNumber,
            @RequestParam(value = "size", required = false) Integer pageSize) {

        List<Product> productList;
        HttpHeaders headers = new HttpHeaders();
        if (pageSize == null) {
            productList = productService.getProductList();
        }
        else {
            if (pageNumber == null) {
                productList = productService.getProductList(0, pageSize);
            }
            else {
                productList = productService.getProductList(pageNumber, pageSize);
            }
        }
        headers.add("X-Total-Count", Integer.toString(productList.size()));
        return new ResponseEntity<>(productList, headers, HttpStatus.OK);
    }



}
