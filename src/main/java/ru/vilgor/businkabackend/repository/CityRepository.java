package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.DeliveryCity;

public interface CityRepository {
    public DeliveryCity find(int id);
}
