package by.teachmeskills.services;

import by.teachmeskills.entities.BaseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    ModelAndView create(T entity);
   // Product create(T entity);

    List<T> read();

    void delete(int id);
}
