package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {
    Order findById(int id);

    List<Order> findByUserId(int id);
}