package ru.vilgor.businkabackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "DeliveryCity")
public class DeliveryCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;

    @Column(name = "city_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_region_id")
    private DeliveryRegion region;

    @Column(name = "city_delivery_price")
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
