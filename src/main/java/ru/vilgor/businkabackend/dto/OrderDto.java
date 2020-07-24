package ru.vilgor.businkabackend.dto;

import java.util.List;

public class OrderDto {
    private String userName;
    private String userEmail;
    private int deliveryCityId;
    private String deliveryCityAddress;
    private List<OrderProductDto> productList;

    public OrderDto(String userName, String userEmail, int deliveryCityId,
                    String deliveryCityAddress, List<OrderProductDto> productList) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.deliveryCityId = deliveryCityId;
        this.deliveryCityAddress = deliveryCityAddress;
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", deliveryCityId=" + deliveryCityId +
                ", deliveryCityAddress='" + deliveryCityAddress + '\'' +
                ", productList=" + productList +
                '}';
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

    public int getDeliveryCityId() {
        return deliveryCityId;
    }

    public void setDeliveryCityId(int deliveryCityId) {
        this.deliveryCityId = deliveryCityId;
    }

    public String getDeliveryCityAddress() {
        return deliveryCityAddress;
    }

    public void setDeliveryCityAddress(String deliveryCityAddress) {
        this.deliveryCityAddress = deliveryCityAddress;
    }

    public List<OrderProductDto> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProductDto> productList) {
        this.productList = productList;
    }
}
