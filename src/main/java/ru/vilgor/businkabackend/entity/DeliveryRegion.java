package ru.vilgor.businkabackend.entity;

import com.fasterxml.jackson.annotation.JsonView;
import ru.vilgor.businkabackend.jsonview.View;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DeliveryRegion")
public class DeliveryRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    @JsonView(View.Public.class)
    private int id;

    @JsonView(View.Public.class)
    @Column(name = "region_name")
    private String name;

    @JsonView(View.Public.class)
    @Column(name = "region_code")
    private String code;

    @JsonView(View.Nested.class)
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
