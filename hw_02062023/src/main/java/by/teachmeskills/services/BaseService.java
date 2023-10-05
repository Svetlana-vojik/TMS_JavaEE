package by.teachmeskills.services;

import by.teachmeskills.entities.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    T create(T entity);

    List<T> read();

    void delete(int id);
}
