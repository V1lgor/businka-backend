package ru.vilgor.businkabackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prod_id")
    private Integer id;

    @Column(name = "prod_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "prod_category_id")
    private Category category;

    @Column(name = "prod_code")
    private String code;

    @Column(name = "prod_price")
    private Double price;

    @Column(name = "prod_discount")
    private Integer discount;

    @Column(name = "prod_image_url")
    private String imageURL;

    @Column(name = "prod_hidden")
    private Boolean hidden;


    public Product() {}

    public Product(String name, Category category, String code, double price, int discount, String imageURL, boolean hidden) {
        this.name = name;
        this.category = category;
        this.code = code;
        this.price = price;
        this.discount = discount;
        this.imageURL = imageURL;
        this.hidden = hidden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public double getPriceWithDiscount() {
        return price * (100 - discount) / 100;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' + "}";
    }
}
