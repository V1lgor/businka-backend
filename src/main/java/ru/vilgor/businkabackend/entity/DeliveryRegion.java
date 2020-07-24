package ru.vilgor.businkabackend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DeliveryRegion")
public class DeliveryRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private int id;

    @Column(name = "region_name")
    private String name;

    @Column(name = "region_code")
    private String code;

    @OneToMany(mappedBy = "region")
    private List<DeliveryCity> cityList;

    public DeliveryRegion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DeliveryCity> getCityList() {
        return cityList;
    }

    public void setCityList(List<DeliveryCity> cityList) {
        this.cityList = cityList;
    }
}
