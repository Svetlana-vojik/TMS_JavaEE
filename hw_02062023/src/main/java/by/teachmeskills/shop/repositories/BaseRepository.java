package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.BaseEntity;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
    ConnectionPool pool = ConnectionPool.getInstance();

    T create(T entity);

    List<T> read();
    T update(T entity);

    void delete(int id);
}