package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.News;
import ru.vilgor.businkabackend.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public List<Product> findByCountAndOffset(int count, int offset) {
        return entityManager.createQuery("from Product order by id asc", Product.class)
                .setFirstResult(offset)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public Product find(Integer id) {
        return entityManager.find(Product.class, id);
    }

    public void detachEntity(Object entity) {
        entityManager.detach(entity);
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        return entityManager.createQuery("from Product where category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<Product> findByCategoryIdList(List<Integer> categoryIdList) {
        return entityManager.createQuery("from Product where category.id in (:categoryIdList)", Product.class)
                .setParameter("categoryIdList", categoryIdList)
                .getResultList();
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, int count, int offset) {
        return entityManager.createQuery("from Product where category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .setFirstResult(offset)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public List<Product> findByCategoryIdList(List<Integer> categoryIdList, int count, int offset) {
        return entityManager.createQuery("from Product where category.id in (:categoryIdList)", Product.class)
                .setParameter("categoryIdList", categoryIdList)
                .setFirstResult(offset)
                .setMaxResults(count)
                .getResultList();
    }
}
