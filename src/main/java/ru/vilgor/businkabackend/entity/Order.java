package ru.vilgor.businkabackend.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="\"Order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "order_user_name")
    private String userName;

    @Column(name = "order_user_email")
    private String userEmail;

    @Column(name = "order_date")
    private Date date = new Date();

    @Column(name = "order_total_cost")
    private double totalCost;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST})
    private List<OrderProduct> productList;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "order_delivery_id")
    private OrderDeliveryInfo deliveryInfo;

    public Order() {

    }

    public Order(String userName, String userEmail, double totalCost) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<OrderProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProduct> productList) {
        this.productList = productList;
    }

    public OrderDeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(OrderDeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }
}
