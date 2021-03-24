package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.entity.DeliveryCity;

import java.util.List;

public interface CityService {
    DeliveryCity getCityById(int id);
    List<DeliveryCity> getCitiesByRegionId(int regionId);
}
