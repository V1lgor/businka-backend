package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vilgor.businkabackend.entity.DeliveryRegion;
import ru.vilgor.businkabackend.repository.RegionRepository;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<DeliveryRegion> getAllRegions() {
        return regionRepository.findAll();
    }
}
