package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.Order;
import ru.vilgor.businkabackend.entity.OrderProduct;
import ru.vilgor.businkabackend.entity.Product;

import javax.persistence.EntityManager;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private EntityManager entityManager;

    @Autowired
    public OrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Order order) {

        // em.getReference() возвращает Proxy объекта, не проверяя, есть ли он при этом в БД


        for (OrderProduct product : order.getProductList()) {
            product.setProduct(entityManager.getReference(Product.class, product.getId().getProductId()));
        }

        entityManager.persist(order);
    }
}
