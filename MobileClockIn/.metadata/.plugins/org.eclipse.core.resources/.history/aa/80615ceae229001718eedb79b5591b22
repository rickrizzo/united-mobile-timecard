package ua.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.server.model.Category;
import ua.server.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService extends AbstractService<Category, Long> {

    private CategoryRepository repository;


    @Autowired
    public CategoryService(CategoryRepository repository) {
        super(repository);
        this.repository=repository;
    }

    public List<Category> findByName(String name) {
        return repository.findByName(name);
    }
}
