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
import ru.gerasimov.home_budget.model.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Интеграционные тесты работы приложения с БД.
 */
@SpringBootTest
@ContextConfiguration(initializers = TestContainerConfig.PostgresInitializer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OperationRepositoryTest {

    @Autowired
    private OperationRepository repository;
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
    @DisplayName("Тест корректного сохранения и получение сущностей Operation из БД.")
    @Transactional
    void repositoryTest() {
        //given

        Category category = new Category(null, 100, "test", userRepository.getReferenceById(1));
        categoryRepository.save(category);
        Operation operation = new Operation(null, category, Type.IN, 100, "comment");
        //when Сохраняем одну сущности.
        repository.save(operation);
        //then Ожидаем получить из БД одну сущности.
        assertEquals(1, repository.findAll().size());
    }
}