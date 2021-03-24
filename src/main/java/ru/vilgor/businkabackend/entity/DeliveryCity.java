package ru.vilgor.businkabackend.entity;

import com.fasterxml.jackson.annotation.JsonView;
import ru.vilgor.businkabackend.jsonview.View;

import javax.persistence.*;

@Entity
@Table(name = "DeliveryCity")
public class DeliveryCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    @JsonView(View.Public.class)
    private int id;

    @Column(name = "city_name")
    @JsonView(View.Public.class)
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_region_id")
    @JsonView(View.Nested.class)
    private DeliveryRegion region;

    @Column(name = "city_delivery_price")
    @JsonView(View.Public.class)
    private double deliveryPrice;

    public DeliveryCity() {

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

    public DeliveryRegion getRegion() {
        return region;
    }

    public void setRegion(DeliveryRegion region) {
        this.region = region;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}
