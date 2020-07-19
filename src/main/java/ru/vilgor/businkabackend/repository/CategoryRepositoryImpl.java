package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.Category;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private EntityManager entityManager;

    @Autowired
    public CategoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }

    @Override
    public List<Category> findRootCategoryList() {
        return entityManager.createQuery("from Category where parentCategory = null", Category.class)
                .getResultList();
    }

    @Override
    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }
}
