package ru.vilgor.businkabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.News;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.exceptions.CategoryException;
import ru.vilgor.businkabackend.exceptions.ProductException;
import ru.vilgor.businkabackend.mapper.OrderMapper;
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

    @PostMapping("")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        System.out.println(product);
        try {
            int productId = productService.save(product);

            return new ResponseEntity<>(productId, HttpStatus.CREATED);
        }
        catch (ProductException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            productService.update(id, product);
        }
        catch (ProductException | CategoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        productService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
