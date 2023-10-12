package by.teachmeskills.services;

import by.teachmeskills.entities.BaseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    ModelAndView create(T entity);

    List<T> read();

    T update(T entity);

    void delete(int id);
}
