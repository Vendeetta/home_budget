package ru.gerasimov.home_budget.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gerasimov.home_budget.dto.CategoryDto;
import ru.gerasimov.home_budget.mapper.CategoryMapper;
import ru.gerasimov.home_budget.repository.CategoryRepository;

import static ru.gerasimov.home_budget.controller.CategoryController.URL;


@RestController
@AllArgsConstructor
@RequestMapping(URL)
public class CategoryController {

    public static final String URL = "/api/category";
    private CategoryRepository categoryRepository;
    private CategoryMapper mapper;

    @GetMapping
    public String test() {
        return "test";
    }

    @PostMapping
    public String getAllCategories(@RequestBody CategoryDto dto) {
        categoryRepository.save(mapper.toEntity(dto));
        return "get";
    }
}
