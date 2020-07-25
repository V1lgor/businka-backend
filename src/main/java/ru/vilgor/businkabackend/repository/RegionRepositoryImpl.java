package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.DeliveryRegion;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RegionRepositoryImpl implements RegionRepository {
    private EntityManager entityManager;

    @Autowired
    public RegionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DeliveryRegion> findAll() {
        return entityManager.createQuery("from DeliveryRegion", DeliveryRegion.class)
                .getResultList();
    }
}
