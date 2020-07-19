package ru.vilgor.businkabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.service.CategoryService;
import ru.vilgor.businkabackend.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("")
    public List<Category> getCategoryList() {
        return categoryService.getCategoryListHierarchy();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("{id}/products")
    public ResponseEntity<List<Product>> getProductList(
            @PathVariable(value = "id") Integer categoryId,
            @RequestParam(value = "page", required = false) Integer pageNumber,
            @RequestParam(value = "size", required = false) Integer pageSize) {

        List<Product> productList;
        HttpHeaders headers = new HttpHeaders();
        if (pageSize == null) {
            productList = productService.getProductListByCategoryId(categoryId);
        }
        else {
            if (pageNumber == null) {
                productList = productService.getProductListByCategoryId(categoryId, 0, pageSize);
            }
            else {
                productList = productService.getProductListByCategoryId(categoryId, pageNumber, pageSize);
            }
        }
        headers.add("X-Total-Count", Integer.toString(productList.size()));
        return new ResponseEntity<>(productList, headers, HttpStatus.OK);
    }
}
