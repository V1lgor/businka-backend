package ru.vilgor.businkabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.exceptions.ImageFileNotFoundException;
import ru.vilgor.businkabackend.service.ImageService;
import ru.vilgor.businkabackend.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private ImageService imageService;

    public ProductController(ProductService productService, ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @Autowired


    @GetMapping("")
    public List<Product> getProductList() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getImage(@PathVariable("id") Integer productId) {
        ResponseEntity responseEntity = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            byte[] productImage = productService.getProductImage(productId);
            headers.add("Content-Type", "image/jpeg");
            responseEntity = ResponseEntity.ok()
                    .headers(headers)
                    .body(productImage);

        } catch (ImageFileNotFoundException e) {
            System.out.println(e.getMessage());
            headers.add("Content-Type", "application/json");
            responseEntity = new ResponseEntity<>(e.getMessage(), headers, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
