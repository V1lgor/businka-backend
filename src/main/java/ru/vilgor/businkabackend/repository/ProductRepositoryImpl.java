package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.Category;
import ru.vilgor.businkabackend.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private final EntityManager entityManager;

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

    @Override
    public List<Product> findByIdList(List<Integer> idList) {
        return entityManager.createQuery("from Product where id in (:idList)", Product.class)
                .setParameter("idList", idList)
                .getResultList();
    }

    @Override
    public int save(Product product) {
        entityManager.persist(product);

        return product.getId();
    }

    @Override
    public Product findByName(String name) {
        try {
            return entityManager.createQuery("from Product p where p.name = :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Product findByCode(String code) {
        try {
            return entityManager.createQuery("from Product p where p.code = :code", Product.class)
                    .setParameter("code", code)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void update(int id, Product product) {
        Product existingProduct = entityManager.find(Product.class, id);

        if (product.getName() != null) existingProduct.setName(product.getName());
        if (product.getCode() != null) existingProduct.setCode(product.getCode());
        if (product.getDiscount() != null) existingProduct.setDiscount(product.getDiscount());

        Integer newCategoryId = product.getCategory().getId();
        Integer currentCategoryId = existingProduct.getCategory().getId();

        if (newCategoryId != null && !newCategoryId.equals(currentCategoryId)) {
            Category category = entityManager.find(Category.class, product.getCategory().getId());
            existingProduct.setCategory(category);
        }

        if (product.isHidden() != null) existingProduct.setHidden(product.isHidden());
        if (product.getPrice() != null) existingProduct.setPrice(product.getPrice());
    }

    @Override
    public void delete(int id) {
        entityManager.createQuery("delete from Product p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
