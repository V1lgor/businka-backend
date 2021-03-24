package ru.vilgor.businkabackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import ru.vilgor.businkabackend.dto.ProductInOrderDto;
import ru.vilgor.businkabackend.dto.SaveOrderDto;
import ru.vilgor.businkabackend.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class OrderMapper {

    public Order saveOrderDtoToOrder(SaveOrderDto saveOrderDto) {
        Order order = new Order();

        order.setUserName(saveOrderDto.getCustomerName());
        order.setUserEmail(saveOrderDto.getCustomerEmail());

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (ProductInOrderDto productInOrderDto : saveOrderDto.getProductsInOrder()) {
            OrderProduct orderProduct = new OrderProduct();

            orderProduct.setId(new OrderProductId(null, productInOrderDto.getProductId()));
            Product product = new Product();
            product.setId(productInOrderDto.getProductId());
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setCount(productInOrderDto.getCount());

            orderProducts.add(orderProduct);
        }

        order.setProductList(orderProducts);

        OrderDeliveryInfo orderDeliveryInfo = new OrderDeliveryInfo();

        orderDeliveryInfo.setAddress(saveOrderDto.getDeliveryAddress());

        DeliveryCity deliveryCity = new DeliveryCity();

        deliveryCity.setId(saveOrderDto.getDeliveryCityId());

        orderDeliveryInfo.setCity(deliveryCity);

        order.setDeliveryInfo(orderDeliveryInfo);

        return order;
    }
}
