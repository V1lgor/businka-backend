package ru.vilgor.businkabackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prod_id")
    private int id;

    @Column(name = "prod_name")
    private String name;

    @Column(name = "prod_code")
    private String code;

    @Column(name = "prod_price")
    private double price;

    @Column(name = "prod_hidden")
    private boolean hidden;

    public Product() {}

    public Product(String name, String code, double price, boolean hidden) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.hidden = hidden;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
