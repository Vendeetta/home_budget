package ru.gerasimov.home_budget.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.gerasimov.home_budget.TestContainerConfig;
import ru.gerasimov.home_budget.model.Category;
import ru.gerasimov.home_budget.model.Operation;
import ru.gerasimov.home_budget.model.Type;

import java.util.List;

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
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Тест корректного сохранения и получение сущностей Operation из БД.")
    void repositoryTest() {
        //given
        Category category = new Category(null, 100, "test");
        categoryRepository.save(category);
        Operation operation = new Operation(null, category, Type.IN, 100, "comment");
        //when Сохраняем одну сущности.
        repository.save(operation);
        //then Ожидаем получить из БД одну сущности.
        assertEquals(1, repository.findAll().size());
    }
}