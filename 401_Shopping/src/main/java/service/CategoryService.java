package service;

import model.Category;
import repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService() {
        this.categoryRepository = new CategoryRepository();
    }

    public void initDB() {
        categoryRepository.create();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    public Category insert(Category category) {
        return categoryRepository.insert(category);
    }

    public void update(Category category) {
        categoryRepository.update(category);
    }

    public void delete(long id) {
        categoryRepository.delete(id);
    }
}
