package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.Product;
import ru.vilgor.businkabackend.exceptions.CategoryException;
import ru.vilgor.businkabackend.repository.CategoryRepository;
import ru.vilgor.businkabackend.repository.ProductRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }



    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoryListHierarchy() {
        return categoryRepository.findRootCategoryList();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public boolean categoryExists(Category category) {
        return categoryRepository.findById(category.getId()) != null;
    }

    private boolean categoryNameExists(String categoryName) {
        return categoryRepository.findByName(categoryName) != null;
    }

    @Override
    @Transactional
    public int save(Category category) {
        System.out.println(category.getParentCategory().getId());

        if (categoryNameExists(category.getName())) {
            throw new CategoryException("Category with given name already exists");
        }

        if (category.getParentCategory() != null) {
            if (getCategoryById(category.getParentCategory().getId()) == null) {
                throw new CategoryException("No category with given ID found to set parent category");
            }
        }

        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void update(int id, Category category) {
        if (categoryRepository.findById(id) == null) {
            throw new CategoryException("Category with given ID not found");
        }

        if (categoryNameExists(category.getName())) {
            throw new CategoryException("Category with given name already exists");
        }

        if (category.getParentCategory() != null) {
            if (getCategoryById(category.getParentCategory().getId()) == null) {
                throw new CategoryException("No category with given ID found to set parent category");
            }
        }

        categoryRepository.update(id, category);
    }

    @Override
    @Transactional
    public void delete(int id) {

        if (getCategoryById(id) == null) {
            throw new CategoryException("Category doesn't exist");
        }

        List<Product> productList = productRepository.findByCategoryId(id);

        if (productList.size() == 0) {
            categoryRepository.delete(id);
        }
        else {
            throw new CategoryException("Category can't be deleted because it contains product(s).");
        }
    }
}
