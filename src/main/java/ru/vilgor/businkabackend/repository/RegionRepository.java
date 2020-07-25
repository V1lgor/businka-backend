package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.DeliveryRegion;

import java.util.List;

public interface RegionRepository {
    public List<DeliveryRegion> findAll();
}
