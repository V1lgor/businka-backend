package ru.vilgor.businkabackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vilgor.businkabackend.entity.News;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class NewsRepositoryImpl implements NewsRepository{

    private EntityManager entityManager;

    @Autowired
    public NewsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<News> findAll() {
        return entityManager.createQuery("from News order by date desc", News.class)
                .getResultList();
    }

    @Override
    public List<News> findByCountAndOffset(int count, int offset) {
        return entityManager.createQuery("from News order by date desc", News.class)
                .setFirstResult(offset)
                .setMaxResults(count)
                .getResultList();
    }
}
