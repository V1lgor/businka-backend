package ru.vilgor.businkabackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vilgor.businkabackend.dto.OrderDto;
import ru.vilgor.businkabackend.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity saveOrder(@RequestBody OrderDto orderDto) {
        System.out.println(orderDto.toString());
        orderService.saveOrder(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
