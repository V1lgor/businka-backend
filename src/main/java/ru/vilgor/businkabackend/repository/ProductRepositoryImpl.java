package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.Product;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }
}
