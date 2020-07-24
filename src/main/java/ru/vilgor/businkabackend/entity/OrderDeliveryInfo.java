package ru.vilgor.businkabackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "OrderDeliveryInfo")
public class OrderDeliveryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_delivery_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_delivery_city_id")
    private DeliveryCity city;

    @Column(name = "order_delivery_address")
    private String address;

    @Column(name = "order_delivery_price")
    private double price;

    public OrderDeliveryInfo() {

    }

    public OrderDeliveryInfo(DeliveryCity city, String address, double price) {
        this.city = city;
        this.address = address;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryCity getCity() {
        return city;
    }

    public void setCity(DeliveryCity city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

