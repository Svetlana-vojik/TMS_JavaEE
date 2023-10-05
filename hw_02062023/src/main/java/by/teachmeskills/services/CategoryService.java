package by.teachmeskills.services;

import by.teachmeskills.entities.Category;

public interface CategoryService extends BaseService<Category> {
    String findNameById(int id);
}