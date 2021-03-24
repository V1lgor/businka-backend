package ru.vilgor.businkabackend.entity;

import javax.persistence.*;

@Entity(name = "ProductsInOrder")
@Table(name = "ProductsInOrder")
public class OrderProduct {
    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id")
    @MapsId("orderId")
    private Order order;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private Product product;

    @Column(name = "product_count")
    private int count;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_discount")
    private int discount;

    public OrderProduct(Order order, Product product, int count) {
        this.order = order;
        this.product = product;
        this.count = count;
        this.discount = product.getDiscount();
        this.price = product.getPrice();
        this.id = new OrderProductId(this.order.getId(), this.product.getId());
    }

    public OrderProduct() {

    }

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", product=" + product +
                ", count=" + count +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
