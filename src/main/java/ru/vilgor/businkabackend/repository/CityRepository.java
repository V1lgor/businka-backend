package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.DeliveryCity;

import java.util.List;

public interface CityRepository {
    public DeliveryCity find(int id);
    List<DeliveryCity> findByRegionId(int regionId);
}
