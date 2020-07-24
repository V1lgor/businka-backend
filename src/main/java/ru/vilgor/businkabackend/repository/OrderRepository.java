package ru.vilgor.businkabackend.repository;

import ru.vilgor.businkabackend.entity.Order;

public interface OrderRepository {
    void save(Order order);
}
