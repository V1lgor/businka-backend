package ru.vilgor.businkabackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vilgor.businkabackend.dto.SaveOrderDto;
import ru.vilgor.businkabackend.entity.Order;
import ru.vilgor.businkabackend.entity.OrderProduct;
import ru.vilgor.businkabackend.exceptions.OrderException;
import ru.vilgor.businkabackend.mapper.OrderMapper;
import ru.vilgor.businkabackend.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("")
    public ResponseEntity saveOrder(@RequestBody SaveOrderDto orderDto) {

        Order order = orderMapper.saveOrderDtoToOrder(orderDto);
        System.out.println(order);
        try {
            orderService.saveOrder(order);
        }
        catch (OrderException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
