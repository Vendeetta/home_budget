package ru.gerasimov.home_budget.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.gerasimov.home_budget.TestContainerConfig;
import ru.gerasimov.home_budget.model.Category;
import ru.gerasimov.home_budget.model.Role;
import ru.gerasimov.home_budget.model.User;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Интеграционные тесты работы приложения с БД.
 */
@SpringBootTest
@ContextConfiguration(initializers = TestContainerConfig.PostgresInitializer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    @Transactional
    void before() {
        var user = User.builder()
                .userId(1)
                .email("email@email.ru")
                .category(Set.of())
                .password("123")
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }

    @Test
    @DisplayName("Тест корректного сохранения и получение сущностей из БД.")
    @Transactional
    void repositoryTest() {
        //given
        Category category1 = new Category(null, 100, "test1", userRepository.getReferenceById(1));
        Category category2 = new Category(null, 200, "test3", userRepository.getReferenceById(1));
        //when Сохраняем три сущности.
        categoryRepository.saveAll(List.of(category1, category2));
        //then Ожидаем получить из БД три сущности.
        List<Category> all = categoryRepository.findAll();
        assertEquals(2, all.size());
    }
}