package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vilgor.businkabackend.entity.DeliveryCity;
import ru.vilgor.businkabackend.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public DeliveryCity getCityById(int id) {
        return cityRepository.find(id);
    }
}
