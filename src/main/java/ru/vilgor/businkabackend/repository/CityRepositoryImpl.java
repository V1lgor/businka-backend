package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.DeliveryCity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository {

    private EntityManager entityManager;

    @Autowired
    public CityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public DeliveryCity find(int id) {
        return entityManager.find(DeliveryCity.class, id);
    }

    @Override
    public List<DeliveryCity> findByRegionId(int regionId) {
        return entityManager.createQuery("from DeliveryCity where region.id = :regionId", DeliveryCity.class)
                .setParameter("regionId", regionId)
                .getResultList();
    }
}
