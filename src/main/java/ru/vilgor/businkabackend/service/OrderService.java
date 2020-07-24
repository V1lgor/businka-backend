package ru.vilgor.businkabackend.service;

import ru.vilgor.businkabackend.dto.OrderDto;

public interface OrderService {
    void saveOrder(OrderDto orderDto);
}
