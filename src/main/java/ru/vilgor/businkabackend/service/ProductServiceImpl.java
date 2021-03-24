package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.exceptions.CategoryException;
import ru.vilgor.businkabackend.exceptions.ProductException;
import ru.vilgor.businkabackend.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final Environment environment;

    private final CategoryService categoryService;

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

    @Override
    public List<Product> getProductListByIdList(List<Integer> idList) {
        return productRepository.findByIdList(idList);
    }

    private boolean productNameExists(String productName) {
        Product product = productRepository.findByName(productName);

        return product != null;
    }

    private boolean productCodeExists(String productCode) {
        Product product = productRepository.findByCode(productCode);

        return product != null;
    }


    @Override
    @Transactional
    public int save(Product product) {
        if (productNameExists(product.getName())) {
            throw new ProductException("Product with given name already exists");
        }
        if (productCodeExists(product.getCode())) {
            throw new ProductException("Product with given code already exists");
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void update(int id, Product product) {
        if (productRepository.find(id) == null) {
            throw new ProductException("There is no product with given ID");
        }
        if (product.getName() != null) {
            if (productNameExists(product.getName())) {
                throw new ProductException("Product with given name already exists");
            }
        }
        if (product.getCode() != null) {
            if (productCodeExists(product.getCode())) {
                throw new ProductException("Product with given code already exists");
            }
        }

        if (!categoryService.categoryExists(product.getCategory())) {
            throw new CategoryException("No category with given ID found");
        }

        productRepository.update(id, product);
    }

    @Override
    @Transactional
    public void delete(int id) {
        productRepository.delete(id);
    }
}
