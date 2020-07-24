package ru.vilgor.businkabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vilgor.businkabackend.dto.OrderDto;
import ru.vilgor.businkabackend.dto.OrderProductDto;
import ru.vilgor.businkabackend.entity.*;
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


    @Override
    @Transactional
    public void saveOrder(OrderDto orderDto) {
        List<Integer> productIdList = new ArrayList<>();

        for (OrderProductDto orderProductDto : orderDto.getProductList()) {
            productIdList.add(orderProductDto.getId());
        }

        List<Product> productList = productService.getProductListByIdList(productIdList);

        double totalOrderCost = 0;

        for (int i = 0; i < productList.size(); i++) {
            double price = productList.get(i).getPriceWithDiscount();
            int count = orderDto.getProductList().get(i).getCount();
            System.out.println(productList.get(i).getName() + ": " + price * count);
            totalOrderCost += price * count;
        }

        Order order = new Order(orderDto.getUserName(), orderDto.getUserEmail(), totalOrderCost);

        List<OrderProduct> orderProductList = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            int count = orderDto.getProductList().get(i).getCount();
            orderProductList.add(new OrderProduct(order, product, count));
        }

        DeliveryCity deliveryCity = cityService.getCityById(orderDto.getDeliveryCityId());

        OrderDeliveryInfo orderDeliveryInfo =
                new OrderDeliveryInfo(deliveryCity, orderDto.getDeliveryCityAddress(), deliveryCity.getDeliveryPrice());

        order.setProductList(orderProductList);
        order.setDeliveryInfo(orderDeliveryInfo);

        orderRepository.save(order);
    }
}
