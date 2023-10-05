package by.teachmeskills.repositories;

import by.teachmeskills.entities.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {
    Order findById(int id);

    List<Order> findByUserId(int id);
}