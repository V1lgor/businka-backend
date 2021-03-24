package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vilgor.businkabackend.entity.*;
import ru.vilgor.businkabackend.exceptions.OrderException;
import ru.vilgor.businkabackend.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private ProductService productService;
    private CityService cityService;
    private OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(ProductService productService, CityService cityService, OrderRepository orderRepository) {
        this.productService = productService;
        this.cityService = cityService;
        this.orderRepository = orderRepository;
    }

    private List<Product> getProductsFromOrder(Order order) {
        List<Integer> productIdList = new ArrayList<>();

        for (OrderProduct orderProduct : order.getProductList()) {
            productIdList.add(orderProduct.getId().getProductId());
        }

        List<Product> productList = productService.getProductListByIdList(productIdList);

        if (productList.size() != productIdList.size()) throw new OrderException("There is product in order that doesn't exist.");

        return productList;
    }

    private double getOrderTotalCost(Order order) {
        List<Product> productList = getProductsFromOrder(order);

        double totalOrderCost = 0;

        for (int i = 0; i < productList.size(); i++) {
            double price = productList.get(i).getPriceWithDiscount();
            int count = order.getProductList().get(i).getCount();
            totalOrderCost += price * count;
        }
        System.out.println(totalOrderCost);
        return totalOrderCost;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        order.setTotalCost(getOrderTotalCost(order));

        orderRepository.save(order);
    }
}
