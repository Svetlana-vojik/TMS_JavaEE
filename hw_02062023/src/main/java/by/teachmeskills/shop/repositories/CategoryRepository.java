package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.Category;

public interface CategoryRepository extends BaseRepository<Category> {
    Category findNameById(int id);
}