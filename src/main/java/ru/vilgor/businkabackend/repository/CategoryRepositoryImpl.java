package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final EntityManager entityManager;

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

    @Override
    public int save(Category category) {
        entityManager.persist(category);

        return category.getId();
    }

    @Override
    public Category findByName(String name) {
        try {
            return entityManager.createQuery("from Category c where c.name = :name", Category.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void update(int id, Category category) {

        Category existingCategory = entityManager.find(Category.class, id);

        if (category.getName() != null) existingCategory.setName(category.getName());

        if (category.getParentCategory() != null) {
            if (!category.getParentCategory().getId().equals(existingCategory.getParentCategory().getId())) {

                Category newParentCategory = entityManager.find(Category.class, category.getParentCategory().getId());

                existingCategory.setParentCategory(newParentCategory);
            }
        }
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Category.class, id));
    }
}
