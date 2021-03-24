package ru.vilgor.businkabackend.dto;

import ru.vilgor.businkabackend.entity.Product;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class SaveOrderDto {
    private String customerName;
    private String customerEmail;

    private String deliveryAddress;
    private Integer deliveryCityId;

    private List<ProductInOrderDto> productsInOrder;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getDeliveryCityId() {
        return deliveryCityId;
    }

    public void setDeliveryCityId(Integer deliveryCityId) {
        this.deliveryCityId = deliveryCityId;
    }

    public List<ProductInOrderDto> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(List<ProductInOrderDto> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }
}
