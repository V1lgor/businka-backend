package ru.vilgor.businkabackend.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vilgor.businkabackend.entity.DeliveryCity;
import ru.vilgor.businkabackend.entity.DeliveryRegion;
import ru.vilgor.businkabackend.jsonview.View;
import ru.vilgor.businkabackend.service.CityService;
import ru.vilgor.businkabackend.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private RegionService regionService;
    private CityService cityService;

    @Autowired
    public RegionController(RegionService regionService, CityService cityService) {
        this.regionService = regionService;
        this.cityService = cityService;
    }

    @GetMapping("")
    @JsonView(View.Public.class)
    public List<DeliveryRegion> getDeliveryRegions() {
        return regionService.getAllRegions();
    }

    @JsonView(View.Public.class)
    @GetMapping("/{regionId}/cities")
    public List<DeliveryCity> getDeliveryCitiesByRegionId(@PathVariable("regionId") int regionId) {
        return cityService.getCitiesByRegionId(regionId);
    }

}
