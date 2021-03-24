package ru.vilgor.businkabackend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.exceptions.CategoryException;
import ru.vilgor.businkabackend.jsonview.CategoryView;
import ru.vilgor.businkabackend.service.CategoryService;
import ru.vilgor.businkabackend.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("")
    @JsonView(CategoryView.WithChildren.class)
    public List<Category> getCategoryHierarchy() {
         return categoryService.getCategoryListHierarchy();

    }

    @GetMapping("/flat")
    @JsonView(CategoryView.WithParent.class)
    public List<Category> getCategoryList() {
        return categoryService.getCategoryList();
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

    @PostMapping("")
    public ResponseEntity saveCategory(@RequestBody Category category) {
        try {
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
        }
        catch (CategoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable int id, @RequestBody Category category) {
        try {
            categoryService.update(id, category);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (CategoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id) {
        try {
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (CategoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
