package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private Environment environment;

    private CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, Environment environment, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.environment = environment;
        this.categoryService = categoryService;
    }


    @Override
    @Transactional
    public List<Product> getProductList() {
        List<Product> productList = productRepository.findAll();

        makeFullProductsImageURL(productList);

        return productList;
    }

    private void makeFullProductsImageURL(List<Product> productList) {
        for (Product product : productList) {
            productRepository.detachEntity(product);
            if (product.getImageURL() != null) {
                product.setImageURL("http://localhost:" + environment.getProperty("server.port") + product.getImageURL());
            }
        }
    }

    public List<Product> getProductList(int pageNumber, int pageSize) {
        List<Product> productList = productRepository.findByCountAndOffset(pageSize, pageNumber * pageSize);

        makeFullProductsImageURL(productList);

        return productList;
    }

    @Override
    @Transactional
    public List<Product> getProductListByCategoryId(int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);

        if (category.getChildCategoryList().size() == 0) {
            List<Product> productList = productRepository.findByCategoryId(categoryId);
            makeFullProductsImageURL(productList);
            return productList;
        }

        List<Integer> categoryIdList = new ArrayList<>();

        for (Category childCategory : category.getChildCategoryList()) {
            categoryIdList.add(childCategory.getId());
        }

        List<Product> productList = productRepository.findByCategoryIdList(categoryIdList);

        makeFullProductsImageURL(productList);

        return productList;
    }

    @Override
    public List<Product> getProductListByCategoryId(int categoryId, int pageNumber, int pageSize) {
        Category category = categoryService.getCategoryById(categoryId);

        if (category.getChildCategoryList().size() == 0) {
            List<Product> productList =
                    productRepository.findByCategoryId(categoryId, pageSize, pageNumber * pageSize);

            makeFullProductsImageURL(productList);
            return productList;
        }

        List<Integer> categoryIdList = new ArrayList<>();

        for (Category childCategory : category.getChildCategoryList()) {
            categoryIdList.add(childCategory.getId());
        }

        List<Product> productList = productRepository.findByCategoryIdList(categoryIdList, pageSize, pageNumber * pageSize);

        makeFullProductsImageURL(productList);

        return productList;
    }
}
