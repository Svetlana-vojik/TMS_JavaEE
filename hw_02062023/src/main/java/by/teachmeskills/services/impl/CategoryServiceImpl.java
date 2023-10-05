package by.teachmeskills.services.impl;

import by.teachmeskills.entities.Category;
import by.teachmeskills.repositories.CategoryRepository;
import by.teachmeskills.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.services.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    @Override
    public Category create(Category entity) {
        return categoryRepository.create(entity);
    }

    @Override
    public List<Category> read() {
        return categoryRepository.read();
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public String findNameById(int id) {
        return categoryRepository.findNameById(id);
    }
}
