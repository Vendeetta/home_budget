package ru.gerasimov.home_budget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gerasimov.home_budget.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public String test() {
        return "test";
    }
}
