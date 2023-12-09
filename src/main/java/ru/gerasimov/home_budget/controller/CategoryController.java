package ru.gerasimov.home_budget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gerasimov.home_budget.dto.CategoryDto;
import ru.gerasimov.home_budget.mapper.CategoryMapper;
import ru.gerasimov.home_budget.model.Category;
import ru.gerasimov.home_budget.repository.CategoryRepository;
import ru.gerasimov.home_budget.repository.UserRepository;
import ru.gerasimov.home_budget.service.JwtService;

import java.util.Objects;

import static ru.gerasimov.home_budget.controller.CategoryController.URL;


@RestController
@AllArgsConstructor
@RequestMapping(URL)
public class CategoryController {

    public static final String URL = "/api/category";
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private CategoryMapper mapper;
    private final JwtService jwtService;

    @GetMapping("/get")
    public String test(@RequestHeader HttpHeaders headers) {
        String token = Objects.requireNonNull(headers.get("Authorization")).get(0).substring(7);

        return "test" + jwtService.extractClaim(token, claims -> claims.get("userId", Integer.class));
    }

    @PostMapping
    public String getAllCategories(@RequestBody CategoryDto dto) {
        Category entity = mapper.toEntity(dto);
        entity.setUser(userRepository.findById(1).orElseThrow());
        categoryRepository.save(entity);
        return "get";
    }
}
